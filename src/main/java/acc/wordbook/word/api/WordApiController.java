package acc.wordbook.word.api;

import acc.wordbook.member.dto.Member;
import acc.wordbook.word.dto.Word;
import acc.wordbook.word.mapper.WordMapper;
import acc.wordbook.word.mapper.WrongWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/word")
@RestController
public class WordApiController {

    @Autowired
    private WordMapper wordMapper;

    @Autowired
    private WrongWordMapper wrongWordMapper;

    @GetMapping("/question/list")
    @ResponseBody
    public List<Word> getQuestionList(@RequestParam("mode") String mode, HttpServletRequest request){
        Member member = (Member) request.getSession().getAttribute("loginMember");
        long memberPk = member.getMember_pk();

        List<Word> questionList = null;

        if("wrong".equals(mode)){
            questionList = wrongWordMapper.getWrongWordList(memberPk);
        } else {
            questionList = wordMapper.getQuestionList(memberPk);
        }

        return questionList;
    }
}
