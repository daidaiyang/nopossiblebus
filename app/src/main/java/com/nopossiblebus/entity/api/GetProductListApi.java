package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpGetService;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.entity.bean.BasePageData;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class GetProductListApi extends BaseApi {


    private Map<String,Object> map ;

    public GetProductListApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(String key,String kind_code,int pagenum){
        map = new HashMap<>();
        if (!key.equals(""))
            map.put("key",key);
        if (!kind_code.equals(""))
        map.put("kind_code",kind_code);
        map.put("page_num",pagenum);
        map.put("page_size",10);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getProductList(map);
    }

}
