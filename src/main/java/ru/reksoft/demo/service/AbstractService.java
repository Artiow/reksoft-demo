package ru.reksoft.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.reksoft.demo.dto.PageDividerDTO;

public abstract class AbstractService {
    public static class PageDivider {

        private Integer pageSize;
        private Integer pageNum;


        public PageDivider() {
            this.pageSize = null;
            this.pageNum = null;
        }

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
