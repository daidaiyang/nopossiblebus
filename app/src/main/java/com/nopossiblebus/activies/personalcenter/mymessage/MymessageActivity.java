package com.nopossiblebus.activies.personalcenter.mymessage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MymessageActivity extends MVPBaseActivity<MymessageContract.View, MymessagePresenter> implements MymessageContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.message_noti_num)
    TextView messageNotiNum;
    @BindView(R.id.message_noti)
    RelativeLayout messageNoti;
    @BindView(R.id.message_newgood_num)
    TextView messageNewgoodNum;
    @BindView(R.id.message_newgood)
    RelativeLayout messageNewgood;
    @BindView(R.id.message_activity_num)
    TextView messageActivityNum;
    @BindView(R.id.message_activity)
    RelativeLayout messageActivity;
    @BindView(R.id.message_tui_num)
    TextView messageTuiNum;
    @BindView(R.id.message_tui)
    RelativeLayout messageTui;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("消息");
    }

    @OnClick({R.id.title_back, R.id.message_noti, R.id.message_newgood, R.id.message_activity, R.id.message_tui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.message_noti:
                break;
            case R.id.message_newgood:
                break;
            case R.id.message_activity:
                break;
            case R.id.message_tui:
                break;
        }
    }
}
