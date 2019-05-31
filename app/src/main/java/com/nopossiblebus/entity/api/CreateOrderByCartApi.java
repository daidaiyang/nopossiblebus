package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.activies.confirorder.ConfirmOrderBean;
import com.nopossiblebus.activies.confirorder.OrderLineBean;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class CreateOrderByCartApi extends BaseApi {

    private RequestBody body ;

    public CreateOrderByCartApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(ConfirmOrderBean bean){
        String s = new Gson().toJson(bean);
        body = getbody(s);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.createOrderByCart(body);
    }
}
