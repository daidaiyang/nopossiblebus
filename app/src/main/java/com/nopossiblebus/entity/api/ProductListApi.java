package com.nopossiblebus.entity.api;

import com.nopossiblebus.entity.HttpGetService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

public class ProductListApi extends BaseApi<String> {


    private Map<String,Object> map;

    private int pageSize = 10;


    public ProductListApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(false);
        setCache(false);
        setCancel(false);
    }
    public void initData(String key,int pagenum){
        map = new HashMap<>();
        map.put("key",key);
        map.put("page_num",pagenum);
        map.put("page_size",pageSize);
    }


    public void initData(String key,String kind_code,int pagenum){
        map = new HashMap<>();
        map.put("key",key);
        map.put("kind_code",kind_code);
        map.put("page_num",pagenum);
        map.put("page_size",pageSize);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.productList(map);
    }
}
