package ru.reksoft.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.net.URI;

public class ResourceLocationBuilder {

    public static String build(@NotNull HttpServletRequest request, @NotNull Integer identifier) {
        return request.getRequestURL().append('/').append(identifier.toString()).toString();
    }

    public static URI buildURI(@NotNull HttpServletRequest request, @NotNull Integer identifier) {
        return URI.create(build(request, identifier));
    }
}
