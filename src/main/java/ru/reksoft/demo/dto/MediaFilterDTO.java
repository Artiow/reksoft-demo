package ru.reksoft.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class MediaFilterDTO {

    private Integer pageSize;
    private Integer pageNum;

    private String searchType;
    private String searchString;

    private Collection<String> typeCodes;


    public Integer getPageSize() {
        return pageSize;
    }

    public MediaFilterDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public MediaFilterDTO setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

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
}
