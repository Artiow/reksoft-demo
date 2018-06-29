package ru.reksoft.demo.dto.generic;

public abstract class AbstractDescriptiveDictionaryDTO<T extends AbstractDescriptiveDictionaryDTO> extends AbstractDictionaryDTO {

    public abstract String getDescription();

    public abstract T setDescription(String description);
}
