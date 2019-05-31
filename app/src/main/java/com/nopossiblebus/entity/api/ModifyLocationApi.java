package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.activies.myaddress.MyAddressEventBean;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class ModifyLocationApi extends BaseApi {

    private RequestBody body;
    private MyAddressEventBean bean;

    public ModifyLocationApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(MyAddressEventBean bean){
        this.bean = bean;
        body = getbody(new Gson().toJson(bean));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.modifyLocation(body);
    }
}
