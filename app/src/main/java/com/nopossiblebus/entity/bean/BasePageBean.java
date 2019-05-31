package com.nopossiblebus.entity.bean;

import java.io.Serializable;
import java.util.List;

public class BasePageBean<T> implements Serializable {

    private static final long serialVersionUID = -5296567216136831522L;
    private int page;
    private int pageLength;
    private int totalPage;
    private int totalSize;
    private List<T> data;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
