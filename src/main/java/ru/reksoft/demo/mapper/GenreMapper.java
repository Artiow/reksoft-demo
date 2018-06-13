package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.GenreEntity;
import ru.reksoft.demo.dto.GenreDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface GenreMapper extends AbstractEntityMapper<GenreEntity, GenreDTO>, AbstractEntitySortedMapper<GenreEntity, GenreDTO> {

    @Mapping(target = "id", ignore = true)
    GenreEntity toEntity(GenreDTO dto);
}
