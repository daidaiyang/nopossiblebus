package com.nopossiblebus.activies.search;

import android.content.Context;

import com.nopossiblebus.entity.api.AddToShopCarApi;
import com.nopossiblebus.entity.api.ProductListApi;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.LogUtil;
import com.nopossiblebus.utils.ToastUtil;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SearchPresenter extends BasePresenterImpl<SearchContract.View> implements SearchContract.Presenter{



    private int pageNum = 1;

    public void search(String key){
        ProductListApi productListApi = new ProductListApi(searchProduct,mView.getThis());
        productListApi.initData(key,pageNum);
        mView.getManager().doHttpDeal(productListApi);
    }


    /**
     *刷新商品
     * @param key
     */
    public void refresh(String key){
        pageNum = 1;
        ProductListApi productListApi = new ProductListApi(refreshProduct,mView.getThis());
        productListApi.initData(key,pageNum);
        mView.getManager().doHttpDeal(productListApi);
    }

    /**
     * 加载商品
     * @param key
     */
    public void load(String key){
        pageNum ++;
        ProductListApi productListApi = new ProductListApi(loadProduct,mView.getThis());
        productListApi.initData(key,pageNum);
        mView.getManager().doHttpDeal(productListApi);
    }

    /**
     * 加入购物车
     */
    public void  addtocart(String productId){
        AddToShopCarApi addToShopCarApi = new AddToShopCarApi(addToShop,mView.getThis());
        addToShopCarApi.initData(productId,"1");
        mView.getManager().doHttpDeal(addToShopCarApi);
    }


    /**
     * 第一次加载
     */
    private HttpOnNextListener<BasePageBean<ProductListBean>> searchProduct = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
            mView.upDataData(bean.getData());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e("error",e.getMessage());
        }
    };

    /**
     * 刷新
     */
    private HttpOnNextListener<BasePageBean<ProductListBean>> refreshProduct = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
            mView.refreshData(bean.getData());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e("error",e.getMessage());
        }
    };
    /**
     * 加载
     */
    private HttpOnNextListener<BasePageBean<ProductListBean>> loadProduct = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
            if (pageNum>bean.getTotalPage()){
                pageNum--;
                ToastUtil.showBottomToast("没有更多数据了~~~");
            }
            mView.refreshData(bean.getData());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e("error",e.getMessage());
        }
    };


    private HttpOnNextListener<String> addToShop = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            ToastUtil.showBottomToast("添加购物车成功");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }
    };
}
