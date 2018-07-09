package ru.reksoft.demo.service.security;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.reksoft.demo.mapper.manual.security.UserDetailsMapper;

@Service
public class SecurityService {

    private TokenService tokenService;

    private UserDetailsMapper mapper;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    public void setMapper(UserDetailsMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Getting used token type from token service.
     *
     * @return tokenType
     */
    public String getTokenType() {
        return tokenService.getTokenType();
    }

    /**
     * Returns JWT by UserDetails.
     *
     * @param userDetails - user data
     * @return encoded json
     */
    public String login(UserDetails userDetails) throws JwtException {
        return tokenService.generate(mapper.toMap(userDetails));
    }

    /**
     * Returns UserDetails by JWT.
     *
     * @param token - encoded json
     * @return user data
     */
    public UserDetails authentication(String token) throws JwtException {
        return mapper.toUserDetails(tokenService.verify(token));
    }
}
