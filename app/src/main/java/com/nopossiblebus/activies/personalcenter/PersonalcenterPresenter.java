package com.nopossiblebus.activies.personalcenter;

import android.content.Context;

import com.nopossiblebus.entity.api.GetUserDetailApi;
import com.nopossiblebus.entity.bean.UserLoginData;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalcenterPresenter extends BasePresenterImpl<PersonalcenterContract.View> implements PersonalcenterContract.Presenter{


    public void getInfo(){
        GetUserDetailApi getUserDetailApi = new GetUserDetailApi(getUser,mView.getThis());
        mView.getManager().doHttpDeal(getUserDetailApi);
    }


    private HttpOnNextListener<UserLoginData> getUser = new HttpOnNextListener<UserLoginData>() {
        @Override
        public void onNext(UserLoginData userLoginData) {
            mView.setUserData(userLoginData);
        }
    };
}
