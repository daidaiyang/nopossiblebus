package com.nopossiblebus.entity.bean;

import java.io.Serializable;

public class SaveOrderBean implements Serializable {
    private String total_money;


    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }
}
