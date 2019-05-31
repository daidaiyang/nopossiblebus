package com.nopossiblebus.activies.personalcenter;

import android.content.Context;

import com.nopossiblebus.entity.bean.UserLoginData;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalcenterContract {
    interface View extends BaseView {
        void setUserData(UserLoginData userLoginData);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
