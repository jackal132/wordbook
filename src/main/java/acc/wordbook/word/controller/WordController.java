package acc.wordbook.word.controller;

import acc.wordbook.member.dto.Member;
import acc.wordbook.word.dto.Answer;
import acc.wordbook.word.dto.AnswerList;
import acc.wordbook.word.dto.Word;
import acc.wordbook.word.mapper.WordMapper;
import acc.wordbook.word.mapper.WrongWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/word")
@Controller
public class WordController {

    @Autowired
    private WordMapper wordMapper;

    @Autowired
    private WrongWordMapper wrongWordMapper;

    @PostMapping("/register")
    public String register(@ModelAttribute("word") Word word, HttpServletRequest request){

        Member member = (Member) request.getSession().getAttribute("loginMember");
        long memberPk = member.getMember_pk();

        Word dupWord = wordMapper.wordDuplicateCheck(word.getWord(), memberPk);
        if(dupWord == null) {
            word.setMember_pk(memberPk);
            wordMapper.saveWord(word);
        }

        return "redirect:/home";
    }

    @PostMapping("/save")
    public String save(@RequestParam("mode") String mode, HttpServletRequest request){

        Member member = (Member) request.getSession().getAttribute("loginMember");
        long memberPk = member.getMember_pk();

        String wordPk[] = request.getParameterValues("word_pk");
        String answer[] = request.getParameterValues("answer");


        return "redirect:/home";
    }
}
