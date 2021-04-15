package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;


@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {

        //enum타입은 == 비교
        if(member.getGrade()== Grade.VIP)
        {
            return discountFixAmount;
        }else{
            return 0;
        }

    }
}
