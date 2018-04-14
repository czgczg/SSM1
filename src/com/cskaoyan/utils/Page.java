package com.cskaoyan.utils;

import java.util.List;



public class Page<T>
{


    public static final int PASSENGER__NUM_PER_PAGE =5;
    //修改分页要显示的每页的记录条数
    public static final int ORDERMAIN__NUM_PER_PAGE =8;

    private  int totalCount;  //总记录数

    private int currentPage;  //当前处于第几页. 请求参数里应该告诉后台要去显示的页码

    private int totalPage; //总的页数

    private  int prevPageNum;  //前一页

    private  int nextPageNum;  //后一页


    private List<T>  result;


    public static int getPassenger_numPerPage() {
        return PASSENGER__NUM_PER_PAGE;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public void init(int num_int){
        int totalPageNum = totalCount % PASSENGER__NUM_PER_PAGE == 0 ? totalCount / PASSENGER__NUM_PER_PAGE : totalCount / PASSENGER__NUM_PER_PAGE + 1;
        this.setTotalPage(totalPageNum);
        this.setNextPageNum(num_int-1==0?num_int:num_int-1);
        this.setPrevPageNum(num_int+1>totalPageNum?num_int:num_int+1);

    }

    public void init2(int totalCount,int num_int){
        this.setCurrentPage(num_int);
        this.setTotalCount(totalCount);
        int totalPageNum = totalCount % PASSENGER__NUM_PER_PAGE == 0 ? totalCount / PASSENGER__NUM_PER_PAGE : totalCount / PASSENGER__NUM_PER_PAGE + 1;
        this.setTotalPage(totalPageNum);
        this.setNextPageNum(num_int-1==0?num_int:num_int-1);
        this.setPrevPageNum(num_int+1>totalPageNum?num_int:num_int+1);
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", prevPageNum=" + prevPageNum +
                ", nextPageNum=" + nextPageNum +
                ", records=" + result +
                '}';
    }
}