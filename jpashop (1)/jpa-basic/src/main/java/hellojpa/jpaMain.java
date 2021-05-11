package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {

        //persistence에 unit 이름을 파라미터로

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //실제 db에 영향을끼치는 일관적인 단위가 있으면 꼭 엔티티 매니저를 만들어주어야 한다
        EntityManager em = emf.createEntityManager();


        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //저장




            Team team = new Team();
            team.setName("TeamA");
            //team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);


            em.flush();
            em.clear();


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {

            em.close();
        }

        emf.close();


    }
}


//생성
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HELLOA");
//            em.persist(member);
//            tx.commit();
//            //수정
//            Member findmember = em.find(Member.class, 1L);
//            //저장이 필요없다(em.persist)
//            findmember.setName("heeloJPA");
//
//            //커밋시점에 체크해서 쿼리를 날린다
//            tx.commit();

//  List<Member> result = em.createQuery("select m from Member", Member.class).getResultList();





//*---
////비영속
//Member member = new Member();
//            member.setId(100L);
//                    member.setName("HelloJPA");
//
//                    //영속
//                    System.out.println("BEFOR");
//                    em.persist(member);
//                    System.out.println("AFTER");
//                    Member member1 = em.find(Member.class, 100L);
//        System.out.println("member1 = " + member1);