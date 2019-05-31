package com.nopossiblebus.entity.bean;

import java.io.Serializable;
import java.util.List;

public class MyKucunDataBean implements Serializable {

    private String total;
    private String today;
    private List<MyKucunDataListBean> data_list;


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public List<MyKucunDataListBean> getData_list() {
        return data_list;
    }

    public void setData_list(List<MyKucunDataListBean> data_list) {
        this.data_list = data_list;
    }
}
