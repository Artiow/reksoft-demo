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
            if (pageSize != null) {
                if (pageSize < 1) {
                    throw new IllegalArgumentException("Page index must not be less than one!");
                }

                Integer pageNum = dto.getPageNum();
                if (pageNum == null) {
                    throw new IllegalArgumentException("Page index must not be null!");
                }
                if (pageNum < 0) {
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
