package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

//Junit 스피링과 함께 실행
@RunWith(SpringRunner.class)
//스프링 부트를 띄운상태로 태스트하기위해서(autowired 등 사용위해필요
@SpringBootTest

// 테스트 끝나면 롤백
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;
    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception
    {

        //give 주어졌을때
        Member member = new Member();
        member.setName("kim");

        //when 이렇게하면
        Long saveId =memberService.join(member);
        //then 이렇게 된다
        //em.flush();
        Assert.assertEquals(member,memberRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외()throws Exception
    {
        Member member1 = new Member();
        member1.setName("KIM1");

        Member member2 = new Member();
        member2.setName("KIM1");

        memberService.join(member1);
        memberService.join(member2); //예외 발생
        //어노테이션으로 제공한다
//        try{
//
//        }catch(IllegalStateException e)
//        {
//            return;
//
//        }


        //then
        fail("예외가 발생해야 합니다");

    }

}