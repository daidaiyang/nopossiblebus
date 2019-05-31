package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class SaveAuthApi extends BaseApi {


    private RequestBody body;

    public SaveAuthApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }


    public void initData(String name, String phone, String merName, Map<String,String> urlMap,List<String> schemes){
        SaveAuthBean saveAuthBean = new SaveAuthBean();
        saveAuthBean.setPhone(phone);
        saveAuthBean.setStore_name(merName);
        saveAuthBean.setContacts(name);
        Iterator<Map.Entry<String,String>> iterator = urlMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            if (key.equals("id_card_1")){
                saveAuthBean.setId_card_1(next.getValue());
            }else if (key.equals("id_card_0")){
                saveAuthBean.setId_card_0(next.getValue());
            }else if (key.equals("business_license")){
                saveAuthBean.setBusiness_license(next.getValue());
            }else {
                saveAuthBean.setCirculate_license(next.getValue());
            }
        }
        List<SaveAuthScheme> scheme_list = new ArrayList<>();
        for (String content :schemes) {
            scheme_list.add(new SaveAuthScheme(content));
        }
        saveAuthBean.setScheme_list(scheme_list);
        String s = new Gson().toJson(saveAuthBean);
        body = getbody(s);

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.saveAuth(body);
    }



    private class SaveAuthBean{
        private String contacts;
        private String phone;
        private String store_name;
        private String business_license;
        private String circulate_license;
        private String id_card_0;
        private String id_card_1;
        private List<SaveAuthScheme> scheme_list;

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

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
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

        public List<SaveAuthScheme> getScheme_list() {
            return scheme_list;
        }

        public void setScheme_list(List<SaveAuthScheme> scheme_list) {
            this.scheme_list = scheme_list;
        }
    }


    private class SaveAuthScheme{
        private String scheme_id;

        public SaveAuthScheme(String scheme_id) {
            this.scheme_id = scheme_id;
        }

        public String getScheme_id() {
            return scheme_id;
        }

        public void setScheme_id(String scheme_id) {
            this.scheme_id = scheme_id;
        }
    }
}
