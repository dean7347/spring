package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//자바x와 스프링제공이 있는데 스프링이 더 좋다 옵션이 많음
//readOnly true를 주면 최적화된다(조회에서만 사용) 쓰기에는 넣으면 안된다
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
//
//    // 필드 인젝션
//    // 변경이 불가능한 단점이 있다
//    @Autowired
//    private MemberRepository memberRepository;

//
//    //세터 인젝션
//    //테스트 코드시 직접 주입가능한 장점이 있다
//    // 사실 런타임시에 바꿀일이 별로없음
//    private MemberRepository memberRepository;
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    //그래서 사용하는 방법
    //생성자 인젝션
    //final키워드가 있으면 중간에 변경을 막을수있다
    private final MemberRepository memberRepository;

    //이것은 롬복의 AllArgsConstructor로 대체
    //@RequiredArgsConstructor로 파이널이 있는필드만 생성자를 만들수 있다
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원가입
    @Transactional //쓰기는 리드온리를 주면 안됨
    public Long join(Member member)
    {

        //회원가입시 서비스로직 (ex 중복회원)
        validateDuplicatemember(member);
        memberRepository.save(member);
        return member.getId();
    }


    //동시성 문제가 발생할 수 있따
    private void validateDuplicatemember(Member member)
    {
        // Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty())
        {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    //회원 전체 조회

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId)
    {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }
}
