package ru.reksoft.demo.mapper.manual;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class PictureURIMapper {

    @Value("${api-path.picture}")
    private String apiPath;

    public URI toURI(Integer id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(apiPath).path("/" + id).build().toUri();
    }
}
