package study.querydsl;


import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.*;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;
    //시작전 데이터 셋팅
    @BeforeEach
    public void befor(){
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1",10,teamA);
        Member member2 = new Member("member2",20,teamA);
        Member member3 = new Member("member3",30,teamB);
        Member member4 = new Member("member4",40,teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

    }

    @Test
    public void startJPQL()
    {
        //member1찾기.

        Member findByJPQL = em.createQuery("select m from Member m where m.username =:username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findByJPQL.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl()
    {
        //스태틱임포트로 해도된다
//        QMember m = QMember.member;

//      QMember m = new QMember("m");

        Member findM = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        assertThat(findM.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search()
    {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1").and(member.age.eq(10)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetch(){
//        List<Member> fetch = queryFactory
//                .selectFrom(member)
//                .fetch();
//
//        Member fetchOne = queryFactory
//                .selectFrom(member)
//                .fetchOne();
//        Member fetchFirst = queryFactory
//                .selectFrom(member)
//                .fetchFirst();

        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        results.getTotal();
        //꺼내야 컨텐츠(데이터)가 나온다
        List<Member> results1 = results.getResults();


        //카운트용 쿼리
        long total = queryFactory
                .selectFrom(member)
                .fetchCount();
    }

    //회원 정렬순서
    // 1 나이 내림차순
    // 2 이름 올림차순
    // 단 2에서 회원 이름이 없으면 마지막에 출력 (nulls last)

    @Test
    public void sort()
    {
        em.persist(new Member (null,100));

        em.persist(new Member ("member5",100));

        em.persist(new Member ("member6",100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
    }

    @Test
    public void paging1()
    {
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                //몇번째부터 끊어서 (0부터시작인데 1이면 하나 스킵)
                .offset(1)
                //2개까지 가져옴
                .limit(2)
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void paging2()
    {
        QueryResults<Member> Qresult = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                //몇번째부터 끊어서 (0부터시작인데 1이면 하나 스킵)
                .offset(1)
                //2개까지 가져옴
                .limit(2)
                .fetchResults();

        assertThat(Qresult.getTotal()).isEqualTo(4);


    }

    @Test
    public void aggregation()
    {
        List<Tuple> fetch = queryFactory.select(member.count(),
                member.age.sum(),
                member.age.avg(),
                member.age.max(),
                member.age.min())
                .from(member)
                .fetch();

        //튜플로 조회되는데 이것은 쿼리 dsl이 제공한다
        //여러 타입을 막 꺼내오는것

        Tuple tuple = fetch.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(4);


    }

    //팀 이름 각 팀의 평균 연령 구해라
    @Test
    public void group() throws Exception{
        List<Tuple> result = queryFactory.select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

    }




}
