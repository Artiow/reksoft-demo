package ru.reksoft.demo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan({
        "ru.reksoft.demo.domain"
})
@EnableJpaRepositories({
        "ru.reksoft.demo.repository"
})
@ComponentScan({
        "ru.reksoft.demo.mapper",
        "ru.reksoft.demo.config",
        "ru.reksoft.demo.service",
        "ru.reksoft.demo.controller"
})
public class ReksoftDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ReksoftDemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ReksoftDemoApplication.class);
    }
}
