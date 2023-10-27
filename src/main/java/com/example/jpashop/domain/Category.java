package com.example.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*SQL
    create table category (
       category_id int8 not null,
        name varchar(255),
        parent_id int8,
        primary key (category_id)
    )
* */

@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> childs = new ArrayList<>();


    // 다대다 관계는 실무에서는 많이 사용하지는 않음
    @ManyToMany
    @JoinTable( // 조인할 테이블을 매핑
            name = "CATEGORY_ITEM", // 테이블명
            joinColumns = @JoinColumn(name = "category_id"), // 주 대상
            inverseJoinColumns = @JoinColumn(name = "item_id") // 반대의 보조 대상
    )
    private List<Item> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChilds() {
        return childs;
    }

    public void addChildCategory(Category child) {
        this.childs.add(child);
        child.setParent(this);
    }
}
