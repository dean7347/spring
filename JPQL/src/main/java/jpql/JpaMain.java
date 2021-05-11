package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {


            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

                Member member = new Member();
                member.setUserName("회원1");
                member.setTeam(teamA);
                em.persist(member);

            Member member2 = new Member();
            member2.setUserName("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUserName("회원3");
            member3.setTeam(teamB);
            em.persist(member3);






            em.flush();
            em.clear();
            int i = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            System.out.println("i = " + i);

//            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.userName, m.age) from Member m ", MemberDTO.class).getResultList();
//
//            MemberDTO memberDTO = result.get(0);
//            System.out.println("memberDTO = " + memberDTO.getUserName());
//            System.out.println("memberDTO = " + memberDTO.getAge());
//            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
//            Member member1 = result.get(0);
//            member1.setAge(20);
/*
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            List<Member> resultList = query.getResultList();

            //여러개
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            //한개
            Member result = query.getSingleResult();
            System.out.println("result = " + result);

            TypedQuery<String> query1 = em.createQuery("select m.username from Member m", String.class);
            //age는 int고 name은 string이다
            Query query2 = em.createQuery("select m.username, m.age from Member m", Member.class);
*/
//
//            Member singleResult = em.createQuery("select m from Member m where m.userName = :username", Member.class)
//                    .setParameter("username", "member1")
//                    .getSingleResult();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {

            em.close();
        }

        emf.close();

    }
}
