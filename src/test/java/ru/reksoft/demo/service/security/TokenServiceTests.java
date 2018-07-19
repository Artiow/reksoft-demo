package ru.reksoft.demo.service.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.reksoft.demo.boot.ReksoftDemoApplication;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
public class TokenServiceTests {

    private TokenService tokenService;


    @Before
    public void setUp() {
        tokenService = new TokenService();
        tokenService.setIssuer("test-issuer");
        tokenService.setSign("test-sign");
    }


    @Test
    public void lifecycle() {

        // arrange
        Map<String, Object> entryMap = new HashMap<>();
        entryMap.put("field1", "value1");
        entryMap.put("field2", "value2");

        // act
        Map<String, Object> resultMap = tokenService.verify(tokenService.generate(entryMap));

        // assert
        Assert.assertEquals("value1", resultMap.get("field1"));
        Assert.assertEquals("value2", resultMap.get("field2"));
    }
}
