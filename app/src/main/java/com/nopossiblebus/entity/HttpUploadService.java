package com.nopossiblebus.entity;

import com.nopossiblebus.entity.bean.UpLoadImagebean;
import com.nopossiblebus.http.Api.BaseResultEntity;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface HttpUploadService {

    @Multipart
    @POST("file/upload")
    Observable<BaseResultEntity<UpLoadImagebean>> uploadImage(@Part MultipartBody.Part file);


}
