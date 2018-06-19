package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractDescriptiveDictionaryEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "order_status")
public class OrderStatusEntity extends AbstractDescriptiveDictionaryEntity {

    private Collection<OrderEntity> orders;


    @OneToMany(mappedBy = "status")
    public Collection<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrderEntity> orders) {
        this.orders = orders;
    }
}
