package ru.reksoft.demo.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.reksoft.demo.domain.MediaEntity;
import ru.reksoft.demo.domain.MediaEntity_;
import ru.reksoft.demo.domain.MediaTypeEntity;
import ru.reksoft.demo.domain.MediaTypeEntity_;
import ru.reksoft.demo.dto.MediaFilterDTO;
import ru.reksoft.demo.dto.MediaTypeDTO;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class MediaFilter implements Specification<MediaEntity> {

    private Integer pageNum;
    private Integer pageSize;

    private Collection<String> typeCodes;


    public MediaFilter(MediaFilterDTO dto) {
        Integer pageNum = dto.getPageNum();
        Integer pageSize = dto.getPageSize();

        if (pageSize == null)
            throw new IllegalArgumentException("Page size must not be null!");
        if ((pageSize > 0) && (pageNum == null))
            throw new IllegalArgumentException("Page index must not be null!");
        if ((pageSize > 0) && (pageNum < 0))
            throw new IllegalArgumentException("Page index must not be less than zero!");

        this.pageSize = pageSize;
        this.pageNum = pageNum;

        Collection<String> typeCodes = dto.getTypeCodes();
        if ((typeCodes != null) && (!typeCodes.isEmpty())) this.typeCodes = new ArrayList<>(typeCodes);
    }

    public Pageable getPageRequest() {
        if (pageSize == 0) return Pageable.unpaged();
        return PageRequest.of(pageNum, pageSize);
    }

    @Override
    public Predicate toPredicate(Root<MediaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (typeCodes != null) { //TODO: what if null?
            Join<MediaEntity, MediaTypeEntity> join = root.join(MediaEntity_.type);
            return join.get(MediaTypeEntity_.code).in(typeCodes);
        }

        return null;
    }
}
