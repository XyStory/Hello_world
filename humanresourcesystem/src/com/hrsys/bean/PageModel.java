package com.hrsys.bean;

/**
 * @author steve
 */
public class PageModel {
    private int pageIndex;
    private int totalRecordSum;
    private int pageSize;
    private int totalPageSum;

    public PageModel() {
        this.pageIndex = 1;
        this.pageSize = 5;
    }

    public int getPageIndex() {
        this.pageIndex = Math.max(this.pageIndex, 1);
        this.pageIndex = Math.min(this.pageIndex, this.totalPageSum);
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalRecordSum() {
        return totalRecordSum;
    }

    public void setTotalRecordSum(int totalRecordSum) {
        this.totalRecordSum = totalRecordSum;
        this.totalPageSum = (totalRecordSum - 1) / pageSize + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageSum() {
        return this.totalPageSum;
    }

    public int getStartIndex() {
        return (getPageIndex() - 1) * this.pageSize;
    }
}
