package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.GenreEntity;
import ru.reksoft.demo.dto.GenreDTO;

@Mapper
public interface GenreMapper extends AbstractEntityMapper<GenreEntity, GenreDTO>, AbstractEntitySortedMapper<GenreEntity, GenreDTO> {

}
