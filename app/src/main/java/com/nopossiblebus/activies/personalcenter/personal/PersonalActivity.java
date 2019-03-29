package com.nopossiblebus.activies.personalcenter.personal;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.personalcenter.changeaccount.ChangeaccountActivity;
import com.nopossiblebus.activies.personalcenter.changepass.ChangepassActivity;
import com.nopossiblebus.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalActivity extends MVPBaseActivity<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.personal_avar_img)
    ImageView personalAvarImg;
    @BindView(R.id.personal_avar)
    RelativeLayout personalAvar;
    @BindView(R.id.personal_account_txt)
    TextView personalAccountTxt;
    @BindView(R.id.personal_account)
    RelativeLayout personalAccount;
    @BindView(R.id.personal_changepss)
    RelativeLayout personalChangepss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypersonal);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("个人资料");
    }

    @OnClick({R.id.title_back, R.id.personal_avar, R.id.personal_account, R.id.personal_changepss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.personal_avar:
                break;
            case R.id.personal_account:
                Intent intentaccount = new Intent(this,ChangeaccountActivity.class);
                startActivity(intentaccount);
                break;
            case R.id.personal_changepss:
                Intent intent = new Intent(this,ChangepassActivity.class);
                startActivity(intent);
                break;
        }
    }
}
