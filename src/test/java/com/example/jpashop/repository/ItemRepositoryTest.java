package com.example.jpashop.repository;

import com.example.jpashop.domain.Album;
import com.example.jpashop.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    void saveItem() {
        Album item = new Album();
        item.setName("item");
        item.setArtist("test");

        itemRepository.save(item);

        Item findItem = itemRepository.findOne(item.getId());

        Assertions.assertEquals(item, findItem);
    }
}
