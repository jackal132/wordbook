package acc.wordbook.home.controller;

import acc.wordbook.member.dto.Member;
import acc.wordbook.member.mapper.MemberMapper;
import acc.wordbook.word.dto.Word;
import acc.wordbook.word.mapper.WrongWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private WrongWordMapper wrongWordMapper;

    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("member") Member member, Model model, HttpServletRequest request){
        Member findMember = memberMapper.findMember(member);
        if(findMember == null){
            model.addAttribute("msg", "존재하지 않는 ID입니다.");
            return "/login";
        }

        if(! findMember.getPassword().equals(member.getPassword()) ){
            model.addAttribute("msg", "비밀번호가 다릅니다.");
            return "/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", findMember);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model){

        Member loginMember = (Member) request.getSession().getAttribute("loginMember");
        List<Word> wrongWordList = wrongWordMapper.getWrongWordList(loginMember.getMember_pk());
        model.addAttribute("wordList",wrongWordList);

        return "/home";
    }
}
