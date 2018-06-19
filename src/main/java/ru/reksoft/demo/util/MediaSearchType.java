package ru.reksoft.demo.util;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.beans.factory.annotation.Value;

public enum MediaSearchType {
    BY_SINGER("bySinger"),
    BY_LABEL("byLabel"),
    BY_ALBUM("byAlbum");

    @JsonValue
    private String value;

    MediaSearchType(String value) {
        this.value = value;
    }

    public static MediaSearchType fromValue(String value) {
        for(MediaSearchType v : values()) {
            if(v.value.equalsIgnoreCase(value)) {
                return v;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return value;
    }
}
