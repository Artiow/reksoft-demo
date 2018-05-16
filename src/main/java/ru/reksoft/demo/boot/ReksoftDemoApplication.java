package ru.reksoft.demo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "ru.reksoft.demo.config",
        "ru.reksoft.demo.controller"
})
public class ReksoftDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReksoftDemoApplication.class, args);
    }
}
