package acc.wordbook.word.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private long word_pk;

    private String answer;

    private String hiddenMeaning;

    private long member_pk;

}
