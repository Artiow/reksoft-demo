package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

public class SingerDTO extends AbstractIdentifiedDTO {

    private String name;


    public MediaTypeDTO setId(Integer id) {
        return (MediaTypeDTO) super.setId(id);
    }


    public String getName() {
        return name;
    }

    public SingerDTO setName(String name) {
        this.name = name;
        return this;
    }
}
