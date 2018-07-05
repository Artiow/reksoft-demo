package ru.reksoft.demo.service.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Service
public class SecurityService {

    private ObjectMapper mapper;

    private TypeReference<Map<String, Object>> mapTypeReference;

    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
        mapTypeReference = new TypeReference<Map<String, Object>>() {

        };
    }


    /**
     * Returns JWT by UserDetails.
     *
     * @param user - user data
     * @return encoded json
     */
    public Optional<String> login(UserDetails user) {
        return Optional.ofNullable(tokenService.generate(mapper.convertValue(user, mapTypeReference)));
    }

    /**
     * Returns UserDetails by JWT.
     *
     * @param token - encoded json
     * @return user data
     */
    public Optional<UserDetails> authentication(String token) {
        return Optional.ofNullable(tokenService.verify(token)).map(map -> mapper.convertValue(map, UserDetails.class));
    }
}
