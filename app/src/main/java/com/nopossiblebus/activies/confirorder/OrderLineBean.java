package com.nopossiblebus.activies.confirorder;

import java.io.Serializable;

public class OrderLineBean implements Serializable {

    private String product_id;
    private String num;


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
