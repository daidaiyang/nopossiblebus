package com.nopossiblebus.activies.main.togood.supplygood;

import android.content.Context;

import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SupplygoodContract {
    interface View extends BaseView {
        void setData(List<ProductListBean> list);
        void setMoreDate(List<ProductListBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
