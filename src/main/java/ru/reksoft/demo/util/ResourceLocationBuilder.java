package ru.reksoft.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

public class ResourceLocationBuilder {

    public static String build(@NotNull HttpServletRequest request, @NotNull Integer identifier) {
        return request.getRequestURL().append('/').append(identifier.toString()).toString();
    }
}
