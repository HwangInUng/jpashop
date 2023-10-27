package com.example.jpashop.repository;

import com.example.jpashop.domain.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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
