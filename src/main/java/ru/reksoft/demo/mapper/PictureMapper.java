package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.PictureEntity;
import ru.reksoft.demo.dto.PictureDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;
import ru.reksoft.demo.mapper.manual.PictureURIMapper;

@Mapper(uses = { JavaTimeMapper.class, PictureURIMapper.class}, componentModel = "spring")
public interface PictureMapper extends AbstractEntityMapper<PictureEntity, PictureDTO> {

    @Mapping(target = "uri", source = "id")
    PictureDTO toDTO(PictureEntity entity);
}
