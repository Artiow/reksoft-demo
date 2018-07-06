package ru.reksoft.demo.dto.security;

import ru.reksoft.demo.dto.generic.DataTransferObject;
import ru.reksoft.demo.dto.shortcut.UserShortDTO;

public class TokenDTO implements DataTransferObject {

    private String accessToken;
    private String tokenType;
    private UserShortDTO user;


    public String getAccessToken() {
        return accessToken;
    }

    public TokenDTO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public TokenDTO setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public UserShortDTO getUser() {
        return user;
    }

    public TokenDTO setUser(UserShortDTO user) {
        this.user = user;
        return this;
    }
}
