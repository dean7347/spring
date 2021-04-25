package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//스프링 빈으로 등록
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //스프링이 엔티티메니저를 만들어서 주입해준다
    //@PersistenceContext
    //스프링 jPA는
    private final EntityManager em;

//    public MemberRepository(EntityManager em){
//        this.em =em;
//    }

    public void save(Member member)
    {
        em.persist(member);
    }

    public Member findOne(Long id)
    {
        return em.find(Member.class, id);
    }

    public List<Member> findAll()
    {
        //jpql은 sql이랑 비슷하다 sql로 번역된다
        //sql은 테이블 대상쿼리 / jpql은 엔티티 객체를 대상으로
       return em.createQuery("select m from Member m", Member.class).getResultList();

    }

    public List<Member> findByName(String name)
    {
        return em.createQuery("select m from Member m where m.name=:name", Member.class).setParameter("name",name).getResultList();
    }
}
