package com.nopossiblebus.activies.confirorder;

import java.io.Serializable;
import java.util.List;

public class ConfirmOrderBean implements Serializable {

    private String province_no;
    private String province_name;
    private String city_no;
    private String city_name;
    private String district_no;
    private String district_name;
    private String address;
    private String take_contacts;
    private String take_tel_phone;
    private String delivery_time;
    private List<OrderLineBean> order_line;


    public String getProvince_no() {
        return province_no;
    }

    public void setProvince_no(String province_no) {
        this.province_no = province_no;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_no() {
        return city_no;
    }

    public void setCity_no(String city_no) {
        this.city_no = city_no;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_no() {
        return district_no;
    }

    public void setDistrict_no(String district_no) {
        this.district_no = district_no;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTake_contacts() {
        return take_contacts;
    }

    public void setTake_contacts(String take_contacts) {
        this.take_contacts = take_contacts;
    }

    public String getTake_tel_phone() {
        return take_tel_phone;
    }

    public void setTake_tel_phone(String take_tel_phone) {
        this.take_tel_phone = take_tel_phone;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public List<OrderLineBean> getOrder_line() {
        return order_line;
    }

    public void setOrder_line(List<OrderLineBean> order_line) {
        this.order_line = order_line;
    }
}
