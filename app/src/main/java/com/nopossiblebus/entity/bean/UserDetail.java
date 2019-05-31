package com.nopossiblebus.entity.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 2706250330175403793L;
    @Id
    private String id="";
    private String sex="";
    private String nick_name="";
    private String real_name="";
    private String head_img_url="";
    private String province_no="";
    private String province_name="";
    private String city_no="";
    private String city_name="";
    private String district_no="";
    private String district_name="";
    private String address="";
    private String tel_phone="";
    private int valid;
    private String update_user="";
    private long update_time;
    private String create_user="";
    private long create_time;
    private String remark="";

    @Generated(hash = 1927629241)
    public UserDetail(String id, String sex, String nick_name, String real_name,
            String head_img_url, String province_no, String province_name,
            String city_no, String city_name, String district_no,
            String district_name, String address, String tel_phone, int valid,
            String update_user, long update_time, String create_user,
            long create_time, String remark) {
        this.id = id;
        this.sex = sex;
        this.nick_name = nick_name;
        this.real_name = real_name;
        this.head_img_url = head_img_url;
        this.province_no = province_no;
        this.province_name = province_name;
        this.city_no = city_no;
        this.city_name = city_name;
        this.district_no = district_no;
        this.district_name = district_name;
        this.address = address;
        this.tel_phone = tel_phone;
        this.valid = valid;
        this.update_user = update_user;
        this.update_time = update_time;
        this.create_user = create_user;
        this.create_time = create_time;
        this.remark = remark;
    }

    @Generated(hash = 1767819458)
    public UserDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getHead_img_url() {
        return head_img_url;
    }

    public void setHead_img_url(String head_img_url) {
        this.head_img_url = head_img_url;
    }

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

    public String getTel_phone() {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone) {
        this.tel_phone = tel_phone;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
