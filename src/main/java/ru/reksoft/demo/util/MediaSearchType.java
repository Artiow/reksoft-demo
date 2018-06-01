package ru.reksoft.demo.util;

public enum MediaSearchType {
    BY_SINGER("bySinger"),
    BY_LABEL("byLabel"),
    BY_ALBUM("byAlbum");

    private String code;

    MediaSearchType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static MediaSearchType getSearchType(String code) {
        for(MediaSearchType v : values()) if(v.code.equalsIgnoreCase(code)) return v;
        throw new IllegalArgumentException();
    }
}
