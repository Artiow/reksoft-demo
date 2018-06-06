package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.reksoft.demo.domain.MediaEntity;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.MediaShortDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface MediaMapper extends AbstractEntityMapper<MediaEntity, MediaDTO> {

    @Mappings({
            @Mapping(source = "type.name", target = "type"),
            @Mapping(source = "album.name", target = "album"),
            @Mapping(source = "album.label.name", target = "label"),
            @Mapping(source = "album.singer.name", target = "singer")
    })
    MediaShortDTO toShortDTO(MediaEntity entity);
}
