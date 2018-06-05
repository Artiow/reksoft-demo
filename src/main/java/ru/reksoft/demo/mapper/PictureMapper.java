package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.reksoft.demo.domain.PictureEntity;
import ru.reksoft.demo.dto.PictureDTO;
import ru.reksoft.demo.mapper.manual.LocalDateTimeMapper;

@Mapper(uses = LocalDateTimeMapper.class)
public interface PictureMapper extends AbstractEntityMapper<PictureEntity, PictureDTO> {

    PictureMapper INSTANCE = Mappers.getMapper(PictureMapper.class);
}
