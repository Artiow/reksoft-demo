package ru.reksoft.demo.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class OrderedMediaEntityPK implements Serializable {

    @ManyToOne
    private OrderEntity order;

    @ManyToOne
    private MediaEntity media;


    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }
}
