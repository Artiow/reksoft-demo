package ru.reksoft.demo.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.net.URI;

public class ResourceLocationBuilder {

    public static URI buildURI(@NotNull String prefix) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(prefix).build().toUri();
    }

    public static URI buildURI(@NotNull HttpServletRequest request) {
        return URI.create(build(request));
    }

    public static URI buildURI(@NotNull String prefix, @NotNull Integer identifier) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(prefix).path("/" + identifier).build().toUri();
    }

    public static URI buildURI(@NotNull HttpServletRequest request, @NotNull Integer identifier) {
        return URI.create(build(request, identifier));
    }

    public static String build(@NotNull String prefix) {
        return buildURI(prefix).toString();
    }

    public static String build(@NotNull HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    public static String build(@NotNull String prefix, @NotNull Integer identifier) {
        return buildURI(prefix, identifier).toString();
    }

    public static String build(@NotNull HttpServletRequest request, @NotNull Integer identifier) {
        return request.getRequestURL().append('/').append(identifier).toString();
    }
}
