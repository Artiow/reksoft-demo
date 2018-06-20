package ru.reksoft.demo.util;

public class ResourceLocationBuilder {

    public static String build(StringBuffer requestURL, String identifier) {
        StringBuilder builder = new StringBuilder(requestURL);
        return builder.replace(builder.lastIndexOf("/") + 1, builder.length(), identifier).toString();
    }
}
