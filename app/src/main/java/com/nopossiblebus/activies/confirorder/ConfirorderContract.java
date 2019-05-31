package com.nopossiblebus.activies.confirorder;

import android.content.Context;

import com.nopossiblebus.entity.bean.SaveOrderBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ConfirorderContract {
    interface View extends BaseView {
        void saveOrderSuccess(SaveOrderBean s);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
