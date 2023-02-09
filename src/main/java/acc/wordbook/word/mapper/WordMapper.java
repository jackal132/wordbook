package acc.wordbook.word.mapper;

import acc.wordbook.word.dto.Word;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface WordMapper {

    Word wordDuplicateCheck(@RequestParam("word") String word, @RequestParam("member_pk") long member_pk);

    void saveWord(Word word);

    List<Word> getQuestionList(@RequestParam("member_pk") long member_pk);
}
