package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.SingerEntity;
import ru.reksoft.demo.dto.SingerDTO;

@Mapper
public interface SingerMapper extends AbstractEntityMapper<SingerEntity, SingerDTO> {

}
