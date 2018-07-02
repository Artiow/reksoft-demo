package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.dto.UserDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;

@Mapper
public interface UserMapper extends AbstractMapper<UserEntity, UserDTO> {

}
