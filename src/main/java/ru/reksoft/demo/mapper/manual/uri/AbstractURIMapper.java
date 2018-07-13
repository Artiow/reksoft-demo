package ru.reksoft.demo.mapper.manual.uri;

import ru.reksoft.demo.util.ResourceLocationBuilder;

import java.net.URI;

public abstract class AbstractURIMapper {

    protected URI toURI(String apiPath, Integer identifier) {
        return ResourceLocationBuilder.buildURI(apiPath, identifier);
    }
}
