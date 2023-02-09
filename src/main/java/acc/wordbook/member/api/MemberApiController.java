package acc.wordbook.member.api;

import acc.wordbook.member.dto.Member;
import acc.wordbook.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/member")
@RestController
public class MemberApiController {

    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("/chkId")
    public int chkId(@RequestParam String chkId){
        Member member = memberMapper.findById(chkId);
        if(member == null) {
            return 0;
        } else {
            return 1;
        }
    }
}
