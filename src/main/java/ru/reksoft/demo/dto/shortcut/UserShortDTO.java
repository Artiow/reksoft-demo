package ru.reksoft.demo.dto.shortcut;

import java.net.URI;

public class UserShortDTO {

    private String login;
    private URI userURI;

    public String getLogin() {
        return login;
    }

    public UserShortDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public URI getUserURI() {
        return userURI;
    }

    public UserShortDTO setUserURI(URI userURI) {
        this.userURI = userURI;
        return this;
    }
}
