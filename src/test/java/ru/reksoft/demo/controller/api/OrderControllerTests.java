package ru.reksoft.demo.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.reksoft.demo.dto.pagination.filters.OrderFilterDTO;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
@TestPropertySource("classpath:application-test.properties")
@Sql(scripts = "file:database/testing/init_order.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "file:database/testing/clear.sql", executionPhase = AFTER_TEST_METHOD)
@AutoConfigureMockMvc
public class OrderControllerTests extends AbstractSecuredControllerTests {

    @Autowired
    private MockMvc mvc;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;


    @Test
    public void getList_forUnauthorizedUser_error401Returns() throws Exception {

        // arrange
        RequestBuilder request = get("/api/order/list");

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void getList_forForbiddenUser_error403Returns() throws Exception {

        // arrange
        String token = login(mvc, "user", "user");
        RequestBuilder request = get("/api/order/list")
                .header("Authorization", token);

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result.andExpect(status().isForbidden());
    }

    @Test
    public void getList_correct_ordersReturns() throws Exception {

        // arrange
        String token = login(mvc, "root", "root");
        byte[] content = new ObjectMapper().writeValueAsBytes(new OrderFilterDTO());
        RequestBuilder request = post("/api/order/list")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // act
        ResultActions result = mvc.perform(request);

        // assert
        result
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberOfElements").value(3))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[2].id").value(3));
    }
}
