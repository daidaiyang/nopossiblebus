package com.nopossiblebus.activies.search;

import android.content.Context;

import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SearchContract {
    interface View extends BaseView {
        void upDataData(List<ProductListBean> mData);
        void refreshData(List<ProductListBean> mData);
        void loadData(List<ProductListBean> mData);
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
