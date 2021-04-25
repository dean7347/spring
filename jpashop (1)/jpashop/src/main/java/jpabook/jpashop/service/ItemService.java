package jpabook.jpashop.service;


import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item)
    {
        itemRepository.save(item);
    }

    public List<Item> findItems()
    {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


    @Transactional
    public void updateItem(@PathVariable Long itemId, String name, int price, int stockQuantity)
    {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

        //세이브 머지를 호출 할 필요가 없다
        //find Item으로 찾아오면 영속상태이고 값이 셋팅되면
        //트렌젝셔널에 의해서 커밋이 된다 -> flush를 날린다  -> 변경된것 다 찾는다


    }
}
