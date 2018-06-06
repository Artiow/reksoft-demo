package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.MediaTypeEntity;
import ru.reksoft.demo.dto.MediaTypeDTO;

@Mapper
public interface MediaTypeMapper extends AbstractEntityMapper<MediaTypeEntity, MediaTypeDTO> {

}
