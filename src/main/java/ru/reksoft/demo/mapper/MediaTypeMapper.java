package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.MediaTypeEntity;
import ru.reksoft.demo.dto.MediaTypeDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;

@Mapper
public interface MediaTypeMapper extends AbstractMapper<MediaTypeEntity, MediaTypeDTO> {

}
