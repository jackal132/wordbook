package acc.wordbook.member.mapper;

import acc.wordbook.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    Member findMember(Member member);

    Member findById(String id);

    void saveMember(Member member);
}
