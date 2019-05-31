package com.nopossiblebus.activies.register;


import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterContract {
    interface View extends BaseView {
        void setTimer(int leftTime);
        void nextUi();
        void registerSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
