package com.nopossiblebus.activies.main.ingood.order;

import android.content.Context;

import com.nopossiblebus.entity.api.GetOrderListApi;
import com.nopossiblebus.entity.api.GetProductListApi;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderPresenter extends BasePresenterImpl<OrderContract.View> implements OrderContract.Presenter{

    private int pageNum = 1;

    public void getList(String key){
        pageNum = 1;
        GetOrderListApi getOrderListApi = new GetOrderListApi(getProductList,mView.getThis());
        getOrderListApi.initData("",key,pageNum);
        mView.getManager().doHttpDeal(getOrderListApi);
    }

    public void getMore(String key){
        pageNum++;
        GetOrderListApi getOrderListApi = new GetOrderListApi(getMoreProductList,mView.getThis());
        getOrderListApi.initData("",key,pageNum);
        mView.getManager().doHttpDeal(getOrderListApi);
    }




    private HttpOnNextListener<BasePageBean<OrderListBean>> getProductList = new HttpOnNextListener<BasePageBean<OrderListBean>>() {
        @Override
        public void onNext(BasePageBean<OrderListBean> pageBean) {
            List<OrderListBean> data = pageBean.getData();
            mView.setData(data);
        }
    };

    private HttpOnNextListener<BasePageBean<OrderListBean>> getMoreProductList = new HttpOnNextListener<BasePageBean<OrderListBean>>() {
        @Override
        public void onNext(BasePageBean<OrderListBean> pageBean) {
            List<OrderListBean> data = pageBean.getData();
            mView.setMoreData(data);
        }
    };
}
