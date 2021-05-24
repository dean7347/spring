package study.datajpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {


    //이 어노테이션을 사용하면 스프링 컨테이너가 JPA에있는 영속성 컨텍스트 em을 가져다준다
    @PersistenceContext
    private EntityManager em;

    public Member save(Member member)
    {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public Member find(Long id)
    {
        return em.find(Member.class, id);
    }

    public List<Member> findAll()
    {
        //jpql
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }

    public Optional<Member> findById(Long id)
    {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count()
    {
        return em.createQuery("select count(m) from Member m", Long.class).getSingleResult();
    }

    public List<Member> findByUsernameAndAgeGreaterThen(String username, int age)
    {
        return em.createQuery("select m from Member m where m.username = :username and m.age >:age")
                .setParameter("username",username)
                .setParameter("age",age)
                .getResultList();
    }

    public List<Member> findBypage(int age,int offset,int limit)
    {
       return em.createQuery("select m from Member m where m.age = :age order by m.username desc")
                .setParameter("age",age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    public long totalCount(int age)
    {
        return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age",age)
                .getSingleResult();
    }

    public int bulkAgePlus(int age)
    {
        return em.createQuery("update Member m set m.age = m.age + 1 where m.age >= :asge")
                .setParameter("asge", age)
                .executeUpdate();

    }



}
