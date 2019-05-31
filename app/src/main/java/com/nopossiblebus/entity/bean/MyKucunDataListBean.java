package com.nopossiblebus.entity.bean;

import java.io.Serializable;

public class MyKucunDataListBean implements Serializable {

    private String id;
    private String user_id;
    private String product_id;
    private String stock_price;
    private String store_in;
    private String store_out;
    private String surplus;
    private ProductListBean product;


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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getStock_price() {
        return stock_price;
    }

    public void setStock_price(String stock_price) {
        this.stock_price = stock_price;
    }

    public String getStore_in() {
        return store_in;
    }

    public void setStore_in(String store_in) {
        this.store_in = store_in;
    }

    public String getStore_out() {
        return store_out;
    }

    public void setStore_out(String store_out) {
        this.store_out = store_out;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }

    public ProductListBean getProduct() {
        return product;
    }

    public void setProduct(ProductListBean product) {
        this.product = product;
    }
}
