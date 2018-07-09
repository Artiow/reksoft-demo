package ru.reksoft.demo.mapper.manual.uri;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public abstract class AbstractURIMapper {

    protected URI toURI(String apiPath, Integer identifier) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(apiPath).path("/" + identifier).build().toUri();
    }
}
