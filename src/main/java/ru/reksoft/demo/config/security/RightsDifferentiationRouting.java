package ru.reksoft.demo.config.security;

import org.springframework.security.web.util.matcher.*;

public interface RightsDifferentiationRouting {

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

    RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
            ADMIN_API_URLS,
            USER_API_URLS
    );
}
