package com.nopossiblebus.entity.bean;

import java.io.Serializable;
import java.util.List;

public class ProductGroupBean implements Serializable {

    private String id;
    private String name;
    private String valid;
    private List<ProductGroupListBean> line_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public List<ProductGroupListBean> getLine_list() {
        return line_list;
    }

    public void setLine_list(List<ProductGroupListBean> line_list) {
        this.line_list = line_list;
    }
}
