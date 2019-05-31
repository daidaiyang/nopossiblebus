package com.nopossiblebus.entity.api;

import com.nopossiblebus.entity.HttpUploadService;
import com.nopossiblebus.http.Api.BaseApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import rx.Observable;

public class UploadImageApi extends BaseApi {

    private MultipartBody.Part part;

    public UploadImageApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setBaseUrl("http://api.bukenengtech.cn/file/v1/");
        setShowProgress(false);
    }

    public void initData(MultipartBody.Part part){
        this.part = part;
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpUploadService httpUploadService = retrofit.create(HttpUploadService.class);
        return httpUploadService.uploadImage(part);
    }
}
