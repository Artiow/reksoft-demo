package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

public class LabelDTO extends AbstractIdentifiedDTO {

    private String name;


    public LabelDTO setId(Integer id) {
        return (LabelDTO) super.setId(id);
    }


    public String getName() {
        return name;
    }

    public LabelDTO setName(String name) {
        this.name = name;
        return this;
    }
}
