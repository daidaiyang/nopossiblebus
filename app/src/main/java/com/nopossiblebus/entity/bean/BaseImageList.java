package com.nopossiblebus.entity.bean;


import com.nopossiblebus.utils.AppUtil;

import java.io.Serializable;

public class BaseImageList implements Serializable {
    private static final long serialVersionUID = -8791041951982198987L;
    private String id;
    private String product_id;
    private String url;
    private String rank;
    private int valid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUrl() {
        return AppUtil.BASEIMGURL+url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
