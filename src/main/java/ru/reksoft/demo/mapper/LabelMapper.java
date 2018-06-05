package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.LabelEntity;
import ru.reksoft.demo.dto.LabelDTO;

@Mapper
public interface LabelMapper extends AbstractEntityMapper<LabelEntity, LabelDTO> {

}
