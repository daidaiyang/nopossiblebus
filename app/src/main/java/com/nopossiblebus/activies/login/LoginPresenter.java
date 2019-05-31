package com.nopossiblebus.activies.login;


import com.nopossiblebus.entity.api.UserLoginApi;
import com.nopossiblebus.entity.bean.UserLoginData;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.LogUtil;
import com.nopossiblebus.utils.SpUtils;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{


    public void login(String name, String password){
        UserLoginApi userLoginApi = new UserLoginApi(loginListener,mView.getThis());
        userLoginApi.setParams(name,password);
        mView.getManager().doHttpDeal(userLoginApi);
    }

    private HttpOnNextListener loginListener = new HttpOnNextListener<UserLoginData>(){

        @Override
        public void onNext(UserLoginData userLoginData) {
            LogUtil.d("UserLoginData",userLoginData.getUser().getToken());
            SpUtils.putString(mView.getContext(),"token",userLoginData.getUser().getToken());
            mView.loginSuccess();
        }
    };
}
