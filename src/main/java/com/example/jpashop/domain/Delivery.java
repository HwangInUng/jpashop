package com.example.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*SQL
    create table delivery (
       delivery_id int8 not null,
        city varchar(255),
        street varchar(255),
        zipcode varchar(255),
        status varchar(255),
        primary key (delivery_id)
    )
* */

@Entity
@Table(name = "DELIVERY")
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    @OneToOne(mappedBy = "delivery")
    private Order order;

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    // 연관관계 편의 메소드
    public void setOrder(Order order) {
        this.order = order;
        if (order.getDelivery() != null) {
            order.setDelivery(this);
        }
    }
}
