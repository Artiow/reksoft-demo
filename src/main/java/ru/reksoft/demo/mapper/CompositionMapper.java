package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.dto.CompositionDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

@Mapper(uses = JavaTimeMapper.class, componentModel = "spring")
public interface CompositionMapper extends AbstractMapper<CompositionEntity, CompositionDTO> {

    @Mapping(target = "id", ignore = true)
    CompositionEntity toEntity(CompositionDTO dto);
}
