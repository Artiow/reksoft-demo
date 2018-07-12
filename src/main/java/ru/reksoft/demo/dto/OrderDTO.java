package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public OrderDTO setId(Integer id) {
        this.id = id;
        return this;
    }


    public interface IdCheck {

    }

    public interface FieldCheck {

    }
}
