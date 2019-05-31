package com.nopossiblebus.activies.main.ingood.cart;

import android.content.Context;

import com.nopossiblebus.entity.bean.ShopCarProductBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CartContract {
    interface View extends BaseView {
        void initData(List<ShopCarProductBean> data);
        void refreshData(List<ShopCarProductBean> data);
        void loadData(List<ShopCarProductBean> data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
