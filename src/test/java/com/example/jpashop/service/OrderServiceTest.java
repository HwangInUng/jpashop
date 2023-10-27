package com.example.jpashop.service;

import com.example.jpashop.domain.Address;
import com.example.jpashop.domain.Book;
import com.example.jpashop.domain.Item;
import com.example.jpashop.domain.Member;
import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderStatus;
import com.example.jpashop.repository.OrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class OrderServiceTest {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("주문 생성 테스트")
    void orderItem() {
        // Given
        Member member = new Member();
        member.setMemberName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        entityManager.persist(member);

        Item item = new Book();
        item.setName("JPA책");
        item.setStockQuantity(10);
        item.setPrice(10000);
        entityManager.persist(item);

        int orderCount = 2;

        // When
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // Then
        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        Assertions.assertEquals(1, getOrder.getOrderItems().size());
        Assertions.assertEquals(10000 * 2, getOrder.getTotalPrice());
        Assertions.assertEquals(8, item.getStockQuantity());
    }

    @Test
    @DisplayName("주문 취소 테스트")
    void cancelOrder() {
        // Given
        Member member = new Member();
        member.setMemberName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        entityManager.persist(member);

        Item item = new Book();
        item.setName("JPA책");
        item.setStockQuantity(10);
        item.setPrice(10000);
        entityManager.persist(item);

        int orderCount = 2;

        // When
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        orderService.cancelOrder(orderId);

        // Then
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        Assertions.assertEquals(10, item.getStockQuantity());
    }
}
