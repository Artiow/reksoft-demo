package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.dto.UserDTO;
import ru.reksoft.demo.dto.security.LoginDTO;
import ru.reksoft.demo.dto.shortcut.UserShortDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;
import ru.reksoft.demo.mapper.manual.uri.UserURIMapper;

@Mapper(uses = {UserURIMapper.class}, componentModel = "spring")
public interface UserMapper extends AbstractMapper<UserEntity, UserDTO> {

    @Mapping(target = "userURI", source = "id")
    LoginDTO toAuthDTO(UserEntity entity);

    UserShortDTO toShortDTO(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDTO dto);
}
