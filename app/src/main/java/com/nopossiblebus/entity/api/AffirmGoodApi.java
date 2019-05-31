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

public class AffirmGoodApi extends BaseApi {

    private RequestBody body ;



    public AffirmGoodApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }


    public void initData(AffirmGoodBean bean){
        body= getbody(new Gson().toJson(bean));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.affirmGood(body);
    }


    public class AffirmGoodBean{
        private String id;
        private List<AffirmOrderLine> order_line;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<AffirmOrderLine> getOrder_line() {
            return order_line;
        }

        public void setOrder_line(List<AffirmOrderLine> order_line) {
            this.order_line = order_line;
        }
    }


    public class AffirmOrderLine{
        private String product_id;
        private String affirm_type;
        private String affirm_num;


        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getAffirm_type() {
            return affirm_type;
        }

        public void setAffirm_type(String affirm_type) {
            this.affirm_type = affirm_type;
        }

        public String getAffirm_num() {
            return affirm_num;
        }

        public void setAffirm_num(String affirm_num) {
            this.affirm_num = affirm_num;
        }
    }
}
