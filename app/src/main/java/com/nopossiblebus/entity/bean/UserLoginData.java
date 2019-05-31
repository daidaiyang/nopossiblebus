package com.nopossiblebus.entity.bean;

import java.io.Serializable;

public class UserLoginData implements Serializable {

    private static final long serialVersionUID = 6389401693067654730L;
    private User user;
    private UserDetail user_detail;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetail getUser_detail() {
        return user_detail;
    }

    public void setUser_detail(UserDetail user_detail) {
        this.user_detail = user_detail;
    }
}
