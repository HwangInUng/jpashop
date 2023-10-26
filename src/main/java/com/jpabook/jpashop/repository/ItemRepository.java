package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Item;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item); // 영속화
        } else {
            entityManager.merge(item); // 준영속 상태면 병합처리
        }
    }

    public Item findOne(Long itemId) {
        return entityManager.find(Item.class, itemId);
    }

    public List findAll() {
        return entityManager.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
