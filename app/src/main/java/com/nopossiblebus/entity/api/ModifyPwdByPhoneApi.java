package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class ModifyPwdByPhoneApi extends BaseApi {


    private ModifyPwdByPhoneBean bean;
    private RequestBody body;


    public ModifyPwdByPhoneApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setCache(false);
        setCancel(false);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.modifyPwdByPhone(body);
    }

    public void initData(String phone,String code,String pwd){
        bean = new ModifyPwdByPhoneBean(phone,pwd,code);
        String json = new Gson().toJson(bean);
        body = getbody(json);
    }

    class ModifyPwdByPhoneBean{
        private String phone;
        private String pwd;
        private String code;

        public ModifyPwdByPhoneBean(String phone, String pwd, String code) {
            this.phone = phone;
            this.pwd = pwd;
            this.code = code;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
