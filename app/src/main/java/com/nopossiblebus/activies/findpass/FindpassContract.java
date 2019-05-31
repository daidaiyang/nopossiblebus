package com.nopossiblebus.activies.findpass;


import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FindpassContract {
    interface View extends BaseView {
        void setTimer(int leftTime);
        void nextUi();
        void findSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
