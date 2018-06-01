package ru.reksoft.demo.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MediaSearchType {
    BY_SINGER("bySinger"),
    BY_LABEL("byLabel"),
    BY_ALBUM("byAlbum");

    @JsonValue
    private String code;

    MediaSearchType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
