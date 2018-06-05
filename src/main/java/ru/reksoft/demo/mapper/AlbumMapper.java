package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class)
public interface AlbumMapper extends AbstractEntityMapper<AlbumEntity, AlbumDTO> {

}
