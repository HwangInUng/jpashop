package com.jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;
    @Embedded
    private Address address;
    @Enumerated
    private DeliveryStatus status;
    @OneToOne(mappedBy = "delivery")
    private Order order;
}
