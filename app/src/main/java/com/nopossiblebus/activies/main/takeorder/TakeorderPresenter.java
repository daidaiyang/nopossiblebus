package com.nopossiblebus.activies.main.takeorder;

import android.content.Context;

import com.nopossiblebus.entity.api.DeliverOrderApi;
import com.nopossiblebus.entity.api.GetOrderListApi;
import com.nopossiblebus.entity.api.TakeOrderApi;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.ToastUtil;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TakeorderPresenter extends BasePresenterImpl<TakeorderContract.View> implements TakeorderContract.Presenter{


    private int page_num = 1;

    public  void getOrderlist(String status){
        page_num = 1;
        GetOrderListApi getOrderListApi = new GetOrderListApi(getlist,mView.getThis());
        getOrderListApi.initData("",status,page_num);
        mView.getManager().doHttpDeal(getOrderListApi);
    }


    public void takeOrder(String order_id){
        TakeOrderApi takeOrderApi = new TakeOrderApi(takeorder,mView.getThis());
        takeOrderApi.initData(order_id);
        mView.getManager().doHttpDeal(takeOrderApi);
    }


    public void deliverOrder(String order_id){
        DeliverOrderApi deliverOrderApi = new DeliverOrderApi(deliver,mView.getThis());
        deliverOrderApi.initData(order_id);
        mView.getManager().doHttpDeal(deliverOrderApi);
    }


    public void getMoreOrderList(String status){
        page_num++;
        GetOrderListApi getOrderListApi = new GetOrderListApi(getMorelist,mView.getThis());
        getOrderListApi.initData("",status,page_num);
        mView.getManager().doHttpDeal(getOrderListApi);
    }


    private HttpOnNextListener<String> deliver =  new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            ToastUtil.showBottomToast("操作成功");
            mView.refresh();
        }
    };

    private HttpOnNextListener<String> takeorder = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            ToastUtil.showBottomToast("抢单成功");
            mView.refresh();
        }
    };


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
