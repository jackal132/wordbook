package acc.wordbook.util.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageVO {

    //사용자가 선택한 페이지 정보를 담을 변수.
    private int pageNum;
    private int countPerPage;
    private int offset;

    // 검색에 필요한 데이터
    private String keyword;
    private String searchField;


    public PageVO(){
        this.pageNum = 1;
        this.countPerPage = 10;
    }

    public int getOffset(){
        this.offset = (this.pageNum -1) * countPerPage;
        return this.offset;
    }
}
