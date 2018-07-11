package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.DomainObject;

import javax.persistence.*;

@Entity
@Table(name = "media_order")
@AssociationOverrides({
        @AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "order_id")),
        @AssociationOverride(name = "pk.media", joinColumns = @JoinColumn(name = "media_id"))
})
public class MediaOrderEntity implements DomainObject {

    @EmbeddedId
    private MediaOrderEntityPK pk;

    @Basic
    @Column(name = "count", nullable = false)
    private Integer count = 1;

    @Basic
    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;


    public MediaOrderEntity() {
        pk = new MediaOrderEntityPK();
    }


    public MediaOrderEntityPK getPk() {
        return pk;
    }

    public void setPk(MediaOrderEntityPK pk) {
        this.pk = pk;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Transient
    public MediaEntity getMedia() {
        return pk.getMedia();
    }

    public void setMedia(MediaEntity media) {
        pk.setMedia(media);
    }

    @Transient
    public OrderEntity getOrder() {
        return pk.getOrder();
    }

    public void setOrder(OrderEntity order) {
        pk.setOrder(order);
    }
}
