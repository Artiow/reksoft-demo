package ru.reksoft.demo.dto.generic;

public abstract class AbstractDictionaryDTO extends AbstractIdentifiedDTO {

    private String code;
    private String name;


    public String getCode() {
        return code;
    }

    public AbstractDictionaryDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public AbstractDictionaryDTO setName(String name) {
        this.name = name;
        return this;
    }
}
