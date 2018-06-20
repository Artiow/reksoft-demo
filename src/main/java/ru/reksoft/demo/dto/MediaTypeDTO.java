package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractDescriptiveDictionaryDTO;

public class MediaTypeDTO extends AbstractDescriptiveDictionaryDTO {

    public MediaTypeDTO setId(Integer id) {
        return (MediaTypeDTO) super.setId(id);
    }

    public MediaTypeDTO setCode(String code) {
        return (MediaTypeDTO) super.setCode(code);
    }

    public MediaTypeDTO setName(String name) {
        return (MediaTypeDTO) super.setName(name);
    }

    public MediaTypeDTO setDescription(String description) {
        return (MediaTypeDTO) super.setDescription(description);
    }
}
