package com.nopossiblebus.activies.identify;

import android.content.Context;

import com.nopossiblebus.entity.api.GetSchemeListApi;
import com.nopossiblebus.entity.api.SaveAuthApi;
import com.nopossiblebus.entity.api.UploadImageApi;
import com.nopossiblebus.entity.bean.GroupTypeBean;
import com.nopossiblebus.entity.bean.ProductGroupBean;
import com.nopossiblebus.entity.bean.UpLoadImagebean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class IdentifyPresenter extends BasePresenterImpl<IdentifyContract.View> implements IdentifyContract.Presenter{


    private String name;
    private String merName;
    private String phone;
    private Map<String,String> maps;

    private Map<String,String> urlMap;

    private MultipartBody.Part part_idz;
    private MultipartBody.Part part_idf;
    private MultipartBody.Part part_yyzz;
    private MultipartBody.Part part_xkz;
    private List<String> schemeList;

    public void getGroup(){
        GetSchemeListApi  getSchemeListApi = new GetSchemeListApi(getList,mView.getThis());
        mView.getManager().doHttpDeal(getSchemeListApi);
    }


    private HttpOnNextListener<List<ProductGroupBean>> getList = new HttpOnNextListener<List<ProductGroupBean>>() {
        @Override
        public void onNext(List<ProductGroupBean> list) {
            List<GroupTypeBean> groupList = new ArrayList<>();
            for (ProductGroupBean bean: list
                 ) {
                GroupTypeBean bean1 = new GroupTypeBean();
                bean1.setTitle(bean.getName());
                bean1.setId(bean.getId());
                groupList.add(bean1);
            }
            mView.setGroupData(groupList);
        }
    };


    public void commitIdenty(String name, String merName, String phone, Map<String,String> map,List<String> schemeList){
        this.name = name;
        this.merName = merName;
        this.phone = phone;
        this.maps = map;
        this.schemeList = schemeList;

        new Thread(new Runnable() {
            @Override
            public void run() {
                File file_idz = new File(maps.get("id_card_1"));
                RequestBody requestBody_idz = RequestBody.create(MediaType.parse("image/png"),file_idz);
                part_idz = MultipartBody.Part.createFormData("file",file_idz.getName(),requestBody_idz);

                File file_idf = new File(maps.get("id_card_0"));
                RequestBody requestBody_idf = RequestBody.create(MediaType.parse("image/png"),file_idf);
                part_idf = MultipartBody.Part.createFormData("file",file_idf.getName(),requestBody_idf);


                File file_yyzz = new File(maps.get("business_license"));
                RequestBody requestBody_yyzz = RequestBody.create(MediaType.parse("image/png"),file_yyzz);
                part_yyzz = MultipartBody.Part.createFormData("file",file_yyzz.getName(),requestBody_yyzz);

                File file_xkz = new File(maps.get("circulate_license"));
                RequestBody requestBody_xkz = RequestBody.create(MediaType.parse("image/png"),file_xkz);
                part_xkz = MultipartBody.Part.createFormData("file",file_xkz.getName(),requestBody_xkz);
                mView.getThis().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        urlMap = new HashMap<>();
                        UploadImageApi uploadImageApi1 = new UploadImageApi(upload_idz,mView.getThis());
                        uploadImageApi1.initData(part_idz);
                        mView.getManager().doHttpDeal(uploadImageApi1);
                    }
                });
            }
        }).start();
    }
    private HttpOnNextListener<UpLoadImagebean> upload_idz = new HttpOnNextListener<UpLoadImagebean>() {
        @Override
        public void onNext(UpLoadImagebean upLoadImagebean) {
            setImageUrl(upLoadImagebean.getUrl(),1);
            UploadImageApi uploadImageApi2 = new UploadImageApi(upload_idf,mView.getThis());
            uploadImageApi2.initData(part_idf);
            mView.getManager().doHttpDeal(uploadImageApi2);

        }
    };
    private HttpOnNextListener<UpLoadImagebean> upload_idf = new HttpOnNextListener<UpLoadImagebean>() {
        @Override
        public void onNext(UpLoadImagebean upLoadImagebean) {
            setImageUrl(upLoadImagebean.getUrl(),2);
            UploadImageApi uploadImageApi3 = new UploadImageApi(upload_yyzz,mView.getThis());
            uploadImageApi3.initData(part_yyzz);
            mView.getManager().doHttpDeal(uploadImageApi3);
        }
    };
    private HttpOnNextListener<UpLoadImagebean> upload_yyzz = new HttpOnNextListener<UpLoadImagebean>() {
        @Override
        public void onNext(UpLoadImagebean upLoadImagebean) {
            setImageUrl(upLoadImagebean.getUrl(),3);
            UploadImageApi uploadImageApi4 = new UploadImageApi(upload_xkz,mView.getThis());
            uploadImageApi4.initData(part_xkz);
            mView.getManager().doHttpDeal(uploadImageApi4);

        }
    };
    private HttpOnNextListener<UpLoadImagebean> upload_xkz = new HttpOnNextListener<UpLoadImagebean>() {
        @Override
        public void onNext(UpLoadImagebean upLoadImagebean) {
            setImageUrl(upLoadImagebean.getUrl(),4);
        }
    };


    public void setImageUrl(String url, int type) {
        if (type == 1){
            urlMap.put("id_card_1",url);
        }else if (type ==2){
            urlMap.put("id_card_0",url);
        }else if (type == 3){
            urlMap.put("business_license",url);
        }else if (type == 4){
            urlMap.put("circulate_license",url);
        }
        if (urlMap.size() == 4){
            commitInfo();
        }

    }

    private void commitInfo() {
        SaveAuthApi saveAuthApi = new SaveAuthApi(saveAuth,mView.getThis());
        saveAuthApi.initData(name,phone,merName,urlMap,schemeList);
        mView.getManager().doHttpDeal(saveAuthApi);
    }


    private HttpOnNextListener<String> saveAuth = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            mView.saveAuthFinish();
        }
    };


}
