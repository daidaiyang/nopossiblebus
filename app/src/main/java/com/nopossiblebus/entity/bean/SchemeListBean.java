package com.nopossiblebus.entity.bean;

import java.io.Serializable;
import java.util.List;

public class SchemeListBean implements Serializable {

    private String id;
    private String user_id;
    private String contacts;
    private String phone;
    private String store_name;
    private String id_card_1;
    private String id_card_0;
    private String business_license;
    private String circulate_license;
    private String status;//auth_status状态 -1:未提交认证(未认证) 0: 待审核 1:通过 2:拒绝/失败
    private String update_user;
    private String update_time;
    private String create_user;
    private String create_time;
    private List<Scheme_list> scheme_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getId_card_1() {
        return id_card_1;
    }

    public void setId_card_1(String id_card_1) {
        this.id_card_1 = id_card_1;
    }

    public String getId_card_0() {
        return id_card_0;
    }

    public void setId_card_0(String id_card_0) {
        this.id_card_0 = id_card_0;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public String getCirculate_license() {
        return circulate_license;
    }

    public void setCirculate_license(String circulate_license) {
        this.circulate_license = circulate_license;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public List<Scheme_list> getScheme_list() {
        return scheme_list;
    }

    public void setScheme_list(List<Scheme_list> scheme_list) {
        this.scheme_list = scheme_list;
    }
}
