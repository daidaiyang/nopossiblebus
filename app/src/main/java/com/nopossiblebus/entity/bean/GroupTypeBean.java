package com.nopossiblebus.entity.bean;

import android.widget.CheckBox;

public class GroupTypeBean extends BaseCheckedBean {

    private String id;
    private String title="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
