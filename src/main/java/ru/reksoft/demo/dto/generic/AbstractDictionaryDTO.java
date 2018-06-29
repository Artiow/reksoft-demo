package ru.reksoft.demo.dto.generic;

public abstract class AbstractDictionaryDTO<T extends AbstractDictionaryDTO> extends AbstractIdentifiedDTO {

    public abstract String getCode();

    public abstract T setCode(String code);

    public abstract String getName();

    public abstract T setName(String name);
}
