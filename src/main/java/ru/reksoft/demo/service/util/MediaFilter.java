package ru.reksoft.demo.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.reksoft.demo.domain.MediaEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MediaFilter implements Specification<MediaEntity> {

    private Integer pageNum;
    private Integer pageSize;


    public MediaFilter() {

    }

    public MediaFilter(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
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


    public PageRequest getPageRequest() {
        return PageRequest.of(pageNum, pageSize);
    }

    @Override
    public Predicate toPredicate(Root<MediaEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
