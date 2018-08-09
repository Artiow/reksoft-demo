package ru.reksoft.demo.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUser;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class AbstractSecuredServiceTests {

    @Retention(RetentionPolicy.RUNTIME)
    @WithSecurityContext(factory = WithMockIdentifiedUserSecurityContextFactory.class)
    protected @interface WithMockIdentifiedUser {

        int id() default 0;

        String login() default "login";

        String password() default "password";

        String role() default "user";
    }

    protected static class WithMockIdentifiedUserSecurityContextFactory implements WithSecurityContextFactory<WithMockIdentifiedUser> {

        @Override
        public SecurityContext createSecurityContext(WithMockIdentifiedUser user) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            IdentifiedUserDetails principal = new IdentifiedUser(
                    user.id(),
                    User.builder()
                            .username(user.login())
                            .password(user.password())
                            .roles(user.role().toUpperCase())
                            .build()
            );

            context.setAuthentication(new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities()));
            return context;
        }
    }
}
