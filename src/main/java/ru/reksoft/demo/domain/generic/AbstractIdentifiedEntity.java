package ru.reksoft.demo.domain.generic;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractIdentifiedEntity implements DomainObject {

    private Integer id;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
