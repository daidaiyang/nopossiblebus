package com.nopossiblebus.activies.personalcenter.mykucun;

import android.content.Context;

import com.nopossiblebus.entity.bean.MyKucunDataBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MykucunContract {
    interface View extends BaseView {
        void setData(MyKucunDataBean bean);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
