package ru.reksoft.demo.mapper;

import org.mapstruct.Mapper;
import ru.reksoft.demo.domain.CurrentBasketEntity;
import ru.reksoft.demo.dto.BasketDTO;

import java.util.Collection;
import java.util.List;

@Mapper(uses = MediaMapper.class, componentModel = "spring")
public interface BasketMapper {

    default BasketDTO toBasket(Collection<CurrentBasketEntity> entityCollection) {

        BasketDTO basketDTO = new BasketDTO();
        List<BasketDTO.BasketItemDTO> content = toItemDTO(entityCollection);
        if (content != null) {
            basketDTO.setContent(content);
            basketDTO.setNumberOfElements(content.size());
        } else {
            basketDTO.setContent(null);
            basketDTO.setNumberOfElements(0);
        }

        return basketDTO;
    }

    BasketDTO.BasketItemDTO toItemDTO(CurrentBasketEntity entity);

    List<BasketDTO.BasketItemDTO> toItemDTO(Collection<CurrentBasketEntity> entityCollection);
}
