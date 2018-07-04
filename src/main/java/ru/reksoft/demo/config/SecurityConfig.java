package ru.reksoft.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final RequestMatcher PROTECTED_URLS =
            new NegatedRequestMatcher(
                    new OrRequestMatcher(
                            new AntPathRequestMatcher("/api/**", "GET"),
                            new AntPathRequestMatcher("/list/**", "POST")
                    )
            );

    private final TokenAuthenticationProvider provider;

    @Autowired
    public SecurityConfig(TokenAuthenticationProvider provider) {
        super();
        this.provider = provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }


    /**
     * Differentiation of rights configuration.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()

                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }


    /**
     * FilterRegistrationBean configuration.
     * Disable Spring Boot automatic filter registration.
     *
     * @param authenticationFilter - custom filter
     * @return registration bean
     */
    @Bean
    public FilterRegistrationBean registrationBean(final TokenAuthenticationFilter authenticationFilter) {
        final FilterRegistrationBean<TokenAuthenticationFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(authenticationFilter);
        bean.setEnabled(false);

        return bean;
    }

    /**
     * TokenAuthenticationFilter configuration.
     *
     * @param messages - messages config
     * @return filter bean
     * @throws Exception - if something goes wrong
     */
    @Bean
    public TokenAuthenticationFilter authenticationFilter(final AuthenticationSuccessHandler successHandler, final MessagesConfig messages)
            throws Exception {

        final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS, messages);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler);

        return filter;
    }

    /**
     * AuthenticationSuccessHandler configuration.
     *
     * @return success handler bean
     */
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy((httpServletRequest, httpServletResponse, s) -> {
            // no redirect
        });

        return successHandler;
    }

    /**
     * BCryptPasswordEncoder configuration.
     *
     * @return password encoder bean
     */
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
