package com.nopossiblebus.entity.bean;

import java.io.Serializable;
import java.util.List;

public class ProductListBean implements Serializable {
    private static final long serialVersionUID = -2671515084106718694L;

    private String id;
    private String no;
    private String store_no;
    private String name;
    private String brand;
    private String title;
    private String kind_code;
    private String kind_name;
    private String bar_code;
    private String qr_code;
    private String co;
    private String key_word;
    private String unit_code;
    private String unit_name;
    private String spec;
    private String sell_price;
    private String stock_price;
    private String remark;
    private String valid;
    private String update_user;
    private String update_time;
    private String create_user;
    private long create_time;
    private String subscribe;
    private String subscribe_code;
    private List<BaseImageList> images_list;


    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getStock_price() {
        return stock_price;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribe_code() {
        return subscribe_code;
    }

    public void setSubscribe_code(String subscribe_code) {
        this.subscribe_code = subscribe_code;
    }

    public void setStock_price(String stock_price) {
        this.stock_price = stock_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStore_no() {
        return store_no;
    }

    public void setStore_no(String store_no) {
        this.store_no = store_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKind_code() {
        return kind_code;
    }

    public void setKind_code(String kind_code) {
        this.kind_code = kind_code;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public String getUnit_code() {
        return unit_code;
    }

    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public List<BaseImageList> getImages_list() {
        return images_list;
    }

    public void setImages_list(List<BaseImageList> images_list) {
        this.images_list = images_list;
    }
}
