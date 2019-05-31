package com.nopossiblebus.activies.personalcenter.myintegral;

import android.content.Context;

import com.nopossiblebus.entity.api.GetIntegralListApi;
import com.nopossiblebus.entity.api.GetIntegralSumApi;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.MyIntegralBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyintegralPresenter extends BasePresenterImpl<MyintegralContract.View> implements MyintegralContract.Presenter{
    private int page_num = 1;


    public void getIntegralNum(){
        GetIntegralSumApi getIntegralSumApi = new GetIntegralSumApi(getSum,mView.getThis());
        mView.getManager().doHttpDeal(getIntegralSumApi);
    }


    private HttpOnNextListener<String> getSum = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };


    public void getIntegralList(String type){
        page_num =1;
        GetIntegralListApi getIntegralListApi = new GetIntegralListApi(getList,mView.getThis());
        getIntegralListApi.initData(type,page_num);
        mView.getManager().doHttpDeal(getIntegralListApi);
    }
    public void getMoreIntegralList(String type){
        page_num ++;
        GetIntegralListApi getIntegralListApi = new GetIntegralListApi(getMoreList,mView.getThis());
        getIntegralListApi.initData(type,page_num);
        mView.getManager().doHttpDeal(getIntegralListApi);

    }


    private HttpOnNextListener<BasePageBean<MyIntegralBean>> getList = new HttpOnNextListener<BasePageBean<MyIntegralBean>>() {
        @Override
        public void onNext(BasePageBean<MyIntegralBean> bean) {
            List<MyIntegralBean> data = bean.getData();
            mView.setListData(data);
        }
    };
    private HttpOnNextListener<BasePageBean<MyIntegralBean>> getMoreList = new HttpOnNextListener<BasePageBean<MyIntegralBean>>() {
        @Override
        public void onNext(BasePageBean<MyIntegralBean> bean) {
            List<MyIntegralBean> data = bean.getData();
            mView.setMoreListData(data);
        }
    };


}
