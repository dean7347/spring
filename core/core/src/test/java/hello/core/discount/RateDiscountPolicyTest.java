package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인")
    void vip_o(){

        //g
        Member vip = new Member(1L, "VIP", Grade.VIP);
        //w
        int discount = discountPolicy.discount(vip, 10000);
        //t
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("Vip아니면 할인 적용 X")
    void vip_x(){
        //g
        Member Bas = new Member(1L, "BAS", Grade.BASIC);
        //w
        int discount = discountPolicy.discount(Bas, 10000);
        //t
        Assertions.assertThat(discount).isEqualTo(1000);
    }

}