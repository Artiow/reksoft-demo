package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.MediaTypeEntity;
import ru.reksoft.demo.dto.MediaTypeDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface MediaTypeMapper extends AbstractMapper<MediaTypeEntity, MediaTypeDTO> {

}
