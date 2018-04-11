package com.cskaoyan.utils;

import java.util.List;



public class PageDivide<T>
{


    public static final int CAGEGORY_NUM_PER_PAGE =3;

    private  int totalCount;  //总记录数

    private int currentPageNum;  //当前处于第几页. 请求参数里应该告诉后台要去显示的页码

    private int totalPageNum; //总的页数

    private  int prevPageNum;  //前一页

    private  int nextPageNum;  //后一页

    private List<T>  records;



    public static int getCagegoryNumPerPage() {
        return CAGEGORY_NUM_PER_PAGE;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getPrevPageNum() {
        return prevPageNum;
    }

    public void setPrevPageNum(int prevPageNum) {
        this.prevPageNum = prevPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public void init(){
        int totalPageNum = totalCount % CAGEGORY_NUM_PER_PAGE == 0 ? totalCount / CAGEGORY_NUM_PER_PAGE : totalCount / CAGEGORY_NUM_PER_PAGE + 1;
        this.setTotalPageNum(totalPageNum);
        this.setNextPageNum(nextPageNum >= totalCount?nextPageNum:totalPageNum);
        this.setPrevPageNum(prevPageNum <= 1?1:prevPageNum);
    }


}