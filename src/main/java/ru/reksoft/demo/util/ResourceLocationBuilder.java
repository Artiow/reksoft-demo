package ru.reksoft.demo.util;

import javax.validation.constraints.NotNull;

public class ResourceLocationBuilder {

    public static String build(@NotNull String requestURI, @NotNull String identifier) {
        StringBuilder builder = new StringBuilder(requestURI);
        return builder.replace(builder.lastIndexOf("/") + 1, builder.length(), identifier).toString();
    }
}
