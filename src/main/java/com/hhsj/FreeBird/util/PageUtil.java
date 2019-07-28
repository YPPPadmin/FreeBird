package com.hhsj.FreeBird.util;

import java.util.List;

public class PageUtil<T> {
    private int pageNum;//页码
    private int pageSize;//页面容量
    private int totalPageCount;//总页数
    private int totalCount;//总资源数
    private List<T> data;//分页查询的结果
    private int pageIndex;//pageIndex = (pageNum-1)*pageSize

    public int getPageIndex() {
        pageIndex = (pageNum-1)*pageSize;
        return pageIndex;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        } else if (pageNum > getTotalPageCount()) {
            pageNum = getTotalPageCount();
        }
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        return totalPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
