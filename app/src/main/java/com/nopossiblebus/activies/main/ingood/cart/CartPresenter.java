package com.nopossiblebus.activies.main.ingood.cart;

import android.content.Context;

import com.nopossiblebus.entity.api.DeleteCartProductApi;
import com.nopossiblebus.entity.api.ModifyCartNumApi;
import com.nopossiblebus.entity.api.ShopcarListApi;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.ShopCarProductBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.ToastUtil;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CartPresenter extends BasePresenterImpl<CartContract.View> implements CartContract.Presenter{


    private int pageNum = 1;
    private int type = 0;

    /**
     * 首次获取数据
     */
    public void getShopCarData(){
        type =0;
        ShopcarListApi shopcarListApi = new ShopcarListApi(shopcarList,mView.getThis());
        shopcarListApi.initData(pageNum);
        mView.getManager().doHttpDeal(shopcarListApi);
    }
    /**
     * 刷新数据
     */
    public void freshData(){
        type =1;
        pageNum = 1;
        ShopcarListApi shopcarListApi = new ShopcarListApi(shopcarList,mView.getThis());
        shopcarListApi.initData(pageNum);
        mView.getManager().doHttpDeal(shopcarListApi);
    }
    /**
     * 加载数据
     */
    public void loadData(){
        type =2;
        pageNum++;
        ShopcarListApi shopcarListApi = new ShopcarListApi(shopcarList,mView.getThis());
        shopcarListApi.initData(pageNum);
        mView.getManager().doHttpDeal(shopcarListApi);
    }


    /**
     * 改变购物车数量
     * @param id
     * @param num
     */
    public void changeNum(String id,int num){
        ModifyCartNumApi modifyCartNumApi = new ModifyCartNumApi(changeNum,mView.getThis());
        modifyCartNumApi.initData(id,num);
        mView.getManager().doHttpDeal(modifyCartNumApi);
    }

    /**
     * 删除产品
     * @param product_ids
     */
    public void deleteProduct(List<String> product_ids){
        DeleteCartProductApi deleteCartProductApi = new DeleteCartProductApi(delete,mView.getThis());
        deleteCartProductApi.initData(product_ids);
        mView.getManager().doHttpDeal(deleteCartProductApi);
    }


    private HttpOnNextListener<String> delete = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            ToastUtil.showBottomToast("产品删除成功");
            getShopCarData();
        }
    };


    private HttpOnNextListener<String> changeNum = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            freshData();
        }
    };


    private HttpOnNextListener<BasePageBean<ShopCarProductBean>> shopcarList = new HttpOnNextListener<BasePageBean<ShopCarProductBean>>() {
        @Override
        public void onNext(BasePageBean<ShopCarProductBean> bean) {
            if (type == 0){
                mView.initData(bean.getData());
            }else if (type ==1){
                mView.refreshData(bean.getData());
            }else {
                if (pageNum > bean.getTotalPage()){
                    pageNum--;
                    ToastUtil.showBottomToast("没有更多数据了~~~");
                }
                mView.loadData(bean.getData());
            }

        }
    };

}
