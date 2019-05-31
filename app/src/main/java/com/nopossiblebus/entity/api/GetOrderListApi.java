package com.nopossiblebus.entity.api;

import com.nopossiblebus.entity.HttpGetService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

public class GetOrderListApi extends BaseApi {

    private Map<String,Object> map ;


    public GetOrderListApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(String key,String status,int page_num){
        map = new HashMap<>();
        if (!key.equals(""))
        map.put("key",key);
        if (!status.equals(""))
        map.put("status",status);
        map.put("page_num",page_num);
        map.put("page_size",10);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getOrderList(map);
    }
}
