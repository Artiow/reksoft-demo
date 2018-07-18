package ru.reksoft.demo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.reksoft.demo.boot.ReksoftDemoApplication;
import ru.reksoft.demo.service.security.TokenService;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
public class TokenServiceTests {

    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Test
    public void generateAndVerify_mapSentAndMapReturns_mapsMatched() {

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
