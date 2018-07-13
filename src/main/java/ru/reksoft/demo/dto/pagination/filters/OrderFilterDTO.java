package ru.reksoft.demo.dto.pagination.filters;

import ru.reksoft.demo.dto.pagination.PageDividerDTO;

import java.util.Collection;

public class OrderFilterDTO extends PageDividerDTO {

    private Collection<String> statusCodes;


    public Collection<String> getStatusCodes() {
        return statusCodes;
    }

    public OrderFilterDTO setStatusCodes(Collection<String> statusCodes) {
        this.statusCodes = statusCodes;
        return this;
    }
}
