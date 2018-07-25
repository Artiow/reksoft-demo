package ru.reksoft.demo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.test.context.junit4.SpringRunner;
import ru.reksoft.demo.boot.ReksoftDemoApplication;
import ru.reksoft.demo.config.messages.MessageContainer;
import ru.reksoft.demo.domain.*;
import ru.reksoft.demo.mapper.OrderMapper;
import ru.reksoft.demo.repository.OrderRepository;
import ru.reksoft.demo.repository.OrderStatusRepository;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUser;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
public class OrderServiceTests {

    private OrderService orderService;

    private ArrayList<MediaEntity> testMedias;
    private ArrayList<OrderEntity> testOrders;
    private OrderStatusEntity testExpectStatus;
    private OrderStatusEntity testSentStatus;
    private OrderEntity testNewOrder;

    private UserEntity testUser;

    @Autowired
    private MessageContainer messages;

    @Autowired
    private OrderMapper orderMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderStatusRepository orderStatusRepository;


    @Before
    public void setUp() {
        orderService = new OrderService();
        orderService.setMessages(messages);
        orderService.setOrderMapper(orderMapper);
        orderService.setUserRepository(userRepository);
        orderService.setOrderRepository(orderRepository);
        orderService.setOrderStatusRepository(orderStatusRepository);

        setData();
        setMockLogic();
    }

    private void setData() {
        UserRoleEntity testRole = new UserRoleEntity();
        testRole.setCode("user");

        testUser = new UserEntity();
        testUser.setId(0);
        testUser.setLogin("login");
        testUser.setPassword("password");
        testUser.setAddress("test-address");
        testUser.setRole(testRole);

        int mediaCapacity = 2;
        testMedias = new ArrayList<>(mediaCapacity);
        for (int i = 0; i < mediaCapacity; i++) {
            MediaEntity media = new MediaEntity();
            media.setId(i);
            media.setPrice(1000);
            testMedias.add(media);
        }

        int basketCapacity = 2;
        ArrayList<CurrentBasketEntity> testBasket = new ArrayList<>(basketCapacity);
        for (int i = 0; i < basketCapacity; i++) {
            CurrentBasketEntity item = new CurrentBasketEntity();
            item.setMedia(testMedias.get(i));
            item.setUser(testUser);
            item.setCount(1);
            testBasket.add(item);
        }

        testUser.setBasket(testBasket);

        testExpectStatus = new OrderStatusEntity();
        testExpectStatus.setId(1);
        testExpectStatus.setCode("expect");

        testSentStatus = new OrderStatusEntity();
        testSentStatus.setId(2);
        testSentStatus.setCode("sent");

        int orderCapacity = 3;
        testOrders = new ArrayList<>(orderCapacity);
        for (int i = 0; i < orderCapacity; i++) {
            OrderEntity order = new OrderEntity();
            order.setId(i);
            order.setStatus(testExpectStatus);
            testOrders.add(order);
        }
    }

    private void setMockLogic() {

        // user repository
        when(userRepository.getOne(testUser.getId())).thenReturn(testUser);
        when(userRepository.save(testUser)).thenReturn(testUser);

        // order repository
        when(orderRepository.getOne(any(Integer.class))).then(
                invoke -> testOrders.get(invoke.getArgument(0))
        );
        when(orderRepository.save(any(OrderEntity.class))).then(
                invoke -> {
                    testNewOrder = invoke.getArgument(0);
                    return testNewOrder;
                }
        );

        // order status repository
        when(orderStatusRepository.findByCode(testExpectStatus.getCode())).thenReturn(testExpectStatus);
        when(orderStatusRepository.findByCode(testSentStatus.getCode())).thenReturn(testSentStatus);
    }


    @Test(expected = AuthorizationRequiredException.class)
    @WithAnonymousUser
    public void make_forAnonymousUser() throws AuthorizationRequiredException, ResourceCannotCreateException {

        // act
        orderService.make();

        // no assert
        // must throw AuthorizationRequiredException
    }

    @Test
    @WithMockIdentifiedUser
    public void make_forAuthorizeUser() throws AuthorizationRequiredException, ResourceCannotCreateException {

        // act
        orderService.make();

        // assert
        Assert.assertTrue(testUser.getBasket().isEmpty());
        Assert.assertEquals(testUser.getAddress(), testNewOrder.getAddress());
        Assert.assertEquals(2000, testNewOrder.getCost().intValue());
        for (OrderedMediaEntity item : testNewOrder.getMedias()) {
            Integer itemMediaId = item.getMedia().getId();
            Assert.assertEquals(testMedias.get(itemMediaId).getId(), itemMediaId);
            Assert.assertEquals(1, item.getCount().intValue());
        }
    }

    @Test
    public void send_ordersSent() throws ResourceNotFoundException {

        // act
        orderService.send(0);
        orderService.send(1);
        orderService.send(2);

        // assert
        for (OrderEntity order : testOrders) {
            Assert.assertEquals(testSentStatus.getCode(), order.getStatus().getCode());
        }
    }


    @Retention(RetentionPolicy.RUNTIME)
    @WithSecurityContext(factory = WithMockIdentifiedUserSecurityContextFactory.class)
    private @interface WithMockIdentifiedUser {

        int id() default 0;

        String login() default "login";

        String password() default "password";

        String role() default "user";
    }

    private static class WithMockIdentifiedUserSecurityContextFactory implements WithSecurityContextFactory<WithMockIdentifiedUser> {

        @Override
        public SecurityContext createSecurityContext(WithMockIdentifiedUser user) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            IdentifiedUserDetails principal = new IdentifiedUser(
                    user.id(),
                    User.builder()
                            .username(user.login())
                            .password(user.password())
                            .roles(user.role().toUpperCase())
                            .build()
            );

            context.setAuthentication(new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities()));
            return context;
        }
    }
}
