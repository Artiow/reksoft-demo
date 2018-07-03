package ru.reksoft.demo.config;

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
     * Required authentication request matcher setting.
     */
    protected TokenAuthenticationFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
    }


    /**
     * Returns user authentication in case of successful authentication.
     *
     * @param request  - http request
     * @param response - http response
     * @return authentication
     * @throws AuthenticationException - if valid credentials not found in request
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response

    ) throws AuthenticationException {

        final String token;
        final String credentials = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (credentials == null) {
            // todo: get message?
            throw new BadCredentialsException("credentials not found in request");
        } else if (!credentials.startsWith(BEARER)) {
            // todo: get message?
            throw new BadCredentialsException("sent credentials not valid");
        } else {
            token = credentials.substring(BEARER.length());
        }

        // todo: complement to (principal, credentials, authorities)?
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
