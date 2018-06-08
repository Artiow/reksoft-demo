package ru.reksoft.demo.dto.filters;

import ru.reksoft.demo.dto.pagination.PageDividerDTO;

public class StringSearcherDTO extends PageDividerDTO {

    private String searchString;


    public String getSearchString() {
        return searchString;
    }

    public StringSearcherDTO setSearchString(String searchString) {
        this.searchString = searchString;
        return this;
    }
}
