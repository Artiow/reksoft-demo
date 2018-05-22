package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.MediaEntity;

public class MediaDTOShort {

    private Integer id;
    private Integer price;
//    TODO: add Album and MediaType!

    public MediaDTOShort() {

    }

    public MediaDTOShort(MediaEntity entity) {
        id = entity.getId();
        price = entity.getPrice();
    }


    public Integer getId() {
        return id;
    }

    public MediaDTOShort setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public MediaDTOShort setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
