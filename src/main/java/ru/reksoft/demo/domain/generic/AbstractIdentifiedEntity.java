package ru.reksoft.demo.domain.generic;

public abstract class AbstractIdentifiedEntity implements DomainObject {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
