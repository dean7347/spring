package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testEntity()
    {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10 , teamA);
        Member member2 = new Member("member2", 10 , teamA);
        Member member3 = new Member("member3", 10 , teamB);
        Member member4 = new Member("member4", 10 , teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        //persist하면 쿼리를 날리는게 아니라 모아둔다 flush를 하면 강제로 인서트쿼리를 날린다
        em.flush();
        //캐쉬를 초기화시킨다
        em.clear();


        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("->member.team = " + member.getTeam());
        }
    }
    @Test
    public void JpaEventBaseEntity() throws Exception {
        //g
        Member member = new Member("member1");
        memberRepository.save(member); //@PrePersist

        Thread.sleep(100);
        member.setUsername("member2");
        em.flush(); //@PreUpdate
        em.clear();
        //w
        Member findmember = memberRepository.findById(member.getId()).get();
        //t
        System.out.println("findmember.getCreatedDate() = " + findmember.getCreateDate());
        System.out.println("findmember.getCreatedDate() = " + findmember.getLastModifiedDate());
        System.out.println("findmember.getCreatedDate() = " + findmember.getCreatedBy());
    }
}