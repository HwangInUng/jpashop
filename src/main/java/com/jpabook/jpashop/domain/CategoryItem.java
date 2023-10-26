package com.jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY_ITEM")
public class CategoryItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Id
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}
