package com.nopossiblebus.entity.bean;

import java.util.List;

public class PrivinceBean {

    private String id;
    private String name;
    private String parentId;
    private List<CityBean> childList;


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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<CityBean> getChildList() {
        return childList;
    }

    public void setChildList(List<CityBean> childList) {
        this.childList = childList;
    }
}
