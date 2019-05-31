package com.nopossiblebus.entity.bean;

import java.io.Serializable;
import java.util.List;

public class ApplyOrderLineBean implements Serializable {

    private String id;
    private String apply_id;
    private String user_id;
    private String product_id;
    private List<BaseImageList> product_image_list;

    public List<BaseImageList> getProduct_image_list() {
        return product_image_list;
    }

    public void setProduct_image_list(List<BaseImageList> product_image_list) {
        this.product_image_list = product_image_list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
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
}
