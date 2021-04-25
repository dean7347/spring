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



            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZ");

            //JPA에서 관리하지 않게되므로 트랙젝션시 아무일도 안일어남남
            //특정 엔티티만 중영속 상태로 전환
           em.detach(member);


           //영속성 컨텐츠 초기화
            //완전 초기화됐기따문에 다시 조회하면 셀렉트 쿼리가 다시나감(캐싱 x  1차캐시를 다지움)
           em.clear();

           //영속성 컨텍스트 종료
           em.close();


            //쿼리가 나가는 시점
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