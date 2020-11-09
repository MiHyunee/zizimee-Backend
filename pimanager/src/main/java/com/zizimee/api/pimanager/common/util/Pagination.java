package com.zizimee.api.pimanager.common.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Pagination {
    private int nowPage;
    private int startPage;
    private int endPage;
    private int listCount;
    private int countPerPage;
    private int lastPage;
    private int start;
    private int end;

    @Builder
    public Pagination(int listCount, int nowPage, int countPerPage) {
        this.nowPage = nowPage;
        this.listCount = listCount;
        this.countPerPage = countPerPage;
        calcLastPage(listCount, countPerPage);
        calcStartEndPage(nowPage, countPerPage);
        calcStartEnd(nowPage, countPerPage);
    }

    private void calcLastPage(int listCount, int countPerPage) {
        this.lastPage = (int)Math.ceil((double)listCount / (double)countPerPage);
    }

    private void calcStartEndPage(int nowPage, int countPerPage) {
        this.endPage = (int)Math.ceil((double)nowPage/(double)countPerPage)*countPerPage;
        if(lastPage < endPage) {
            this.endPage = getLastPage();
        }
        this.startPage = (getEndPage()-countPerPage + 1);
        if(startPage < 1) {
            this.startPage = 1;
        }
    }

    private void calcStartEnd(int nowPage, int countPerPage) {
        this.end = nowPage*countPerPage;
        this.start = end-countPerPage+1;
    }
}
