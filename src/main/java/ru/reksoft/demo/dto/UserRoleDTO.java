package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractDescriptiveDictionaryDTO;

public class UserRoleDTO  extends AbstractDescriptiveDictionaryDTO {

    public UserRoleDTO setId(Integer id) {
        return (UserRoleDTO) super.setId(id);
    }

    public UserRoleDTO setCode(String code) {
        return (UserRoleDTO) super.setCode(code);
    }

    public UserRoleDTO setName(String name) {
        return (UserRoleDTO) super.setName(name);
    }

    public UserRoleDTO setDescription(String description) {
        return (UserRoleDTO) super.setDescription(description);
    }
}
