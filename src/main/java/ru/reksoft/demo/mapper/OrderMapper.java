package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.reksoft.demo.domain.OrderEntity;
import ru.reksoft.demo.domain.OrderStatusEntity;
import ru.reksoft.demo.domain.OrderedMediaEntity;
import ru.reksoft.demo.dto.OrderDTO;
import ru.reksoft.demo.dto.OrderStatusDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {JavaTimeMapper.class, MediaMapper.class}, componentModel = "spring")
public interface OrderMapper extends AbstractMapper<OrderEntity, OrderDTO> {

    @Mappings({
            @Mapping(target = "content", source = "medias"),
            @Mapping(target = "numberOfElements", source = "medias")
    })
    OrderDTO toDTO(OrderEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "cost", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "medias", ignore = true)
    })
    OrderEntity toEntity(OrderDTO dto);

    @Mappings({
            @Mapping(target = "code", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "description", ignore = true)
    })
    OrderStatusEntity toEntity(OrderStatusDTO dto);

    OrderDTO.OrderItemDTO toItemDTO(OrderedMediaEntity entity);

    List<OrderDTO.OrderItemDTO> toItemDTO(Collection<OrderedMediaEntity> entityCollection);

    default Integer toSize(Collection<OrderedMediaEntity> entityCollection) {
        return entityCollection.size();
    }
}
