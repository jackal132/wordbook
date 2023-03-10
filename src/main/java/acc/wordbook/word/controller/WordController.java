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
                ?????? ?????? ???????????? ???????????? ???????????? right ????????? ????????? wrong
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
            ?????? ??????????????? ???????????????
            ???????????? wrong ??????
            ???????????? ??????

            - question
            ????????? use_yn > 1??? ??????
            ????????? wrong ??????
        */

        if(rightList.size() > 0){
            wordMapper.updateUseYn(rightList);
        }

        if("question".equals(mode)){

            for(int i = 0; i < wrongList.size(); i++){
                long pk = (Long) wrongList.get(i);

                // ?????? ?????? ????????? ???????????? ???????????? ??????
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
