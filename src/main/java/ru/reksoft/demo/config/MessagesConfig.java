package ru.reksoft.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
public class MessagesConfig {

    private MessageSource messageSource;
    private MessageSourceAccessor accessor;

    @Autowired
    public MessagesConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Config accessor.
     */
    @PostConstruct
    private void init() {
        this.accessor = new MessageSourceAccessor(this.messageSource, Locale.getDefault());
    }

    /**
     * Config message source for validation messages.
     *
     * @return LocalValidatorFactoryBean bean
     */
    @Bean
    public LocalValidatorFactoryBean getLocalValidatorFactoryBean() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();

        factory.setValidationMessageSource(this.messageSource);
        return factory;
    }


    public String get(String msg) {
        try {
            return accessor.getMessage(msg);
        } catch (NoSuchMessageException e) {
            return '{' + msg + '}';
        }
    }
}
