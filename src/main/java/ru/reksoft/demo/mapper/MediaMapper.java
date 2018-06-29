package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.MediaEntity;
import ru.reksoft.demo.domain.MediaTypeEntity;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.MediaShortDTO;
import ru.reksoft.demo.dto.MediaTypeDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

import java.awt.*;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface MediaMapper extends AbstractEntityMapper<MediaEntity, MediaDTO> {

    @Mappings({
            @Mapping(source = "type.name", target = "type"),
            @Mapping(source = "album.name", target = "album"),
            @Mapping(source = "album.picture", target = "picture"),
            @Mapping(source = "album.label.name", target = "label"),
            @Mapping(source = "album.singer.name", target = "singer")
    })
    MediaShortDTO toShortDTO(MediaEntity entity);

    @Mapping(target = "id", ignore = true)
    MediaEntity toEntity(MediaDTO dto);

    @Mappings({
            @Mapping(target = "code", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "description", ignore = true)
    })
    MediaTypeEntity toEntity(MediaTypeDTO dto);

    @Mappings({
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "releaseYear", ignore = true),
            @Mapping(target = "label", ignore = true),
            @Mapping(target = "singer", ignore = true),
            @Mapping(target = "picture", ignore = true),
            @Mapping(target = "compositions", ignore = true),
            @Mapping(target = "genres", ignore = true)
    })
    AlbumEntity toEntity(AlbumDTO dto);

    default MediaEntity merge(MediaEntity acceptor, MediaEntity donor) {
        acceptor.setPrice(donor.getPrice());

        acceptor.setType(donor.getType());
        acceptor.setAlbum(donor.getAlbum());

        acceptor.setBaskets(donor.getBaskets());
        acceptor.setOrders(donor.getOrders());

        return acceptor;
    }
}
