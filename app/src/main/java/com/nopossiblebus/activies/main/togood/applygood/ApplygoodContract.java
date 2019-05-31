package com.nopossiblebus.activies.main.togood.applygood;

import android.content.Context;

import com.nopossiblebus.entity.bean.ApplyOrderDataBean;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ApplygoodContract {
    interface View extends BaseView {
            void setData(BasePageBean<ApplyOrderDataBean> bean);
            void setMoreData(BasePageBean<ApplyOrderDataBean> bean);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
