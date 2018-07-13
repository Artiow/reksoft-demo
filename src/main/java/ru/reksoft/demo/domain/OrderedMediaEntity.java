package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.DomainObject;

import javax.persistence.*;

@Entity
@Table(name = "ordered_media")
@AssociationOverrides({
        @AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "order_id")),
        @AssociationOverride(name = "pk.media", joinColumns = @JoinColumn(name = "media_id"))
})
public class OrderedMediaEntity implements DomainObject {

    @EmbeddedId
    private OrderedMediaEntityPK pk;

    @Basic
    @Column(name = "count", nullable = false)
    private Integer count = 1;


    public OrderedMediaEntity() {
        pk = new OrderedMediaEntityPK();
    }


    public OrderedMediaEntityPK getPk() {
        return pk;
    }

    public void setPk(OrderedMediaEntityPK pk) {
        this.pk = pk;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
