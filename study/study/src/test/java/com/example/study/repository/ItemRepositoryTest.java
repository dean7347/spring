package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemRepositoryTest extends StudyApplicationTests {
    @Autowired
    private ItemRepository itemRepository;
    @Test
    public void create(){
        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("삼성 놑느북");
        item.setTitle("A100 삼송");
        item.setContent("2019형 놋북");
        item.setPrice(900000);
        //item.setPartnerId(1L);// Long ->Partner
        item.setBrandName("SAMSUNG");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");

        Item newItem =itemRepository.save(item);
        Assertions.assertNotNull(newItem);
    }
    @Test
    @Transactional
    public void read(){

        Long id = 1L;

        Optional<Item> item= itemRepository.findById(id);
        assertTrue(item.isPresent());
        item.ifPresent(i-> {
            System.out.println(i);
        });
    }
}
