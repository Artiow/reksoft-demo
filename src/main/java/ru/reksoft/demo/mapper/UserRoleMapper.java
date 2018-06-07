package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.UserRoleEntity;
import ru.reksoft.demo.dto.UserRoleDTO;

@Mapper
public interface UserRoleMapper extends AbstractEntityMapper<UserRoleEntity, UserRoleDTO> {

}
