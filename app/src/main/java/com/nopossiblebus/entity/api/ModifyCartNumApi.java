package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class ModifyCartNumApi extends BaseApi<String> {


    private RequestBody body;
    private ModifyCartNumBean bean;



    public ModifyCartNumApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }



    public void initData(String id,int num){
        bean = new ModifyCartNumBean(id,num);
        body = getbody(new Gson().toJson(bean));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.modifyCartNum(body);
    }


    class ModifyCartNumBean{
        private String product_id;
        private int num;

        public ModifyCartNumBean(String product_id, int num) {
            this.product_id = product_id;
            this.num = num;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
