package com.nopossiblebus;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.nopossiblebus.utils.AppUtil;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class MyApplication extends Application {

    private static boolean debug;
    public static Context app;
    private IWXAPI iwxapi;
    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
        debug = true;
        SpeechUtility.createUtility(app,SpeechConstant.APPID+"=5c834d65");
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        iwxapi = WXAPIFactory.createWXAPI(this, AppUtil.APP_ID, true);
        // 将应用的appId注册到微信
        iwxapi.registerApp(AppUtil.APP_ID);
    }


    public static Context getApplication() {
        return app;
    }

    public static boolean isDebug() {
        return debug;
    }
}
