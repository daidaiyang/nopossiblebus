package com.nopossiblebus.activies.personalcenter.changeaccount;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ChangeaccountActivity extends MVPBaseActivity<ChangeaccountContract.View, ChangeaccountPresenter> implements ChangeaccountContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.changeaccount_tel_cur)
    TextView changeaccountTelCur;
    @BindView(R.id.changeaccount_code)
    EditText changeaccountCode;
    @BindView(R.id.changeaccount_getcode)
    TextView changeaccountGetcode;
    @BindView(R.id.changeaccount_newtel)
    EditText changeaccountNewtel;
    @BindView(R.id.changeaccount_newpass)
    EditText changeaccountNewpass;
    @BindView(R.id.changeaccount_commit)
    TextView changeaccountCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeaccount);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("更换账号");
    }

    @OnClick({R.id.title_back, R.id.changeaccount_getcode, R.id.changeaccount_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.changeaccount_getcode:
                break;
            case R.id.changeaccount_commit:
                break;
        }
    }
}
