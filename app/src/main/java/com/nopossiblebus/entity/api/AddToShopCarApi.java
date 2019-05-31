package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class AddToShopCarApi extends BaseApi<String> {


    private RequestBody body ;
    private AddToShopCar addToShopCar;

    public AddToShopCarApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(String id,String num){
        addToShopCar = new AddToShopCar(id,num);
        String json = new Gson().toJson(addToShopCar);
        body = getbody(json);
    }




    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.addToShopCar(body);
    }

    class AddToShopCar{
        private String product_id;
        private String num;

        public AddToShopCar(String product_id, String num) {
            this.product_id = product_id;
            this.num = num;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
