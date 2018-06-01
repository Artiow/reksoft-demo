package ru.reksoft.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "media_order")
public class MediaOrderEntity {

    @EmbeddedId
    private MediaOrderEntityPK pk;

    private Integer count;
    private Integer totalPrice;

    @ManyToOne
    @MapsId("mediaId")
    private MediaEntity media;
    @ManyToOne
    @MapsId("orderId")
    private OrderEntity order;


    public MediaOrderEntity() {
        pk = new MediaOrderEntityPK();
    }


    public MediaOrderEntityPK getPk() {
        return pk;
    }

    public void setPk(MediaOrderEntityPK pk) {
        this.pk = pk;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "total_price", nullable = false)
    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaOrderEntity that = (MediaOrderEntity) o;

        return pk.equals(that.pk);
    }

    @Override
    public int hashCode() {
        return pk.hashCode();
    }


    @ManyToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id", nullable = false)
    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
