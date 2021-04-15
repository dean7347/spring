package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원 가입
    public Long join(Member member){

        //같은 이름 중복회원 X
        //단축키 컨트롤 알트 v 자동으로
        //Optional<Member> result =
        //이것을 메소드로 뺄려면
        //커맨드 알트 m
        System.out.println("메서드 실행");
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

//    전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }
}