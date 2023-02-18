package acc.wordbook.word.controller;

import acc.wordbook.member.dto.Member;
import acc.wordbook.word.dto.Word;
import acc.wordbook.word.mapper.WordMapper;
import acc.wordbook.word.mapper.WrongWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

        String wordPkArray[] = request.getParameterValues("word_pk");
        String answerArray[] = request.getParameterValues("answer");
        String meaningArray[] = request.getParameterValues("hiddenMeaning");

        List<Long> rightList = new ArrayList<>();
        List<Long> wrongList = new ArrayList<>();

        int maxLen = wordPkArray.length;
        for(int i = 0; i < maxLen; i++){

            String word[] = meaningArray[i].split(",");
            String answer[] = answerArray[i].split(",");
            Long wordPk = Long.parseLong(wordPkArray[i]);
            boolean isRight = false;
            /*
                나눈 단어 의미중에 하나라도 맞았다면 right 하나도 없으면 wrong
            */
            for(int j = 0; j  < word.length; j++){
                for(int k = 0; k < answer.length; k++){
                    if(word[j].equals(answer[k])) {
                        isRight = true;
                    }
                }
            }

            if(isRight){
                rightList.add(wordPk);
            } else {
                wrongList.add(wordPk);
            }
        }

        /*
            - wrong
            다시 맞춰야되는 문제인경우
            맞췄으면 wrong 삭제
            틀렸으면 놔둠

            - question
            맞추면 use_yn > 1로 변경
            틀리면 wrong 등록
        */
        if("question".equals(mode)){
            wordMapper.updateUseYn(rightList);
            for(int i = 0; i < wrongList.size(); i++){
                long pk = (Long) wrongList.get(i);

                // 이미 틀린 단어에 있는경우 추가하지 않음
                if(wrongWordMapper.getWrongWordCount(pk, memberPk) == 0){
                    wrongWordMapper.saveWrongWord(pk, memberPk);
                }
            }
        } else {
            for(int i = 0; i < rightList.size(); i++){
                long pk = (Long) rightList.get(i);
                wrongWordMapper.deleteWrongWord(pk, memberPk);
            }
        }

        return "redirect:/home";
    }

    @RequestMapping("/modify")
    public String modify(Word word, HttpServletRequest request){

        Member member = (Member) request.getSession().getAttribute("loginMember");
        long memberPk = member.getMember_pk();

        word.setMember_pk(memberPk);
        wordMapper.updateWord(word);

        return "redirect:/member/"+memberPk;
    }
}
