package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.messages.MessageContainer;
import ru.reksoft.demo.domain.*;
import ru.reksoft.demo.dto.OrderDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.OrderFilterDTO;
import ru.reksoft.demo.mapper.OrderMapper;
import ru.reksoft.demo.repository.OrderRepository;
import ru.reksoft.demo.repository.OrderStatusRepository;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.service.generic.AbstractService;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class OrderService {

    private MessageContainer messages;

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;

    private OrderMapper orderMapper;

    @Autowired
    public void setMessages(MessageContainer messages) {
        this.messages = messages;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderStatusRepository(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    /**
     * Returns page with orders by a certain statuses.
     *
     * @param filterDTO - order filter
     * @return order page
     */
    @Transactional(readOnly = true)
    public PageDTO<OrderDTO> getList(@NotNull OrderFilterDTO filterDTO) {
        OrderFilter searcher = new OrderFilter(filterDTO);
        return new PageDTO<>(orderRepository.findAll(searcher, searcher.getPageRequest()).map(orderMapper::toDTO));
    }

    /**
     * Returns order by order id.
     *
     * @param id - order id
     * @return order
     * @throws ResourceNotFoundException - if order not found by id
     */
    @Transactional(readOnly = true)
    public OrderDTO get(@NotNull Integer id) throws ResourceNotFoundException {
        try {
            return orderMapper.toDTO(orderRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Order.notExistById.message", id), e);
        }
    }

    /**
     * Make an order by order form for auth user.
     *
     * @param orderDTO - order form
     * @throws AuthorizationRequiredException - if authorization is missing
     * @throws ResourceCannotCreateException  - if current basket is empty for user and media
     */
    @Transactional
    public void make(@NotNull OrderDTO orderDTO) throws AuthorizationRequiredException, ResourceCannotCreateException {
        String ORDER_EXPECT = "expect"; // order expect status code

        UserEntity user = getCurrentUserEntity();
        Collection<CurrentBasketEntity> basket = user.getBasket();

        if ((basket == null) || (basket.isEmpty())) {
            throw new ResourceCannotCreateException(messages.get("reksoft.demo.Basket.isEmpty.message"));
        } else {
            OrderEntity order = orderMapper.toEntity(orderDTO);
            order.setStatus(orderStatusRepository.findByCode(ORDER_EXPECT));
            order.setMedias(new ArrayList<>(basket.size()));
            order.setAddress(user.getAddress());

            Integer cost = 0;
            for (CurrentBasketEntity basketItem : basket) {
                Integer count = basketItem.getCount();
                cost += ((basketItem.getMedia().getPrice()) * count);

                OrderedMediaEntity orderItem = new OrderedMediaEntity();
                orderItem.setMedia(basketItem.getMedia());
                orderItem.setOrder(order);
                orderItem.setCount(count);

                order.getMedias().add(orderItem);
            }

            order.setCost(cost);
            orderRepository.save(order);
            user.getBasket().clear();
            userRepository.save(user);
        }
    }

    /**
     * Update order status (set it as sent) by order id.
     *
     * @param id - order id
     * @throws ResourceNotFoundException - if order not found
     */
    @Transactional
    public void send(@NotNull Integer id) throws ResourceNotFoundException {
        String ORDER_SENT = "sent"; // order sent status code

        try {
            OrderEntity orderEntity = orderRepository.getOne(id);
            orderEntity.setStatus(orderStatusRepository.findByCode(ORDER_SENT));
            orderRepository.save(orderEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Order.notExistById.message", id), e);
        }
    }


    /**
     * Returns authenticated user id.
     *
     * @return user id
     * @throws AuthorizationRequiredException - if user is anonymous
     */
    private Integer getCurrentUserId() throws AuthorizationRequiredException {
        try {
            return ((IdentifiedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        } catch (ClassCastException e) {
            throw new AuthorizationRequiredException(messages.get("reksoft.demo.auth.filter.credentialsNotFound.message"), e);
        }
    }

    /**
     * Returns authenticated user.
     *
     * @return user entity
     * @throws AuthorizationRequiredException - if user is anonymous
     */
    private UserEntity getCurrentUserEntity() throws AuthorizationRequiredException {
        return userRepository.getOne(getCurrentUserId());
    }


    public static class OrderFilter extends AbstractService.PageDivider implements Specification<OrderEntity> {

        private Collection<String> statuses;


        public OrderFilter(OrderFilterDTO dto) {
            super(dto);

            configureSearchByStatuses(dto);
        }


        private void configureSearchByStatuses(OrderFilterDTO dto) {
            Collection<String> statuses = dto.getStatusCodes();
            if ((statuses != null) && (!statuses.isEmpty())) {
                this.statuses = new ArrayList<>(statuses.size());
                for (String status : statuses) {
                    this.statuses.add(status.toLowerCase());
                }
            }
        }


        @Override
        public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if (statuses != null) {
                return searchByCodePredicate(root, query, cb);
            } else {
                return null;
            }
        }

        private Predicate searchByCodePredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.lower(root.join(OrderEntity_.status).get(OrderStatusEntity_.code)).in(statuses);
        }
    }
}
