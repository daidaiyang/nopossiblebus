package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class PayWxApi extends BaseApi {

    private RequestBody body;



    public PayWxApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);

    }


    public void initData(String money){
            body = getbody(new Gson().toJson(new Money(money)));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.payWx(body);
    }


    private class Money{
        private String money;

        public Money(String money) {
            this.money = money;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

}
