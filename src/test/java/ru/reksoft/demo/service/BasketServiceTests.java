package ru.reksoft.demo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
public class BasketServiceTests {

    private MessageContainer messages;

    private UserEntity testUser;
    private ArrayList<MediaEntity> testMedias;
    private ArrayList<CurrentBasketEntity> testBasket;

    private UserRepositoryImpl userRepository;
    private MediaRepositoryImpl mediaRepository;
    private CurrentBasketRepositoryImpl currentBasketRepository;

    private BasketMapper basketMapper;

    private BasketService basketService;

    @Autowired
    public void setMessages(MessageContainer messages) {
        this.messages = messages;
    }

    @Autowired
    public void setBasketMapper(BasketMapper basketMapper) {
        this.basketMapper = basketMapper;
    }


    @Before
    public void setUp() {
        basketService = new BasketService();
        basketService.setMessages(messages);
        basketService.setBasketMapper(basketMapper);
        userRepository = new UserRepositoryImpl();
        basketService.setUserRepository(userRepository);
        mediaRepository = new MediaRepositoryImpl();
        basketService.setMediaRepository(mediaRepository);
        currentBasketRepository = new CurrentBasketRepositoryImpl();
        basketService.setCurrentBasketRepository(currentBasketRepository);
    }

    private void restart() {
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

        userRepository.setInMemoryUser(testUser);
        mediaRepository.setInMemoryMedias(testMedias);
        currentBasketRepository.setInMemoryBasket(testBasket);
    }


    @Test(expected = AuthorizationRequiredException.class)
    @WithAnonymousUser
    public void get_forAnonymousUser_authException() throws AuthorizationRequiredException {

        // arrange
        restart();

        // act
        basketService.get();
    }

    @Test
    @WithMockIdentifiedUser
    public void get_forAuthorizeUser_basketReturns() throws AuthorizationRequiredException {

        // arrange
        restart();

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

        // arrange
        restart();

        // act
        basketService.add(2);

        // assert
        Assert.assertEquals(((ArrayList<CurrentBasketEntity>) testUser.getBasket()).get(2).getMedia().getId().intValue(), 2);
    }

