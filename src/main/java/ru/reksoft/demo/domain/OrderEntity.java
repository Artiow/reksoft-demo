package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractIdentifiedEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "order")
public class OrderEntity extends AbstractIdentifiedEntity {

    private String address;
    private Timestamp orderedTime;

    private OrderStatusEntity status;

    private Collection<MediaOrderEntity> media;


    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "ordered")
    public Timestamp getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(Timestamp orderedTime) {
        this.orderedTime = orderedTime;
    }


    @OneToMany(mappedBy = "order")
    public Collection<MediaOrderEntity> getMedia() {
        return media;
    }

    public void setMedia(Collection<MediaOrderEntity> media) {
        this.media = media;
    }


    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public OrderStatusEntity getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEntity status) {
        this.status = status;
    }
}
