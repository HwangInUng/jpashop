package com.example.jpashop.domain;

import org.springframework.data.jpa.domain.Specification;

public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    public Specification<Order> toSpecification() {
        return OrderSpec.memberNameLike(memberName)
                .and(OrderSpec.orderStatusEq(orderStatus));
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
