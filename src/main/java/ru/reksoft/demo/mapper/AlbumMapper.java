package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.reksoft.demo.domain.*;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

import java.util.Collection;

@Mapper(uses = { JavaTimeMapper.class, CompositionMapper.class }, componentModel = "spring")
public interface AlbumMapper extends AbstractEntityMapper<AlbumEntity, AlbumDTO> {

    @Mappings({
            @Mapping(source = "label.name", target = "label"),
            @Mapping(source = "singer.name", target = "singer")
    })
    AlbumShortDTO toShortDTO(AlbumEntity entity);

    @Mapping(target = "id", ignore = true)
    AlbumEntity toEntity(AlbumDTO dto);

    @Mapping(target = "name", ignore = true)
    LabelEntity toEntity(LabelDTO dto);

    @Mapping(target = "name", ignore = true)
    SingerEntity toEntity(SingerDTO dto);

    @Mappings({
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "width", ignore = true),
            @Mapping(target = "height", ignore = true),
            @Mapping(target = "uploaded", ignore = true)
    })
    PictureEntity toEntity(PictureDTO dto);

    @Mappings({
            @Mapping(target = "code", ignore = true),
            @Mapping(target = "name", ignore = true)
    })
    GenreEntity toEntity(GenreDTO dto);

    default AlbumEntity merge(AlbumEntity acceptor, AlbumEntity donor) {
        acceptor.setName(donor.getName());
        acceptor.setDescription(donor.getDescription());
        acceptor.setReleaseYear(donor.getReleaseYear());

        acceptor.setPicture(donor.getPicture());
        acceptor.setLabel(donor.getLabel());
        acceptor.setSinger(donor.getSinger());

        acceptor.setMedia(donor.getMedia());

        Collection<CompositionEntity> compositions = acceptor.getCompositions();
        if (!compositions.isEmpty()) {
            compositions.clear();
        }
        for (CompositionEntity composition: donor.getCompositions()) {
            composition.setAlbum(acceptor);
            compositions.add(composition);
        }

        acceptor.setGenres(donor.getGenres());

        return acceptor;
    }
}
