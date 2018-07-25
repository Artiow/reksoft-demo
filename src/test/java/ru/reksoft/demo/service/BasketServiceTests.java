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
import ru.reksoft.demo.domain.CurrentBasketEntity;
import ru.reksoft.demo.domain.MediaEntity;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.domain.UserRoleEntity;
import ru.reksoft.demo.dto.BasketDTO;
import ru.reksoft.demo.dto.shortcut.MediaShortDTO;
import ru.reksoft.demo.mapper.BasketMapper;
import ru.reksoft.demo.repository.CurrentBasketRepository;
import ru.reksoft.demo.repository.MediaRepository;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUser;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
public class BasketServiceTests {

    private BasketService basketService;

    private UserEntity testUser;
    private ArrayList<MediaEntity> testMedias;
    private ArrayList<CurrentBasketEntity> testBasket;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MediaRepository mediaRepository;

    @MockBean
    private CurrentBasketRepository currentBasketRepository;

    @Autowired
    private MessageContainer messages;

    @Autowired
    private BasketMapper basketMapper;


    @Before
    public void setUp() {
        basketService = new BasketService();
        basketService.setMessages(messages);
        basketService.setBasketMapper(basketMapper);
        basketService.setUserRepository(userRepository);
        basketService.setMediaRepository(mediaRepository);
        basketService.setCurrentBasketRepository(currentBasketRepository);

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
        testUser.setRole(testRole);

        int mediaCapacity = 3;
        testMedias = new ArrayList<>(mediaCapacity);
        for (int i = 0; i < mediaCapacity; i++) {
            MediaEntity media = new MediaEntity();
            media.setId(i);
            media.setPrice(1000);
            testMedias.add(media);
        }

        int basketCapacity = 2;
        testBasket = new ArrayList<>(basketCapacity);
        for (int i = 0; i < basketCapacity; i++) {
            CurrentBasketEntity item = new CurrentBasketEntity();
            item.setMedia(testMedias.get(i));
            item.setUser(testUser);
            item.setCount(1);
            testBasket.add(item);
        }

        testUser.setBasket(testBasket);
    }

    private void setMockLogic() {

        // user repository
        when(userRepository.getOne(testUser.getId())).thenReturn(testUser);
        when(userRepository.save(testUser)).thenReturn(testUser);

        // media repository
        for (MediaEntity media : testMedias) {
            when(mediaRepository.getOne(media.getId())).thenReturn(media);
        }

        // current basket repository
        for (CurrentBasketEntity item : testBasket) {
            int userId = item.getUser().getId();
            int mediaId = item.getMedia().getId();
            doAnswer(invoke -> testBasket.remove(item)).when(currentBasketRepository).deleteByPkUserIdAndPkMediaId(userId, mediaId);
            when(currentBasketRepository.existsByPkUserIdAndPkMediaId(userId, mediaId)).thenReturn(true);
            when(currentBasketRepository.findByPkUserIdAndPkMediaId(userId, mediaId)).thenReturn(item);
            when(currentBasketRepository.save(item)).thenReturn(item);
        }
    }


    @Test(expected = AuthorizationRequiredException.class)
    @WithAnonymousUser
    public void get_forAnonymousUser_authException() throws AuthorizationRequiredException {

        // act
        basketService.get();

        // no assert
        // must throw AuthorizationRequiredException
    }

    @Test
    @WithMockIdentifiedUser
    public void get_forAuthorizeUser_basketReturns() throws AuthorizationRequiredException {

        // act
        BasketDTO basketDTO = basketService.get();

        // assert
        Assert.assertEquals(basketDTO.getNumberOfElements().intValue(), testBasket.size());
        for (BasketDTO.BasketItemDTO itemDTO : basketDTO.getContent()) {
            Assert.assertEquals(itemDTO.getCount().intValue(), 1);

            MediaShortDTO mediaShortDTO = itemDTO.getMedia();
            MediaEntity mediaEntity = testMedias.get(mediaShortDTO.getId());

            Assert.assertEquals(mediaEntity.getId(), mediaShortDTO.getId());
            Assert.assertEquals(mediaEntity.getPrice(), mediaShortDTO.getPrice());
        }
    }

    @Test
    @WithMockIdentifiedUser
    public void add_forAuthorizeUser_itemAdded() throws AuthorizationRequiredException, ResourceCannotCreateException, ResourceNotFoundException {

        // act
        basketService.add(2);

        // assert
        Assert.assertEquals(((ArrayList<CurrentBasketEntity>) testUser.getBasket()).get(2).getMedia().getId().intValue(), 2);
    }

    @Test
    @WithMockIdentifiedUser
    public void update_forAuthorizeUser_quantityUpdated() throws AuthorizationRequiredException, ResourceNotFoundException {

        // act
        basketService.update(0, 5);

        // assert
        Assert.assertEquals(((ArrayList<CurrentBasketEntity>) testUser.getBasket()).get(0).getCount().intValue(), 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @WithMockIdentifiedUser
    public void remove_forAuthorizeUser_itemRemoved() throws AuthorizationRequiredException, ResourceNotFoundException {

        // act
        basketService.remove(1);

        // assert (was failed)
        Assert.assertEquals(((ArrayList<CurrentBasketEntity>) testUser.getBasket()).get(1).getMedia().getId().intValue(), 1);
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
