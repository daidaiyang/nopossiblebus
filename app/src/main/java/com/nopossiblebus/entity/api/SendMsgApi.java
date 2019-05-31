package com.nopossiblebus.entity.api;

import com.google.gson.Gson;
import com.nopossiblebus.entity.HttpPostService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class SendMsgApi extends BaseApi {

    private SendMsgBean sendMsgBean;
    private RequestBody body;


    public SendMsgApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCancel(false);
    }

    public void initData(String phone){
        sendMsgBean = new SendMsgBean(phone);
        String toJson = new Gson().toJson(sendMsgBean);
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),toJson);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.sendMsg(body);
    }

    class SendMsgBean{
        private String phone;

        public SendMsgBean(String phone) {
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
