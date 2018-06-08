package ru.reksoft.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.reksoft.demo.dto.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public abstract class AbstractService {

    public static class StringSearcher<T> extends PageDivider implements Specification<T> {

        private String searchString;
        private SingularAttribute<? super T, String> attribute;


        public StringSearcher(StringSearcherDTO dto, SingularAttribute<? super T, String> attribute) {
            super(dto);

            this.attribute = attribute;
            configureSearchByString(dto);
        }

        private void configureSearchByString(StringSearcherDTO dto) {
            String searchString = dto.getSearchString();
            if ((searchString != null) && (!searchString.equals(""))) {
                this.searchString = searchString.trim().toLowerCase() + "%";
            }
        }


        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if (searchString != null) {
                return searchByStringPredicate(root, query, cb);
            } else {
                return null;
            }
        }

        private Predicate searchByStringPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(cb.lower(root.get(attribute)), searchString);
        }
    }

    public static class PageDivider {

        private Integer pageSize;
        private Integer pageNum;

        public PageDivider(PageDividerDTO dto) {
            Integer pageSize = dto.getPageSize();
            Integer pageNum = dto.getPageNum();

            Boolean unpaged = ((pageSize == null) && (pageNum == null));
            if ((pageSize != null) && (pageNum != null)) {
                unpaged = ((pageSize == 0) && (pageNum == 0));
            }

            if (!unpaged) {
                if (pageSize == null) {
                    throw new IllegalArgumentException("Page size must not be null! If you want disable the pagination, set \"pageNum\" to null also.");
                } else if (pageNum == null) {
                    throw new IllegalArgumentException("Page index must not be null! If you want disable the pagination, set \"pageSize\" to null also.");
                } else if (pageSize < 1) {
                    throw new IllegalArgumentException("Page size must not be less than one! ");
                } else if (pageNum < 0) {
                    throw new IllegalArgumentException("Page index must not be less than zero!");
                }

                this.pageSize = pageSize;
                this.pageNum = pageNum;
            }
        }

        public Pageable getPageRequest() {
            if (pageSize == null) {
                return Pageable.unpaged();
            }

            return PageRequest.of(pageNum, pageSize);
        }
    }
}
