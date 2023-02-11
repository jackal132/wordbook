package acc.wordbook.word.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyWord {

    private long word_pk;

    private String word;

    private String meaning;

    private String question_yn;

    private String memorize_yn;


}
