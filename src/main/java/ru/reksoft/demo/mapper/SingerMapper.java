package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.SingerEntity;
import ru.reksoft.demo.dto.SingerDTO;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface SingerMapper extends AbstractEntityMapper<SingerEntity, SingerDTO> {

    @Mapping(target = "id", ignore = true)
    SingerEntity toEntity(SingerDTO dto);
}
