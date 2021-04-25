package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//구분자
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy="items")
    private List<Category> categories = new ArrayList<>();


    //==비즈니스 로직 ==//

    //재고감소로직이 set으로 이뤄지는게아니라 메서드를 통해서 이뤄저야한다
    //재고수량 증가 로직
    public void addStock(int quantity)
    {
        this.stockQuantity +=quantity;
    }

    //재고 감소 로직
    public void removeStock(int quantity)
    {
        int restStock = this.stockQuantity -=quantity;
        if(restStock <0)
        {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
