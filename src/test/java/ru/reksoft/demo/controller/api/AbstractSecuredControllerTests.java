package ru.reksoft.demo.controller.api;

import org.json.JSONObject;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public abstract class AbstractSecuredControllerTests {

    protected String login(MockMvc mvc, String login, String password) throws Exception {
        JSONObject json = new JSONObject(
                mvc
                        .perform(
                                get("/api/user/login")
                                        .param("login", login)
                                        .param("password", password)
                        ).andReturn()
                        .getResponse()
                        .getContentAsString()
        );

        return (String) json.get("tokenType") + ' ' + json.get("accessToken");
    }
}
