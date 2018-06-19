package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractDictionaryDTO;

public class GenreDTO extends AbstractDictionaryDTO implements Comparable<GenreDTO> {

    public GenreDTO setId(Integer id) {
        return (GenreDTO) super.setId(id);
    }

    public GenreDTO setCode(String code) {
        return (GenreDTO) super.setCode(code);
    }

    public GenreDTO setName(String name) {
        return (GenreDTO) super.setName(name);
    }


    @Override
    public int compareTo(GenreDTO o) {
        return getCode().compareTo(o.getCode());
    }
}
