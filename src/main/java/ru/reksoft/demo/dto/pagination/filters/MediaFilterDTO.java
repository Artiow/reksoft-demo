package ru.reksoft.demo.dto.pagination.filters;

import ru.reksoft.demo.util.MediaSearchType;

import java.util.Collection;

public class MediaFilterDTO extends StringSearcherDTO {

    private MediaSearchType searchType;
    private Collection<String> typeCodes;
    private Collection<String> genreCodes;


    public MediaSearchType getSearchType() {
        return searchType;
    }

    public MediaFilterDTO setSearchType(MediaSearchType searchType) {
        this.searchType = searchType;
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
