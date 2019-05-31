package com.nopossiblebus.entity.bean;

import java.io.Serializable;

public class MyIntegralBean implements Serializable {
    private String id;
    private String trans_no;
    private String user_id;
    private String trans_method;
    private String trans_num;
    private String trans_type;
    private String balance;
    private String trans_status;
    private String remark;
    private String order_no;
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrans_no() {
        return trans_no;
    }

    public void setTrans_no(String trans_no) {
        this.trans_no = trans_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTrans_method() {
        return trans_method;
    }

    public void setTrans_method(String trans_method) {
        this.trans_method = trans_method;
    }

    public String getTrans_num() {
        return trans_num;
    }

    public void setTrans_num(String trans_num) {
        this.trans_num = trans_num;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTrans_status() {
        return trans_status;
    }

    public void setTrans_status(String trans_status) {
        this.trans_status = trans_status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
