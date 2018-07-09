package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.LabelEntity;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.mapper.generic.AbstractVersionedMapper;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface LabelMapper extends AbstractVersionedMapper<LabelEntity, LabelDTO> {

    @Mapping(target = "id", ignore = true)
    LabelEntity toEntity(LabelDTO dto);

    default LabelEntity merge(LabelEntity acceptor, LabelEntity donor) {
        AbstractVersionedMapper.super.merge(acceptor, donor);

        acceptor.setName(donor.getName());
        acceptor.setAlbums(donor.getAlbums());

        return acceptor;
    }
}
