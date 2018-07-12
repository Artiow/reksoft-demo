package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.reksoft.demo.domain.MediaOrderEntity;
import ru.reksoft.demo.domain.OrderEntity;
import ru.reksoft.demo.domain.OrderStatusEntity;
import ru.reksoft.demo.dto.OrderDTO;
import ru.reksoft.demo.dto.OrderStatusDTO;
import ru.reksoft.demo.mapper.generic.AbstractMapper;
import ru.reksoft.demo.mapper.manual.JavaTimeMapper;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {JavaTimeMapper.class, MediaMapper.class}, componentModel = "spring")
public interface OrderMapper extends AbstractMapper<OrderEntity, OrderDTO> {

    @Mappings({
            @Mapping(target = "content", source = "media"),
            @Mapping(target = "numberOfElements", source = "media")
    })
    OrderDTO toDTO(OrderEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "cost", ignore = true),
            @Mapping(target = "orderedTime", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "media", ignore = true)
    })
    OrderEntity toEntity(OrderDTO dto);

    @Mappings({
            @Mapping(target = "code", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "description", ignore = true)
    })
    OrderStatusEntity toEntity(OrderStatusDTO dto);

    OrderDTO.OrderItemDTO toItemDTO(MediaOrderEntity entity);

    List<OrderDTO.OrderItemDTO> toItemDTO(Collection<MediaOrderEntity> entityCollection);

    default Integer toSize(Collection<MediaOrderEntity> entityCollection) {
        return entityCollection.size();
    }
}
