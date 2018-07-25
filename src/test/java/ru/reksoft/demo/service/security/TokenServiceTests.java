package ru.reksoft.demo.service.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
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
