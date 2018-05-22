package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.MediaEntity;

public class MediaDTO {

    private Integer id;
    private Integer price;
//    TODO: add Album and MediaType!

    public MediaDTO() {

    }

    public MediaDTO(MediaEntity entity) {
        id = entity.getId();
        price = entity.getPrice();
    }


    public Integer getId() {
        return id;
    }

    public MediaDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public MediaDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