    @Test
    @WithMockIdentifiedUser
    public void update_forAuthorizeUser_quantityUpdated() throws AuthorizationRequiredException, ResourceNotFoundException {

        // arrange
        restart();

        // act
        basketService.update(0, 5);

        // assert
        Assert.assertEquals(((ArrayList<CurrentBasketEntity>) testUser.getBasket()).get(0).getCount().intValue(), 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @WithMockIdentifiedUser
    public void remove_forAuthorizeUser_itemRemoved() throws AuthorizationRequiredException, ResourceNotFoundException {

        // arrange
        restart();

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


    private static class UserRepositoryImpl implements UserRepository {

        private UserEntity inMemoryUser;


        UserEntity getInMemoryUser() {
            return inMemoryUser;
        }

        void setInMemoryUser(UserEntity inMemoryUser) {
            this.inMemoryUser = inMemoryUser;
        }


        @Override
        public UserEntity findByLogin(String login) {
            if (existsByLogin(login)) {
                return inMemoryUser;
            } else {
                return null;
            }
        }

        @Override
        public boolean existsByLogin(String login) {
            return inMemoryUser.getLogin().equals(login);
        }

        @Override
        public List<UserEntity> findAll() {
            return null;
        }

        @Override
        public List<UserEntity> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<UserEntity> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<UserEntity> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(UserEntity entity) {

        }

        @Override
        public void deleteAll(Iterable<? extends UserEntity> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends UserEntity> S save(S entity) {
            inMemoryUser = entity;
            return entity;
        }

        @Override
        public <S extends UserEntity> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<UserEntity> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return inMemoryUser.getId().equals(integer);
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends UserEntity> S saveAndFlush(S s) {
            S entity = save(s);
            flush();
            return entity;
        }

        @Override
        public void deleteInBatch(Iterable<UserEntity> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public UserEntity getOne(Integer integer) {
            if (existsById(integer)) {
                return inMemoryUser;
            } else {
                return null;
            }
        }

        @Override
        public <S extends UserEntity> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends UserEntity> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends UserEntity> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends UserEntity> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public Optional<UserEntity> findOne(Specification<UserEntity> specification) {
            return Optional.empty();
        }

        @Override
        public List<UserEntity> findAll(Specification<UserEntity> specification) {
            return null;
        }

        @Override
        public Page<UserEntity> findAll(Specification<UserEntity> specification, Pageable pageable) {
            return null;
        }

        @Override
        public List<UserEntity> findAll(Specification<UserEntity> specification, Sort sort) {
            return null;
        }

        @Override
        public long count(Specification<UserEntity> specification) {
            return 0;
        }
    }

    private static class MediaRepositoryImpl implements MediaRepository {

        private ArrayList<MediaEntity> inMemoryMedias;


        ArrayList<MediaEntity> getInMemoryMedias() {
            return inMemoryMedias;
        }

        void setInMemoryMedias(ArrayList<MediaEntity> inMemoryMedias) {
            this.inMemoryMedias = inMemoryMedias;
        }


        @Override
        public boolean existsByAlbumIdAndTypeId(Integer albumId, Integer typeId) {
            return false;
        }

        @Override
        public Page<MediaEntity> findByAlbumSingerId(Integer id, Pageable pageable) {
            return null;
        }

        @Override
        public Page<MediaEntity> findByAlbumLabelId(Integer id, Pageable pageable) {
            return null;
        }

        @Override
        public Page<MediaEntity> findByAlbumId(Integer id, Pageable pageable) {
            return null;
        }

        @Override
        public List<MediaEntity> findAll() {
            return null;
        }

        @Override
        public List<MediaEntity> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<MediaEntity> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<MediaEntity> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(MediaEntity entity) {

        }

        @Override
        public void deleteAll(Iterable<? extends MediaEntity> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends MediaEntity> S save(S entity) {
            return null;
        }

        @Override
        public <S extends MediaEntity> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<MediaEntity> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends MediaEntity> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<MediaEntity> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public MediaEntity getOne(Integer integer) {
            return inMemoryMedias.get(integer);
        }

        @Override
        public <S extends MediaEntity> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends MediaEntity> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends MediaEntity> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends MediaEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends MediaEntity> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends MediaEntity> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public Optional<MediaEntity> findOne(Specification<MediaEntity> specification) {
            return Optional.empty();
        }

        @Override
        public List<MediaEntity> findAll(Specification<MediaEntity> specification) {
            return null;
        }

        @Override
        public Page<MediaEntity> findAll(Specification<MediaEntity> specification, Pageable pageable) {
            return null;
        }

        @Override
        public List<MediaEntity> findAll(Specification<MediaEntity> specification, Sort sort) {
            return null;
        }

        @Override
        public long count(Specification<MediaEntity> specification) {
            return 0;
        }
    }

    private static class CurrentBasketRepositoryImpl implements CurrentBasketRepository {

        private ArrayList<CurrentBasketEntity> inMemoryBasket;


        ArrayList<CurrentBasketEntity> getInMemoryBasket() {
            return inMemoryBasket;
        }

        void setInMemoryBasket(ArrayList<CurrentBasketEntity> inMemoryBasket) {
            this.inMemoryBasket = inMemoryBasket;
        }


        @Override
        public CurrentBasketEntity findByPkUserIdAndPkMediaId(Integer userId, Integer mediaId) {
            CurrentBasketEntity sought = null;
            for (CurrentBasketEntity item : inMemoryBasket) {
                if ((item.getUser().getId().equals(userId)) && (item.getMedia().getId().equals(mediaId))) {
                    sought = item;
                    break;
                }
            }

            return sought;
        }

        @Override
        public boolean existsByPkUserIdAndPkMediaId(Integer userId, Integer mediaId) {
            for (CurrentBasketEntity item : inMemoryBasket) {
                if ((item.getUser().getId().equals(userId)) && (item.getMedia().getId().equals(mediaId))) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public void deleteByPkUserIdAndPkMediaId(Integer userId, Integer mediaId) {
            Optional.ofNullable(findByPkUserIdAndPkMediaId(userId, mediaId)).map(inMemoryBasket::remove);
        }

        @Override
        public List<CurrentBasketEntity> findAll() {
            return null;
        }

        @Override
        public List<CurrentBasketEntity> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<CurrentBasketEntity> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<CurrentBasketEntity> findAllById(Iterable<CurrentBasketEntityPK> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(CurrentBasketEntityPK currentBasketEntityPK) {

        }

        @Override
        public void delete(CurrentBasketEntity entity) {

        }

        @Override
        public void deleteAll(Iterable<? extends CurrentBasketEntity> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends CurrentBasketEntity> S save(S entity) {
            return null;
        }

        @Override
        public <S extends CurrentBasketEntity> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<CurrentBasketEntity> findById(CurrentBasketEntityPK currentBasketEntityPK) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(CurrentBasketEntityPK currentBasketEntityPK) {
            return false;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends CurrentBasketEntity> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<CurrentBasketEntity> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public CurrentBasketEntity getOne(CurrentBasketEntityPK currentBasketEntityPK) {
            return null;
        }

        @Override
        public <S extends CurrentBasketEntity> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends CurrentBasketEntity> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends CurrentBasketEntity> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends CurrentBasketEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends CurrentBasketEntity> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends CurrentBasketEntity> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public Optional<CurrentBasketEntity> findOne(Specification<CurrentBasketEntity> specification) {
            return Optional.empty();
        }

        @Override
        public List<CurrentBasketEntity> findAll(Specification<CurrentBasketEntity> specification) {
            return null;
        }

        @Override
        public Page<CurrentBasketEntity> findAll(Specification<CurrentBasketEntity> specification, Pageable pageable) {
            return null;
        }

        @Override
        public List<CurrentBasketEntity> findAll(Specification<CurrentBasketEntity> specification, Sort sort) {
            return null;
        }

        @Override
        public long count(Specification<CurrentBasketEntity> specification) {
            return 0;
        }
    }
}
