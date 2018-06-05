package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.reksoft.demo.domain.LabelEntity;
import ru.reksoft.demo.dto.LabelDTO;

@Mapper
public interface LabelMapper {
    LabelMapper INSTANCE = Mappers.getMapper(LabelMapper.class);

    LabelDTO toDTO(LabelEntity entity);

    LabelEntity toEntity(LabelDTO dto);
}
