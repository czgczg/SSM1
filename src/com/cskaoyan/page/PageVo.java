package com.cskaoyan.page;

import java.util.List;

public class PageVo<T> {

    static final int NUM_LIMIT_OF_RECORDS = 4;
    private int currentPageNum;
    private int totalPageNum;
    private int totalRecordsNum;
    private int prevPageNum;
    private int nextPageNum;
    private List<T> records;
    private int offset;
    private String txtname = "";

    public String getTxtname() {
        return txtname;
    }

    public void setTxtname(String txtname) {
        this.txtname = txtname;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "currentPageNum=" + currentPageNum +
                ", totalPageNum=" + totalPageNum +
                ", totalRecordsNum=" + totalRecordsNum +
                ", prevPageNum=" + prevPageNum +
                ", nextPageNum=" + nextPageNum +
                ", records=" + records +
                '}';
    }

    public static int getNumLimitOfRecords() {
        return NUM_LIMIT_OF_RECORDS;
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

    public int getTotalRecordsNum() {
        return totalRecordsNum;
    }

    public void setTotalRecordsNum(int totalRecordsNum) {
        this.totalRecordsNum = totalRecordsNum;
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

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public static PageVo getPage(int totalRecordsNum, int currentPageNum) {
        int limit = NUM_LIMIT_OF_RECORDS;
        PageVo page = new PageVo();
        page.totalRecordsNum = totalRecordsNum;
        page.currentPageNum = currentPageNum;
        page.totalPageNum = totalRecordsNum%limit == 0 ?totalRecordsNum/limit : totalRecordsNum/limit + 1;
        page.offset = (currentPageNum - 1) * limit;
        return page;
    }

    public int getTotalPageNum(int totalRecordsNum){
        return totalRecordsNum%NUM_LIMIT_OF_RECORDS == 0 ?totalRecordsNum/NUM_LIMIT_OF_RECORDS : totalRecordsNum/NUM_LIMIT_OF_RECORDS + 1;
    }
}
