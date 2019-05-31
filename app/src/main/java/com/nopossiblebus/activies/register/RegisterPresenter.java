package com.nopossiblebus.activies.register;


import com.nopossiblebus.entity.api.SecurityCodeApi;
import com.nopossiblebus.entity.api.SendMsgApi;
import com.nopossiblebus.entity.api.UserEnrollApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.LogUtil;
import com.nopossiblebus.utils.VerificationCountDownTimer;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter{


    private VerificationCountDownTimer verificationCountDownTimer;
    //获取短信验证码
    public void getCode(String mobile){
        initCountDownTimer();
        SendMsgApi sendMsgApi = new SendMsgApi(sendMsg,mView.getThis());
        sendMsgApi.initData(mobile);
        mView.getManager().doHttpDeal(sendMsgApi);
    }
    //校验短信验证码
    public void verfityCode(String mobile,String code){
        SecurityCodeApi securityCodeApi = new SecurityCodeApi(security,mView.getThis());
        securityCodeApi.initData(mobile,code);
        mView.getManager().doHttpDeal(securityCodeApi);
    }


    //校验密码-手机号-短信验证码
    public void userEnroll(String mobile,String code,String password){
        UserEnrollApi userEnrollApi = new UserEnrollApi(enroll,mView.getThis());
        userEnrollApi.setParams(mobile,code,password);
        mView.getManager().doHttpDeal(userEnrollApi);
    }

    private HttpOnNextListener<String> security = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            verificationCountDownTimer.timerStart(false);
            mView.nextUi();
        }
    };


    private HttpOnNextListener<String> enroll = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            mView.registerSuccess();
        }
    };



    private HttpOnNextListener<String> sendMsg = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            LogUtil.e("sssssssss",s);
            verificationCountDownTimer.timerStart(true);
        }
    };


    private void initCountDownTimer(){
        if (VerificationCountDownTimer.FLAG_FIRST_IN&&VerificationCountDownTimer.curMillis+60000>System.currentTimeMillis()){
            setCountDownTimer(VerificationCountDownTimer.curMillis+60000-System.currentTimeMillis());
            verificationCountDownTimer.timerStart(false);
        }else {
            setCountDownTimer(60000);
        }
    }


    private void setCountDownTimer(final long countDownTime){
        verificationCountDownTimer = new VerificationCountDownTimer(countDownTime,100){
            @Override
            public void onTick(long millisUntilFinished) {
                super.onTick(millisUntilFinished);
                if (mView!=null)
                mView.setTimer((int)(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (mView!=null)
                mView.setTimer(-1);
                if (countDownTime != 60000){
                    setCountDownTimer(60000);
                }
            }
        };
    }

}
