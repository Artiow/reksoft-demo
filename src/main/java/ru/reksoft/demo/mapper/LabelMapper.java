package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.LabelEntity;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface LabelMapper extends AbstractEntityMapper<LabelEntity, LabelDTO> {

}
