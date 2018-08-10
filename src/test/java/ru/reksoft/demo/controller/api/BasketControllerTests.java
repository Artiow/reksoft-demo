package ru.reksoft.demo.controller.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import ru.reksoft.demo.boot.ReksoftDemoApplication;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
@TestPropertySource("classpath:application-test.properties")
@Sql(scripts = "file:database/testing/init_basket.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "file:database/testing/clear.sql", executionPhase = AFTER_TEST_METHOD)
@AutoConfigureMockMvc
public class BasketControllerTests extends AbstractSecuredControllerTests {

    @Autowired
    private MockMvc mvc;


    @Test
    public void get_forUnauthorizedUser_error401Returns() throws Exception {

        // arrange
        RequestBuilder request = get("/api/basket");

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result.andExpect(status().isUnauthorized());
    }

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
                .andExpect(jsonPath("$.content[0].media.id").value(1))
                .andExpect(jsonPath("$.content[0].count").value(1))
                .andExpect(jsonPath("$.content[1].media.id").value(4))
                .andExpect(jsonPath("$.content[1].count").value(1))
                .andExpect(jsonPath("$.content[2].media.id").value(6))
                .andExpect(jsonPath("$.content[2].count").value(1));
    }

    @Test
    public void add_forUnauthorizedUser_error401Returns() throws Exception {

        // arrange
        RequestBuilder request = post("/api/basket").param("added", String.valueOf(0));

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void add_byNonexistentMedia_error404Returns() throws Exception {

        // arrange
        String token = login(mvc, "user", "user");
        RequestBuilder request = post("/api/basket")
                .param("added", String.valueOf(0))
                .header("Authorization", token);

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result.andExpect(status().isNotFound());
    }

    @Test
    public void add_byAlreadyAddedMedia_error409Returns() throws Exception {

        // arrange
        String token = login(mvc, "user", "user");
        RequestBuilder request = post("/api/basket")
                .param("added", String.valueOf(1))
                .header("Authorization", token);

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result.andExpect(status().isConflict());
    }

    @Test
    public void add_forAuthorizeUser_basketLocationReturns() throws Exception {

        // arrange
        String token = login(mvc, "user", "user");
        RequestBuilder request = post("/api/basket")
                .param("added", String.valueOf(2))
                .header("Authorization", token);

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/basket"));
    }
}
