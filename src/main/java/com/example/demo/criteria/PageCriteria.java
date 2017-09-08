package com.example.demo.criteria;

public class PageCriteria {

    private int page;
    private int perPageNum;

    public PageCriteria(){
        this.page = 1;
        this.perPageNum = 9;
    }

    public void setPage(int page){
        if(page<0){
            this.page=1;
            return;
        }
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getPageStart(){
        return (this.page-1)*perPageNum;
    }

    public int getPerPageNum(){
        return this.perPageNum;
    }
}
