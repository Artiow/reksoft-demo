package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.dto.UserDTO;

@Mapper
public interface UserMapper extends AbstractEntityMapper<UserEntity, UserDTO> {

}
