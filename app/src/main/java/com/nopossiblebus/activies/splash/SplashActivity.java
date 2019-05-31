package com.nopossiblebus.activies.splash;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nopossiblebus.R;
import com.nopossiblebus.activies.login.LoginActivity;
import com.nopossiblebus.activies.main.MainActivity;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.IntentUtil;
import com.nopossiblebus.utils.SpUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SplashActivity extends MVPBaseActivity<SplashContract.View, SplashPresenter> implements SplashContract.View {


    @BindView(R.id.splash_image)
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Glide.with(this)
                .load(R.drawable.splash)
                .into(imageView);
        //创建Timer对象
        Timer timer = new Timer();
        //创建TimerTask对象
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String token = SpUtils.getString(getContext(), "token");
                if (token==null||token.equals("")){
                    IntentUtil.startActivity(SplashActivity.this,LoginActivity.class);
                }else {
                    IntentUtil.startActivity(SplashActivity.this,MainActivity.class);
                }
                finish();
            }
        };
        //使用timer.schedule（）方法调用timerTask，定时3秒后执行run
        timer.schedule(timerTask, 3000);
    }

}
