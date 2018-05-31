package ru.reksoft.demo.dto;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageDTO<T> {

    private List<T> content;
    private Integer pageNumber;
    private Integer numberOfElements;
    private Integer totalElements;
    private Integer totalPages;


    public PageDTO(Page<T> page) {
        content = new ArrayList<>(page.getContent());
        pageNumber = page.getNumber();
        numberOfElements = page.getNumberOfElements();
        totalElements = (int) page.getTotalElements();
        totalPages = page.getTotalPages();
    }


    public List<T> getContent() {
        return content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
