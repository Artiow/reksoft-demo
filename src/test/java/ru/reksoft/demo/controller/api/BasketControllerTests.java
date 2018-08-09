package ru.reksoft.demo.controller.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import ru.reksoft.demo.boot.ReksoftDemoApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class BasketControllerTests extends AbstractSecuredControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void get_forAuthorizeUser_basketReturns() throws Exception {

        // arrange
        String token = login(mvc, "user", "user");
        RequestBuilder request = get("/api/basket").header("Authorization", token);

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberOfElements").value(3))
                .andExpect(jsonPath("$.content[0].media.id").value(4))
                .andExpect(jsonPath("$.content[0].count").value(1))
                .andExpect(jsonPath("$.content[1].media.id").value(3))
                .andExpect(jsonPath("$.content[1].count").value(1))
                .andExpect(jsonPath("$.content[2].media.id").value(5))
                .andExpect(jsonPath("$.content[2].count").value(1));
    }
}
