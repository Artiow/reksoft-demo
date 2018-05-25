package ru.reksoft.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    /**
     * Configure resource handlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

}
