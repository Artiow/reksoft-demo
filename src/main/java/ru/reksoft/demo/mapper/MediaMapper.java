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
import ru.reksoft.demo.mapper.generic.AbstractVersionedMapper;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;
import ru.reksoft.demo.mapper.manual.PictureURIMapper;

@Mapper(uses = {JavaTimeMapper.class, PictureURIMapper.class}, componentModel = "spring")
public interface MediaMapper extends AbstractVersionedMapper<MediaEntity, MediaDTO> {

    @Mappings({
            @Mapping(target = "type", source = "type.name"),
            @Mapping(target = "album", source = "album.name"),
            @Mapping(target = "label", source = "album.label.name"),
            @Mapping(target = "singer", source = "album.singer.name"),
            @Mapping(target = "pictureURI", source = "album.picture.id"),
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
        AbstractVersionedMapper.super.merge(acceptor, donor);

        acceptor.setPrice(donor.getPrice());

        acceptor.setType(donor.getType());

        check(acceptor.getAlbum(), donor.getAlbum());
        acceptor.setAlbum(donor.getAlbum());

        acceptor.setBaskets(donor.getBaskets());
        acceptor.setOrders(donor.getOrders());

        return acceptor;
    }
}
