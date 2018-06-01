package ru.reksoft.demo.dto;

import java.util.Collection;

public class MediaFilterDTO extends PageDividerDTO {

    private String searchType;
    private String searchString;

    private Collection<String> typeCodes;
    private Collection<String> genreCodes;


    public String getSearchType() {
        return searchType;
    }

    public MediaFilterDTO setSearchType(String searchType) {
        this.searchType = searchType;
        return this;
    }

    public String getSearchString() {
        return searchString;
    }

    public MediaFilterDTO setSearchString(String searchString) {
        this.searchString = searchString;
        return this;
    }

    public Collection<String> getTypeCodes() {
        return typeCodes;
    }

    public MediaFilterDTO setTypeCodes(Collection<String> typeCodes) {
        this.typeCodes = typeCodes;
        return this;
    }

    public Collection<String> getGenreCodes() {
        return genreCodes;
    }

    public MediaFilterDTO setGenreCodes(Collection<String> genreCodes) {
        this.genreCodes = genreCodes;
        return this;
    }
}
