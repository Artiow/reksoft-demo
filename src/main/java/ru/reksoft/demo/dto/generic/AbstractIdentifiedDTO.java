package ru.reksoft.demo.dto.generic;

public abstract class AbstractIdentifiedDTO implements DataTransferObject {

    private Integer id;


    public Integer getId() {
        return id;
    }

    public AbstractIdentifiedDTO setId(Integer id) {
        this.id = id;
        return this;
    }
}
