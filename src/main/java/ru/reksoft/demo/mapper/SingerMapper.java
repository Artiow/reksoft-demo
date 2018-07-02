package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.SingerEntity;
import ru.reksoft.demo.dto.SingerDTO;
import ru.reksoft.demo.mapper.generic.AbstractVersionedMapper;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface SingerMapper extends AbstractVersionedMapper<SingerEntity, SingerDTO> {

    @Mapping(target = "id", ignore = true)
    SingerEntity toEntity(SingerDTO dto);

    default SingerEntity merge(SingerEntity acceptor, SingerEntity donor) {
        AbstractVersionedMapper.super.merge(acceptor, donor);

        acceptor.setName(donor.getName());
        acceptor.setAlbums(donor.getAlbums());

        return acceptor;
    }
}
