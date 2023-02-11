package acc.wordbook.util.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCreate {

    private PageVO pageVO;
    private int totalCount;
    private int endPage;
    private int beginPage;
    private boolean prev;
    private boolean next;

    private final int buttonNum = 5;

    private void calculateDataOfPage(){
        endPage = (int) (Math.ceil(pageVO.getPageNum() / (double) buttonNum) * buttonNum);
        beginPage  = (endPage - buttonNum) + 1;
        prev = (beginPage == 1) ? false : true;
        next = totalCount <= (endPage * pageVO.getCountPerPage()) ? false : true;

        if(!next){
            endPage = (int) Math.ceil(totalCount / (double) pageVO.getCountPerPage());
        }
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
        calculateDataOfPage();
    }
}
