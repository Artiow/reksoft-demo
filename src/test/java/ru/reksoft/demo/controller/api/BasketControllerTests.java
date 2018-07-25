package ru.reksoft.demo.controller.api;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.reksoft.demo.boot.ReksoftDemoApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class BasketControllerTests {

    private String token;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        JSONObject json = new JSONObject(
                mvc
                        .perform(
                                get("/api/user/login")
                                        .param("login", "user")
                                        .param("password", "user")
                        ).andReturn()
                        .getResponse()
                        .getContentAsString()
        );

        token = (String) json.get("tokenType") + ' ' + json.get("accessToken");
    }

    @Test
    public void test() throws Exception {
        mvc
                .perform(get("/api/basket").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberOfElements").value(3));
    }
}
