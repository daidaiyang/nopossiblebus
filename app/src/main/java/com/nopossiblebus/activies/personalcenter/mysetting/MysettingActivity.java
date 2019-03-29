package com.nopossiblebus.activies.personalcenter.mysetting;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.personalcenter.setworktime.SetworktimeActivity;
import com.nopossiblebus.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MysettingActivity extends MVPBaseActivity<MysettingContract.View, MysettingPresenter> implements MysettingContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.setting_worktime)
    RelativeLayout settingWorktime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysetting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("设置");
    }

    @OnClick({R.id.title_back, R.id.setting_worktime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.setting_worktime:
                Intent intent = new Intent(this,SetworktimeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
