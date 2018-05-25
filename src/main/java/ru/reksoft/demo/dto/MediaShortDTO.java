package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.MediaEntity;

public class MediaShortDTO {

    private Integer id;
    private Integer price;
//    TODO: add Album and MediaType!

    public MediaShortDTO() {

    }

    public MediaShortDTO(MediaEntity entity) {
        id = entity.getId();
        price = entity.getPrice();
    }


    public Integer getId() {
        return id;
    }

    public MediaShortDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public MediaShortDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
