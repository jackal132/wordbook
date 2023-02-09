package acc.wordbook.word.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Word {

    private long word_pk;

    private long member_pk;

    @NonNull
    private String word;

    @NonNull
    private String meaning;

    private int use_yn;

    private Date reg_dt;
}
