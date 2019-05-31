package com.nopossiblebus.activies.main.ingood.order;

import android.content.Context;

import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderContract {
    interface View extends BaseView {
        void setData(List<OrderListBean> list);
        void setMoreData(List<OrderListBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
