package ru.reksoft.demo.dto.generic;

public abstract class AbstractDescriptiveDictionaryDTO extends AbstractDictionaryDTO {

    private String description;


    public String getDescription() {
        return description;
    }

    public AbstractDescriptiveDictionaryDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
