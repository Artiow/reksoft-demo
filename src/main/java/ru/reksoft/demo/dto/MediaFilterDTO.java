package ru.reksoft.demo.dto;

import java.util.Collection;

public class MediaFilterDTO {

    private Integer pageSize;
    private Integer pageNum;

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

    public Collection<String> getTypeCodes() {
        return typeCodes;
    }

    public MediaFilterDTO setTypeCodes(Collection<String> typeCodes) {
        this.typeCodes = typeCodes;
        return this;
    }
}
