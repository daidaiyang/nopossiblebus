package com.nopossiblebus.activies.togoodapply;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.nopossiblebus.adapter.TogoodapplyAdapter;
import com.nopossiblebus.dialog.SearchResultPopu;
import com.nopossiblebus.entity.api.GetProductListApi;
import com.nopossiblebus.entity.api.ProvideProductApplyApi;
import com.nopossiblebus.entity.api.UploadImageApi;
import com.nopossiblebus.entity.bean.ApplyGoodReaultBean;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.entity.bean.UpLoadImagebean;
import com.nopossiblebus.http.http.HttpManager;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TogoodApplyPresenter extends BasePresenterImpl<TogoodApplyContract.View> implements TogoodApplyContract.Presenter{

    private ProgressDialog pd = null;
    private String idCardFUrl ="";
    private String idCardZUrl ="";
    private String yyzzUrl ="";
    private String xkzUrl="";
    private SearchResultPopu popu = null;

    private List<ApplyGoodReaultBean> searList = new ArrayList<>();
    private String name = "";
    private String tel ="";
    private int position = 0;
    private EditText editText;

    private MultipartBody.Part part_idz;
    private  MultipartBody.Part part_idf;
    private MultipartBody.Part part_yyzz;
    private MultipartBody.Part part_xkz;

    public void upPic(final String idCardZ, final String idCardF, final String yyzz, final String spltxkz,
                      List<ApplyGoodReaultBean> list,String name,String tel){
        searList = list;
        this.name = name;
        this.tel = tel;
        if (pd == null){
            pd = new ProgressDialog(mView.getContext());
        }
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file_idz = new File(idCardZ);
                RequestBody requestBody_idz = RequestBody.create(MediaType.parse("image/png"),file_idz);
                part_idz = MultipartBody.Part.createFormData("file",file_idz.getName(),requestBody_idz);

                File file_idf = new File(idCardF);
                RequestBody requestBody_idf = RequestBody.create(MediaType.parse("image/png"),file_idf);
                part_idf = MultipartBody.Part.createFormData("file",file_idf.getName(),requestBody_idf);


                File file_yyzz = new File(yyzz);
                RequestBody requestBody_yyzz = RequestBody.create(MediaType.parse("image/png"),file_yyzz);
                part_yyzz = MultipartBody.Part.createFormData("file",file_yyzz.getName(),requestBody_yyzz);

                File file_xkz = new File(spltxkz);
                RequestBody requestBody_xkz = RequestBody.create(MediaType.parse("image/png"),file_xkz);
                part_xkz = MultipartBody.Part.createFormData("file",file_xkz.getName(),requestBody_xkz);
                mView.getThis().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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


    private void commit(){
        ProvideProductApplyApi provideProductApplyApi = new ProvideProductApplyApi(apply,mView.getThis());
        List<String> list = new ArrayList<>();
        for (int i =0;i<searList.size();i++){
            ApplyGoodReaultBean bean = searList.get(i);
            String id = bean.getId();
            list.add(id);
        }
        provideProductApplyApi.initData(idCardZUrl,idCardFUrl,yyzzUrl,xkzUrl,list,name,tel);
        mView.getManager().doHttpDeal(provideProductApplyApi);
    }


    private HttpOnNextListener<String> apply = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            mView.applyFinish();
        }
    };



    public void getSearchData(String content,EditText editText) {
        this.editText = editText;
        GetProductListApi getProductListApi = new GetProductListApi(getResult,mView.getThis());
        getProductListApi.initData(content,"",1);
        HttpManager.getInstance().doHttpDeal(getProductListApi);
    }


    private HttpOnNextListener<BasePageBean<ProductListBean>> getResult = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
            List<ProductListBean> data = bean.getData();
            if (popu == null){
                popu = new SearchResultPopu(mView.getContext(),data);
                popu.setOnItemClick(popuClick);
            }else {
                popu.setmData(data);
            }
            popu.showAsDropDown(editText,0,0);
        }
    };

    private SearchResultPopu.OnItemClick popuClick = new SearchResultPopu.OnItemClick() {
        @Override
        public void onItemClick(View view, String name,String id) {
            mView.setGoods(name,id);
        }
    };


    public void setImageUrl(String url, int type) {
        if (type == 1){
            idCardZUrl = url;
        }else if (type ==2){
            idCardFUrl = url;
        }else if (type == 3){
            yyzzUrl = url;
        }else if (type == 4){
            xkzUrl = url;
        }
        pd.cancel();
        if (!"".equals(idCardZUrl)&&!"".equals(idCardFUrl)&&!"".equals(yyzzUrl)&&!"".equals(xkzUrl)){
            commit();
        }

    }



}
