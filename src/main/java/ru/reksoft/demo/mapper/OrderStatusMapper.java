package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.OrderStatusEntity;
import ru.reksoft.demo.dto.OrderStatusDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;

@Mapper
public interface OrderStatusMapper extends AbstractMapper<OrderStatusEntity, OrderStatusDTO> {

}
