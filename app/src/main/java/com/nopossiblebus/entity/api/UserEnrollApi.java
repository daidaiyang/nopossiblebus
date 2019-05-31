package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class UserEnrollApi extends BaseApi {


    private RequestBody body;

    private UserEnrollBean userEnrollBean;



    public UserEnrollApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCancel(false);
        setCache(false);
    }

    public void setParams(String phone,String code,String pwd) {
        userEnrollBean = new UserEnrollBean(phone,pwd,code);
        String json = new Gson().toJson(userEnrollBean);
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
    }



    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.userEnroll(body);
    }

    class UserEnrollBean{
        private String phone;
        private String pwd;
        private String code;

        public UserEnrollBean(String phone, String pwd, String code) {
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
