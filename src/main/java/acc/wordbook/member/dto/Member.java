package acc.wordbook.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    private long member_pk;

    @NonNull
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String password;

    private Date reg_dt;
}
