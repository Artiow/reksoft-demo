package ru.reksoft.demo.dto.filters;

import ru.reksoft.demo.dto.pagination.PageDividerDTO;

public abstract class AbstractSearchFilterDTO extends PageDividerDTO {

    private String searchString;


    public String getSearchString() {
        return searchString;
    }

    public AbstractSearchFilterDTO setSearchString(String searchString) {
        this.searchString = searchString;
        return this;
    }
}
