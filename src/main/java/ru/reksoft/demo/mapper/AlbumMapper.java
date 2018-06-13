package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.AlbumShortDTO;
import ru.reksoft.demo.dto.CompositionDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface AlbumMapper extends AbstractEntityMapper<AlbumEntity, AlbumDTO> {

    @Mappings({
            @Mapping(source = "label.name", target = "label"),
            @Mapping(source = "singer.name", target = "singer")
    })
    AlbumShortDTO toShortDTO(AlbumEntity entity);

    @Mapping(target = "id", ignore = true)
    AlbumEntity toEntity(AlbumDTO dto);

    @Mapping(target = "id", ignore = true)
    CompositionEntity toEntity(CompositionDTO dto);
}
