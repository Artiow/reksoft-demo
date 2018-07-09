package ru.reksoft.demo.dto.security;

import java.net.URI;

public class LoginDTO {

    private String login;
    private URI userURI;

    public String getLogin() {
        return login;
    }

    public LoginDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public URI getUserURI() {
        return userURI;
    }

    public LoginDTO setUserURI(URI userURI) {
        this.userURI = userURI;
        return this;
    }
}
