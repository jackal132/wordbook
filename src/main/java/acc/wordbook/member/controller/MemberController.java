package acc.wordbook.member.controller;

import acc.wordbook.member.dto.Member;
import acc.wordbook.member.mapper.MemberMapper;
import acc.wordbook.util.page.PageCreate;
import acc.wordbook.util.page.PageVO;
import acc.wordbook.word.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private WordMapper wordMapper;

    @GetMapping("/signup")
    public String signupPage(Model model){
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("member") Member member){
        memberMapper.saveMember(member);

        return "redirect:/login";
    }

    @RequestMapping("/{id}")
    public String myPage(@PathVariable("id") long id, @ModelAttribute("pageVO") PageVO pageVO, Model model){

        PageCreate pageCreate = new PageCreate();
        pageCreate.setPageVO(pageVO);
        pageCreate.setTotalCount(wordMapper.getMyWordTotalCount(pageVO));

        model.addAttribute("pageCreate",pageCreate);
        model.addAttribute("myWordLsit",wordMapper.getMyWordList(pageVO));

        return "/member/mypage";
    }
}
