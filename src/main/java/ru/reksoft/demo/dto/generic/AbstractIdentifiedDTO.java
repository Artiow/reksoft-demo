package ru.reksoft.demo.dto.generic;

import ru.reksoft.demo.dto.generic.checkgroups.IdentifierCheck;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public abstract class AbstractIdentifiedDTO implements DataTransferObject {

    @NotNull(message = "id must not be null!", groups = IdentifierCheck.class)
    @Min(value = 1, message = "id must not less then one!", groups = IdentifierCheck.class)
    private Integer id;


    public Integer getId() {
        return id;
    }

    public AbstractIdentifiedDTO setId(Integer id) {
        this.id = id;
        return this;
    }
}
