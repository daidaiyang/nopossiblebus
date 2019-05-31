package com.nopossiblebus.entity.api;

import com.nopossiblebus.entity.HttpGetService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

public class GetIntegralListApi extends BaseApi {


    private Map<String,Object> map;

    public GetIntegralListApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }


    public void initData(String trans_type,int page_num){
        map = new HashMap<>();
        map.put("page_num",page_num);
        map.put("page_size",10);
        if (!trans_type.equals("")){
            map.put("trans_type",trans_type);
        }
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getIntegralList(map);
    }
}
