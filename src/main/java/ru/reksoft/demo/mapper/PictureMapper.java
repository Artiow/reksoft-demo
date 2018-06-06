package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.PictureEntity;
import ru.reksoft.demo.dto.PictureDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class)
public interface PictureMapper extends AbstractEntityMapper<PictureEntity, PictureDTO> {

}
