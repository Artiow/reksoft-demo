package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.UserRoleEntity;
import ru.reksoft.demo.dto.UserRoleDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;

@Mapper
public interface UserRoleMapper extends AbstractMapper<UserRoleEntity, UserRoleDTO> {

}
