package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.DomainObject;

import javax.persistence.*;

@Entity
@Table(name = "media_order")
public class MediaOrderEntity implements DomainObject {

    @EmbeddedId
    private MediaOrderEntityPK pk;

    private Integer count = 1;
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
