package com.nopossiblebus.activies.main;

import android.content.Context;

import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
