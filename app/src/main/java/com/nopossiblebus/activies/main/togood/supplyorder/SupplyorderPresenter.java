package com.nopossiblebus.activies.main.togood.supplyorder;

import android.content.Context;

import com.nopossiblebus.entity.api.GetDeliverOrderListApi;
import com.nopossiblebus.entity.api.GetOrderListApi;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SupplyorderPresenter extends BasePresenterImpl<SupplyorderContract.View> implements SupplyorderContract.Presenter{


    private int page_num = 1;


    public void getOrderList(String status){
        page_num =1;
        GetDeliverOrderListApi getDeliverOrderListApi = new GetDeliverOrderListApi(getlist,mView.getThis());
        getDeliverOrderListApi.initData("",status,page_num);
        mView.getManager().doHttpDeal(getDeliverOrderListApi);
    }
    public void getMoreOrderList(String status){
        page_num ++;
        GetDeliverOrderListApi getDeliverOrderListApi = new GetDeliverOrderListApi(getMorelist,mView.getThis());
        getDeliverOrderListApi.initData("",status,page_num);
        mView.getManager().doHttpDeal(getDeliverOrderListApi);
    }


    private HttpOnNextListener<BasePageBean<OrderListBean>> getlist = new HttpOnNextListener<BasePageBean<OrderListBean>>() {
        @Override
        public void onNext(BasePageBean<OrderListBean> listBeanBasePageBean) {
            List<OrderListBean> data = listBeanBasePageBean.getData();
            mView.setData(data);
        }
    };
    private HttpOnNextListener<BasePageBean<OrderListBean>> getMorelist = new HttpOnNextListener<BasePageBean<OrderListBean>>() {
        @Override
        public void onNext(BasePageBean<OrderListBean> listBeanBasePageBean) {
            List<OrderListBean> data = listBeanBasePageBean.getData();
            mView.setMoreData(data);
        }
    };






}
