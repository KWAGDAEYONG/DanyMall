package com.example.demo.criteria;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

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

    public Paging(PageCriteria cri, Long totalCount){
        this.cri = cri;
        this.totalCount = toIntExact(totalCount);

        calcData();
    }

    public void setSearchCri(SearchCriteria searchCri) {
        this.searchCri = searchCri;
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
