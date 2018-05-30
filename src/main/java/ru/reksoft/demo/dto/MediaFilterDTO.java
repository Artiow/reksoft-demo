package ru.reksoft.demo.dto;

import java.util.Collection;

public class MediaFilterDTO {

    private Integer pageNum;
    private Integer pageSize;

    private Collection<MediaTypeDTO> types;


    public Integer getPageNum() {
        return pageNum;
    }

    public MediaFilterDTO setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public MediaFilterDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Collection<MediaTypeDTO> getTypes() {
        return types;
    }

    public MediaFilterDTO setTypes(Collection<MediaTypeDTO> types) {
        this.types = types;
        return this;
    }
}
