package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class ProvideProductApplyApi extends BaseApi {


    private RequestBody body;

    public ProvideProductApplyApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void  initData(String idcardZ,String idcardF,String yyzz,String xkz,List<String> data,String contacts,String phone){
            ApplyBean applyBean = new ApplyBean();
            applyBean.setId_card_0(idcardF);
            applyBean.setId_card_1(idcardZ);
            applyBean.setBusiness_license(yyzz);
            applyBean.setCirculate_license(xkz);
            applyBean.setContacts(contacts);
            applyBean.setPhone(phone);
            List<IdBean> list = new ArrayList<>();
            for (int i=0;i<data.size();i++){
                IdBean idBean = new IdBean();
                idBean.setProduct_id(data.get(i));
                list.add(idBean);
            }
            applyBean.setLine_List(list);
        body = getbody(new Gson().toJson(applyBean));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.provideProductApply(body);
    }


    private class ApplyBean{
        private String business_license;
        private String circulate_license;
        private String id_card_0;
        private String id_card_1;
        private List<IdBean> line_List;
        private String contacts;
        private String phone;


        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }

        public String getCirculate_license() {
            return circulate_license;
        }

        public void setCirculate_license(String circulate_license) {
            this.circulate_license = circulate_license;
        }

        public String getId_card_0() {
            return id_card_0;
        }

        public void setId_card_0(String id_card_0) {
            this.id_card_0 = id_card_0;
        }

        public String getId_card_1() {
            return id_card_1;
        }

        public void setId_card_1(String id_card_1) {
            this.id_card_1 = id_card_1;
        }

        public List<IdBean> getLine_List() {
            return line_List;
        }

        public void setLine_List(List<IdBean> line_List) {
            this.line_List = line_List;
        }
    }

    private class  IdBean{
        private String product_id;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }
    }
}
