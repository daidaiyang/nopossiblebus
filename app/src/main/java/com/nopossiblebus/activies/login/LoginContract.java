package com.nopossiblebus.activies.login;


import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void loginSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
