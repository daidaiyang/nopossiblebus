package com.nopossiblebus.entity.bean;

import java.io.Serializable;

public class ShopCarProductBean extends BaseCheckedBean implements Serializable{

    private static final long serialVersionUID = -2638954270905997581L;
    private String id;
    private String user_id;
    private String product_id;
    private int num;
    private String unit_price;
    private int discounts;
    private String money;
    private ProductListBean product;
    private String scheme_id;


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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public int getDiscounts() {
        return discounts;
    }

    public void setDiscounts(int discounts) {
        this.discounts = discounts;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getScheme_id() {
        return scheme_id;
    }

    public void setScheme_id(String scheme_id) {
        this.scheme_id = scheme_id;
    }

    public ProductListBean getProduct() {
        return product;
    }

    public void setProduct(ProductListBean product) {
        this.product = product;
    }
}
