package jpabook.jpashop.repository;


import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;
    public void save(Item item)
    {
        //jpa 저장전까지 아이디가없다 = 새로생성한 객체이다
        if(item.getId()==null)
        {
            em.persist(item);
            //디비에서 등록된걸 가지고 오는것이다
        }else{
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll()
    {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
