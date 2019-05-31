package com.nopossiblebus.activies.personalcenter.myintegral;

import android.content.Context;

import com.nopossiblebus.entity.bean.MyIntegralBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyintegralContract {
    interface View extends BaseView {
        void setListData(List<MyIntegralBean> data);
        void setMoreListData(List<MyIntegralBean> data);

    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
