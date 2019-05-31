package com.nopossiblebus.entity.api;

import com.nopossiblebus.entity.HttpGetService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

public class ShopcarListApi extends BaseApi {


    private Map<String,Object> map;


    public ShopcarListApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(false);
    }

    public void initData(int pagenum){
            map = new HashMap<>();
            map.put("page_num",pagenum);
            map.put("page_size",10);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {

        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.shopcarList(map);
    }
}
