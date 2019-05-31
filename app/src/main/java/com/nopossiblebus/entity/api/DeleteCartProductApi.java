package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class DeleteCartProductApi extends BaseApi<String> {

    private RequestBody body;
    private DeleteCartProductBean bean;

    public DeleteCartProductApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(List<String> arrays){
        bean = new DeleteCartProductBean(arrays);
        body = getbody(new Gson().toJson(bean));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.deleteCartProduct(body);
    }


    class DeleteCartProductBean{
        private List<String> arrays;

        public DeleteCartProductBean(List<String> arrays) {
            this.arrays = arrays;
        }

        public List<String> getArrays() {
            return arrays;
        }

        public void setArrays(List<String> arrays) {
            this.arrays = arrays;
        }
    }
}
