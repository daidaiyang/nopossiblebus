package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class UserLoginApi extends BaseApi {


    private UserLoginBean userLoginBean;

    private RequestBody body;


    public UserLoginApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCancel(false);
        setCache(false);
    }


    public void setParams(String phone,String pwd) {
        userLoginBean = new UserLoginBean(phone,pwd);
        String json = new Gson().toJson(userLoginBean);
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.userLogin(body);
    }


    class UserLoginBean{
        private String phone;
        private String pwd;

        public UserLoginBean(String phone, String pwd) {
            this.phone = phone;
            this.pwd = pwd;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }

}
