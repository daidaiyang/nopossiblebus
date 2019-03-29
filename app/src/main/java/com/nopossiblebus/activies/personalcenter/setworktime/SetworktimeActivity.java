package com.nopossiblebus.activies.personalcenter.setworktime;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
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

public class SetworktimeActivity extends MVPBaseActivity<SetworktimeContract.View, SetworktimePresenter> implements SetworktimeContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.setworktime_all)
    RadioButton setworktimeAll;
    @BindView(R.id.setworktime_time)
    RadioButton setworktimeTime;
    @BindView(R.id.setworktime_time_txt)
    TextView setworktimeTimeTxt;
    @BindView(R.id.setworktime_time_start)
    TextView setworktimeTimeStart;
    @BindView(R.id.setworktime_time_end)
    TextView setworktimeTimeEnd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setworktime);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("设置营业时间");
        setworktimeAll.setChecked(true);
        setworktimeTimeTxt.setTextColor(getResources().getColor(R.color.text_black));
    }

    @OnClick({R.id.title_back, R.id.setworktime_all, R.id.setworktime_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.setworktime_all:
                if (setworktimeAll.isChecked()){
                    setworktimeTimeTxt.setTextColor(getResources().getColor(R.color.text_black));
                    setworktimeTime.setChecked(false);
                    setworktimeTimeStart.setTextColor(getResources().getColor(R.color.text_black_99));
                    setworktimeTimeEnd.setTextColor(getResources().getColor(R.color.text_black_99));
                }

                break;
            case R.id.setworktime_time:
                if (setworktimeTime.isChecked()){
                    setworktimeTimeStart.setTextColor(getResources().getColor(R.color.text_black));
                    setworktimeTimeEnd.setTextColor(getResources().getColor(R.color.text_black));
                    setworktimeAll.setChecked(false);
                    setworktimeTimeTxt.setTextColor(getResources().getColor(R.color.text_black_99));
                }
                break;
        }
    }
}
