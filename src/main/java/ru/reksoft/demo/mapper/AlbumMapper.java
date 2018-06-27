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

import java.util.Collection;

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

    default AlbumEntity merge(AlbumEntity acceptor, AlbumEntity donor) {
        acceptor.setName(donor.getName());
        acceptor.setDescription(donor.getDescription());
        acceptor.setReleaseYear(donor.getReleaseYear());

        acceptor.setPicture(donor.getPicture());
        acceptor.setLabel(donor.getLabel());
        acceptor.setSinger(donor.getSinger());

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
