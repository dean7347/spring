package jpabook.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    //핼로우로 오면 이 컨트롤러 호출하겠다
    @GetMapping("hello")
    public String hell(Model model)
    {
        //스프링의 model에 데이터를 실어서 뷰로 넘긴다
        model.addAttribute("data","hello!!");
        //리턴은 화면이름
        //resources폴더에 templates에 설정된 뷰네임과 매칭된다
        return "hello";
    }
}
