package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.PictureEntity;
import ru.reksoft.demo.dto.PictureDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;
import ru.reksoft.demo.mapper.manual.uri.PictureURIMapper;

@Mapper(uses = { JavaTimeMapper.class, PictureURIMapper.class}, componentModel = "spring")
public interface PictureMapper extends AbstractMapper<PictureEntity, PictureDTO> {

    @Mapping(target = "uri", source = "id")
    PictureDTO toDTO(PictureEntity entity);
}
