package jpabook.jpashop.service;


import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //앤티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        //예제의 단순화를 위해 하나만 주문되도록 했다 기능은 생성에서 여러개 넘기면 된다
        OrderItem orderItem = OrderItem.createorderItem(item, item.getPrice(), count);
        //다른스타일 생성을 막아야한다 그래서 orderItem의 생성자를 프로텍트로 하나 만들어준다다
       //주문생성
        Order order =Order.createOrder(member, delivery, orderItem);




        //주문 저장
        //주문생성에 cascadetype을 all로 설정했기때문에 가능한것
        // 오더가 퍼시스트가 되면 컬렉션되있는것도 펄시스트 날려준다

        //casecade의 범위는 고민해야한다 보통 라이프사이클이 동일하게 관리될때 의미가있다 다른곳에서도 참조하고 가져다쓰면 사용하면안된다
        orderRepository.save(order);
        return order.getId();

    }



    //취소
    @Transactional
    public void cancelOrder(Long orderId)
    {
        //조회
        Order order=orderRepository.findOne(orderId);
        //주문 취소

        //JPA강점이 여기서 나온다 데이터를 변경하면 업데이트 쿼리를 짜서 직접 날려야한다
        //JPA는 변경내역을 찾아서 업데이트 쿼리를 날린다
       order.cancel();

    }

    //검색
    public List<Order> findOrder(OrderSearch orderSearch) {
        return orderRepository.findAllByCriteria(orderSearch);
    }
}
