package com.jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속, 단일 테이블 전략
@DiscriminatorColumn // DTYPE
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    String name;
    int price;
    int stockQuantity;
}
