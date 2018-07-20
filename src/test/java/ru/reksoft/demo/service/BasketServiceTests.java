package ru.reksoft.demo.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.reksoft.demo.boot.ReksoftDemoApplication;
import ru.reksoft.demo.config.messages.MessageContainer;
import ru.reksoft.demo.mapper.BasketMapper;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUser;

import java.util.Collections;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
public class BasketServiceTests {

    private MessageContainer messages;

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
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new TestingAuthenticationToken(new IdentifiedUser(0, "user", "password", Collections.emptySet()), null)
                );

        basketService = new BasketService();
        basketService.setMessages(messages);
        basketService.setUserRepository(null);
        basketService.setMediaRepository(null);
        basketService.setCurrentBasketRepository(null);
        basketService.setBasketMapper(basketMapper);
    }


    @Test
    public void add() {

    }
}
