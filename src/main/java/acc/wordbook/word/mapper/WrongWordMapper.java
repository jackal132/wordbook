package acc.wordbook.word.mapper;

import acc.wordbook.word.dto.Word;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface WrongWordMapper {

    List<Word> getWrongWordList(@RequestParam long member_pk);

    void saveWrongWord(@RequestParam("word_pk") long word_pk, @RequestParam("member_pk") long member_pk);

    void deleteWrongWord(@RequestParam("word_pk") long word_pk, @RequestParam("member_pk") long member_pk);

    void resetWrongWord(@RequestParam("member_pk") long member_pk);
}
