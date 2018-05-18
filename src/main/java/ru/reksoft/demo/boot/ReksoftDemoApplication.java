package ru.reksoft.demo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "ru.reksoft.demo.config",
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
