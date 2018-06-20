package ru.reksoft.demo.dto.generic;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public abstract class AbstractIdentifiedDTO implements DataTransferObject {

//    todo: add validation!
//    @NotNull(message = "id must not be null!")
//    @Min(value = 1, message = "id must not less then one!")
    private Integer id;


    public Integer getId() {
        return id;
    }

    public AbstractIdentifiedDTO setId(Integer id) {
        this.id = id;
        return this;
    }
}
