package com.nopossiblebus.activies.main.takeorder;

import android.content.Context;

import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TakeorderContract {
    interface View extends BaseView {
        void setData(List<OrderListBean> data);
        void setMoreData(List<OrderListBean> data);
        void refresh();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
