package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id)
    {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member)
    {

        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> List(Pageable pageable) {
        Page<Member> all = memberRepository.findAll(pageable);
        Page<MemberDto> map = all.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        return map;
    }

    //스프링올라올때 실행
    @PostConstruct
    public void init()
    {
        for(int i =0; i< 100; i++)
        {
            memberRepository.save(new Member("user" + i, i));
        }
    }

}
