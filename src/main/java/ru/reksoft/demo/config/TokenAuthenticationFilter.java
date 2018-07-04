package ru.reksoft.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * Token prefix.
     */
    private static final String BEARER = "Bearer";

    /**
     * Messages container.
     */
    private final MessagesConfig messages;

    /**
     * Required authentication request matcher setting.
     */
    protected TokenAuthenticationFilter(RequestMatcher requestMatcher, MessagesConfig messages) {
        super(requestMatcher);
        this.messages = messages;
    }


    /**
     * Returns user authentication in case of successful authentication.
     *
     * @param request  - http request
     * @param response - http response
     * @return authentication
     * @throws AuthenticationException - if valid token not found in request
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response

    ) throws AuthenticationException {

        final String token;
        final String credentials = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (credentials == null) {
            throw new BadCredentialsException(messages.get("reksoft.demo.auth.filter.credentialsNotFound.message"));
        } else if (!credentials.startsWith(BEARER)) {
            throw new BadCredentialsException(messages.get("reksoft.demo.auth.filter.credentialsNotValid.message"));
        } else {
            token = credentials.substring(BEARER.length());
        }

        // todo: parse token and complement to (principal, credentials, authorities)!
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(token, token));
    }

    /**
     * Actions configuration in case of successful authentication.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain, Authentication authResult

    ) throws IOException, ServletException {

        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
