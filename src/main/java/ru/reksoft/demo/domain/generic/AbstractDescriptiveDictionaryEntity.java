package ru.reksoft.demo.domain.generic;

public abstract class AbstractDescriptiveDictionaryEntity extends AbstractDictionaryEntity {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
