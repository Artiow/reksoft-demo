package ru.reksoft.demo.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.reksoft.demo.domain.MediaEntity;
import ru.reksoft.demo.dto.MediaFilterDTO;
import ru.reksoft.demo.dto.MediaTypeDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class MediaFilter implements Specification<MediaEntity> {

    private Integer pageNum;
    private Integer pageSize;

    private Collection<MediaTypeDTO> types;


    public MediaFilter(MediaFilterDTO dto) {
        pageNum = dto.getPageNum();
        pageSize = dto.getPageSize();
    }


    public Pageable getPageRequest() {
        if ((pageNum == null) || (pageNum < 0) || (pageSize == null) || (pageSize < 1)) return Pageable.unpaged();
        return PageRequest.of(pageNum, pageSize);
    }

    @Override
    public Predicate toPredicate(Root<MediaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return null;
    }
}
