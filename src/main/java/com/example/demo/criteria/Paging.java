package com.example.demo.criteria;

import java.util.ArrayList;
import java.util.List;

public class Paging {
    private PageCriteria cri;
    private int totalCount;
    private int startPage;
    private int endPage;
    private List<Integer> pageList = new ArrayList<Integer>();
    private boolean prev;
    private int prevPage;
    private int nextPage;
    private boolean next;
    private SearchCriteria searchCri;
    private int displayPageNum = 4;

    public void setCri(PageCriteria cri) {
        this.cri = cri;
    }

    public void setSearchCri(SearchCriteria searchCri) {
        this.searchCri = searchCri;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        calcData();
    }


    private void calcData(){
        endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
        startPage = (endPage - displayPageNum)+1;

        int tempEndPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));

        if(endPage > tempEndPage){
            endPage = tempEndPage;
        }

        prev = startPage ==1?false:true; prevPage = startPage-1;
        next = endPage * cri.getPerPageNum()>=totalCount ? false : true; nextPage = endPage+1;

        for(int i = startPage; i<=endPage; i++){
            pageList.add(i);
        }

    }



}
