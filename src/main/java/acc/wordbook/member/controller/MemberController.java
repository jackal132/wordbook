package acc.wordbook.member.controller;

import acc.wordbook.member.dto.Member;
import acc.wordbook.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("/signup")
    public String signupPage(Model model){
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("member") Member member){
        memberMapper.saveMember(member);

        return "redirect:/login";
    }
}
