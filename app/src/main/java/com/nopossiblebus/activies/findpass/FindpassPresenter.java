package com.nopossiblebus.activies.findpass;


import com.nopossiblebus.entity.api.ModifyPwdByPhoneApi;
import com.nopossiblebus.entity.api.SecurityCodeApi;
import com.nopossiblebus.entity.api.SendMsgApi;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.VerificationCountDownTimer;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FindpassPresenter extends BasePresenterImpl<FindpassContract.View> implements FindpassContract.Presenter{

    private VerificationCountDownTimer verificationCountDownTimer;
    //获取短信验证码
    public void getCode(String phone){
        initCountDownTimer();
        SendMsgApi sendMsgApi = new SendMsgApi(sendMsg,mView.getThis());
        sendMsgApi.initData(phone);
        mView.getManager().doHttpDeal(sendMsgApi);
    }
    //校验短信验证码
    public void verfityCode(String mobile,String code){
        SecurityCodeApi securityCodeApi = new SecurityCodeApi(security,mView.getThis());
        securityCodeApi.initData(mobile,code);
        mView.getManager().doHttpDeal(securityCodeApi);
    }

    //找回密码

    public void findPwd(String mobile,String code,String pwd){
        ModifyPwdByPhoneApi modifyPwdByPhoneApi = new ModifyPwdByPhoneApi(modify,mView.getThis());
        modifyPwdByPhoneApi.initData(mobile,code,pwd);
        mView.getManager().doHttpDeal(modifyPwdByPhoneApi);
    }

    private HttpOnNextListener<String> modify = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            mView.findSuccess();
        }
    };

    private HttpOnNextListener<String> security = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            verificationCountDownTimer.timerStart(false);
            mView.nextUi();
        }
    };


    private HttpOnNextListener<String> sendMsg = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
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
