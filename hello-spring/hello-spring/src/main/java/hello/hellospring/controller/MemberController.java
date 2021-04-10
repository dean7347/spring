package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    //객체는 여러군데서 가져다 쓸수 있다
    //그러나 하나만 생성해서 공유으로 쓸 수 있다다
    //스프링이 관리하면 컨테이너에서 받아서 쓰도록 바꿔야한다
   //private final memberService ms = new MemberService();
    //그래서 스프링컨테이너에 등록하자 (하나만 등록된다)
    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService =memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
