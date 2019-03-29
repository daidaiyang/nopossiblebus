package com.nopossiblebus.activies.personalcenter.changepass;


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

public class ChangepassActivity extends MVPBaseActivity<ChangepassContract.View, ChangepassPresenter> implements ChangepassContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.changepass_old)
    EditText changepassOld;
    @BindView(R.id.changepass_new)
    EditText changepassNew;
    @BindView(R.id.changepass_newagain)
    EditText changepassNewagain;
    @BindView(R.id.changepass_confirm)
    TextView changepassConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("修改密码");
    }

    @OnClick({R.id.title_back, R.id.changepass_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.changepass_confirm:
                break;
        }
    }
}
