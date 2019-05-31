package com.nopossiblebus.activies.main.togood.applygood;

import android.content.Context;

import com.nopossiblebus.entity.api.GetApplyListApi;
import com.nopossiblebus.entity.bean.ApplyOrderDataBean;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ApplygoodPresenter extends BasePresenterImpl<ApplygoodContract.View> implements ApplygoodContract.Presenter{


    private int pageNum =1;


    public void getApplyList(String status){
        pageNum =1;
        GetApplyListApi getApplyListApi = new GetApplyListApi(getList,mView.getThis());
        getApplyListApi.initData(status,pageNum);
        mView.getManager().doHttpDeal(getApplyListApi);
    }

    public void getApplyListMore(String status){
        pageNum ++;
        GetApplyListApi getApplyListApi = new GetApplyListApi(getListMore,mView.getThis());
        getApplyListApi.initData(status,pageNum);
        mView.getManager().doHttpDeal(getApplyListApi);
    }


    private HttpOnNextListener<BasePageBean<ApplyOrderDataBean>> getList = new HttpOnNextListener<BasePageBean<ApplyOrderDataBean>>() {
        @Override
        public void onNext(BasePageBean<ApplyOrderDataBean> bean) {
            mView.setData(bean);
        }
    };


    private HttpOnNextListener<BasePageBean<ApplyOrderDataBean>> getListMore = new HttpOnNextListener<BasePageBean<ApplyOrderDataBean>>() {
        @Override
        public void onNext(BasePageBean<ApplyOrderDataBean> bean) {
            mView.setMoreData(bean);
        }
    };
}
