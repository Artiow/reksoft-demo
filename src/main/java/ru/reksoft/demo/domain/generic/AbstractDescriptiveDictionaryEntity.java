package ru.reksoft.demo.domain.generic;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDescriptiveDictionaryEntity extends AbstractDictionaryEntity {

    private String description;


    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
