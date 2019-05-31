package com.nopossiblebus.activies.main.togood.supplygood;


import com.nopossiblebus.entity.api.GetProductListApi;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SupplygoodPresenter extends BasePresenterImpl<SupplygoodContract.View> implements SupplygoodContract.Presenter{


    private int page_num = 1;


    public void getProductList(){
        page_num =1;
        GetProductListApi getProductListApi = new GetProductListApi(getList,mView.getThis());
        getProductListApi.initData("","",page_num);
        mView.getManager().doHttpDeal(getProductListApi);
    }

    public void getProductListMore(){
        page_num ++;
        GetProductListApi getProductListApi = new GetProductListApi(getListMore,mView.getThis());
        getProductListApi.initData("","",page_num);
        mView.getManager().doHttpDeal(getProductListApi);
    }



    private HttpOnNextListener<BasePageBean<ProductListBean>> getList = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
            mView.setData(bean.getData());
        }
    };

    private HttpOnNextListener<BasePageBean<ProductListBean>> getListMore = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
            mView.setMoreDate(bean.getData());
        }
    };
}
