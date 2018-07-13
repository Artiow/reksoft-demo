package ru.reksoft.demo.config.security;

import org.springframework.security.web.util.matcher.*;

public interface RightsDifferentiationRouting {

    RequestMatcher DEFAULT_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/error"),
            new AntPathRequestMatcher("/csrf"),
            new AntPathRequestMatcher("/")
    );

    RequestMatcher SWAGGER_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/**"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/v2/api-docs")
    );

    RequestMatcher USER_API_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/basket"),
            new AntPathRequestMatcher("/api/order")
    );

    RequestMatcher ADMIN_API_URLS = new AndRequestMatcher(
            new NegatedRequestMatcher(USER_API_URLS),
            new OrRequestMatcher(
                    new AntPathRequestMatcher("/api/*", "POST"),
                    new AntPathRequestMatcher("/api/*/*", "PUT"),
                    new AntPathRequestMatcher("/api/*/*", "DELETE"),
                    new AntPathRequestMatcher("/api/order/*")
            )
    );

    RequestMatcher PUBLIC_API_URLS = new AndRequestMatcher(
            new AntPathRequestMatcher("/api/**"),
            new NegatedRequestMatcher(ADMIN_API_URLS),
            new NegatedRequestMatcher(USER_API_URLS)
    );

    RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            PUBLIC_API_URLS,
            SWAGGER_URLS,
            DEFAULT_URLS
    );

    RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(
            PUBLIC_URLS
    );
}
