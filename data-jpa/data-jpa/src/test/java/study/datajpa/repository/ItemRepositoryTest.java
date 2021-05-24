package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.datajpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    @Autowired ItemRepository itemRepository;


    @Test
    public void save()
    {
        Item item = new Item("1");
        itemRepository.save(item);
    }

}