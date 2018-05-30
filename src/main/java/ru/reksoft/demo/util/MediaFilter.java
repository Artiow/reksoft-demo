package ru.reksoft.demo.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.reksoft.demo.domain.MediaEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MediaFilter implements Specification<MediaEntity> {

    private Boolean pagination;
    private Integer pageNum;
    private Integer pageSize;


    public MediaFilter() {
        pagination = false;
    }

    public MediaFilter(Integer pageNum, Integer pageSize) {
        pagination = true;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }


    public Boolean isPagination() {
        return pagination;
    }

    public MediaFilter setPagination(Boolean pagination) {
        this.pagination = pagination;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public MediaFilter setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public MediaFilter setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Pageable getPageRequest() {
        if (!pagination) return Pageable.unpaged();
        else return PageRequest.of(pageNum, pageSize);
    }


    @Override
    public Predicate toPredicate(Root<MediaEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
