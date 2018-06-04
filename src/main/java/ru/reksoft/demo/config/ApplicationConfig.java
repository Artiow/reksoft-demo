package ru.reksoft.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig implements WebMvcConfigurer {

    /**
     * Swagger config
     *
     * @return docket bean
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)

                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.reksoft.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Resource handlers config
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

}
