package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.dto.OrderDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.mapper.OrderMapper;
import ru.reksoft.demo.repository.OrderRepository;
import ru.reksoft.demo.repository.OrderStatusRepository;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceCannotUpdateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

import javax.validation.constraints.NotNull;

@Service
public class OrderService {

    private MessagesConfig messages;

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;

    private OrderMapper orderMapper;

    @Autowired
    public void setMessages(MessagesConfig messages) {
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
     * Returns page with orders.
     *
     * @param dividerDTO - page divider
     * @return order page
     */
    @Transactional(readOnly = true)
    public PageDTO<OrderDTO> getList(@NotNull PageDividerDTO dividerDTO) {
        return null;
    }

    /**
     * Returns order by order id.
     *
     * @return order
     * @throws ResourceNotFoundException - if order not found by id
     */
    @Transactional(readOnly = true)
    public OrderDTO get(@NotNull Integer id) throws ResourceNotFoundException {
        return null;
    }

    /**
     * Make an order by order form for auth user.
     *
     * @param orderDTO - order form
     * @throws AuthorizationRequiredException - if authorization is missing
     * @throws ResourceCannotCreateException  - if current basket already exist for user and media
     */
    @Transactional
    public void make(@NotNull OrderDTO orderDTO) throws AuthorizationRequiredException, ResourceCannotCreateException {

    }

    /**
     * Update order status (set it as sent) by order id.
     *
     * @param id - order id
     * @throws ResourceNotFoundException     - if order not found
     * @throws ResourceCannotUpdateException - if order cannot updated
     */
    @Transactional
    public void send(@NotNull Integer id) throws ResourceNotFoundException, ResourceCannotUpdateException {

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
}
