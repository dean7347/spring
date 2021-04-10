package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appcConfig = new AppConfig();
        MemberService memberService = appcConfig.memberService();
        OrderService orderService= appcConfig.orderService();

//        MemberService memberService =new MemberServiceImpl(null);
//        OrderService orderService= new OrderServiceImpl(null, null);

        Long memberId = 1L;
        Member ma = new Member(memberId, "MA", Grade.VIP);
        memberService.join(ma);

        Order order = orderService.createorder(memberId, "itemA", 20000);

        System.out.println("order = "+order);
    }
}
