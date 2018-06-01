package ru.reksoft.demo.dto;

public class PageDividerDTO {

    private Integer pageSize;
    private Integer pageNum;


    public Integer getPageSize() {
        return pageSize;
    }

    public PageDividerDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public PageDividerDTO setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }
}
