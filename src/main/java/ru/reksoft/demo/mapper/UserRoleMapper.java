package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.UserRoleEntity;
import ru.reksoft.demo.dto.UserRoleDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper extends AbstractMapper<UserRoleEntity, UserRoleDTO> {

    @Mapping(target = "id", ignore = true)
    UserRoleEntity toEntity(UserRoleDTO dto);
}
