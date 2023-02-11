package acc.wordbook.word.mapper;

import acc.wordbook.util.page.PageVO;
import acc.wordbook.word.dto.MyWord;
import acc.wordbook.word.dto.Word;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface WordMapper {

    Word wordDuplicateCheck(@RequestParam("word") String word, @RequestParam("member_pk") long member_pk);

    void saveWord(Word word);

    List<Word> getQuestionList(@RequestParam("member_pk") long member_pk);

    void updateUseYn(List<Long> pkList);

    void resetWord(@RequestParam("member_pk") long member_pk);

    int getMyWordTotalCount(PageVO pageVO);

    List<MyWord> getMyWordList(PageVO pageVO);

    void updateWord(Word word);
}
