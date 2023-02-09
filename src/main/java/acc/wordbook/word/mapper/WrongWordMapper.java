package acc.wordbook.word.mapper;

import acc.wordbook.word.dto.Word;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface WrongWordMapper {

    List<Word> getWrongWordList(@RequestParam long member_pk);
}
