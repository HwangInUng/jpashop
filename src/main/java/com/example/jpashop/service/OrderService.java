package com.example.jpashop.service;

import com.example.jpashop.domain.Delivery;
import com.example.jpashop.domain.Item;
import com.example.jpashop.domain.Member;
import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderItem;
import com.example.jpashop.domain.OrderSearch;
import com.example.jpashop.repository.ItemRepository;
import com.example.jpashop.repository.MemberRepository;
import com.example.jpashop.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    // 주문
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findById(memberId).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();

        // 배송정보 생성
        Delivery delivery = new Delivery();
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        // 영속화
        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.cancel();
    }

    // 나중에 구현
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch.toSpecification());
    }
}
