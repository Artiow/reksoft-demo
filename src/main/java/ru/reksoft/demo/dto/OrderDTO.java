package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;
import ru.reksoft.demo.dto.generic.DataTransferObject;
import ru.reksoft.demo.dto.shortcut.MediaShortDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 90, groups = FieldCheck.class)
    private String address;

    private Integer cost;
    private LocalDateTime orderedTime;

    private OrderStatusDTO status;

    private Integer numberOfElements;
    private List<OrderItemDTO> content;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public OrderDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public OrderStatusDTO getStatus() {
        return status;
    }

    public OrderDTO setStatus(OrderStatusDTO status) {
        this.status = status;
        return this;
    }

    public Integer getCost() {
        return cost;
    }

    public OrderDTO setCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public OrderDTO setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
        return this;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public OrderDTO setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public List<OrderItemDTO> getContent() {
        return content;
    }

    public OrderDTO setContent(List<OrderItemDTO> content) {
        this.content = content;
        return this;
    }


    public interface IdCheck {

    }

    public interface FieldCheck {

    }


    public static class OrderItemDTO implements DataTransferObject {

        private MediaShortDTO media;
        private Integer count;


        public MediaShortDTO getMedia() {
            return media;
        }

        public OrderItemDTO setMedia(MediaShortDTO media) {
            this.media = media;
            return this;
        }

        public Integer getCount() {
            return count;
        }

        public OrderItemDTO setCount(Integer count) {
            this.count = count;
            return this;
        }
    }
}
