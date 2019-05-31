package com.nopossiblebus.activies.personalcenter;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nopossiblebus.R;
import com.nopossiblebus.activies.myaddress.MyAddressActivity;
import com.nopossiblebus.activies.personalcenter.myintegral.MyintegralActivity;
import com.nopossiblebus.activies.personalcenter.mykucun.MykucunActivity;
import com.nopossiblebus.activies.personalcenter.mymessage.MymessageActivity;
import com.nopossiblebus.activies.personalcenter.mysetting.MysettingActivity;
import com.nopossiblebus.activies.personalcenter.mywallet.MywalletActivity;
import com.nopossiblebus.entity.bean.UserDetail;
import com.nopossiblebus.entity.bean.UserLoginData;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.IntentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalcenterActivity extends MVPBaseActivity<PersonalcenterContract.View, PersonalcenterPresenter> implements PersonalcenterContract.View {


    @BindView(R.id.mywallet)
    RelativeLayout mywallet;
    @BindView(R.id.myintegral_txt)
    TextView myintegralTxt;
    @BindView(R.id.myintegral)
    RelativeLayout myintegral;
    @BindView(R.id.mykucun)
    RelativeLayout mykucun;
    @BindView(R.id.myStatistics)
    RelativeLayout myStatistics;
    @BindView(R.id.myAddress)
    RelativeLayout myAddress;
    @BindView(R.id.share)
    RelativeLayout share;
    @BindView(R.id.helpcenter)
    RelativeLayout helpcenter;
    @BindView(R.id.contract_us)
    RelativeLayout contractUs;
    @BindView(R.id.mywallet_txt)
    TextView mywalletTxt;
    @BindView(R.id.myhome)
    ImageView myhome;
    @BindView(R.id.mysetting)
    ImageView mysetting;
    @BindView(R.id.mymessage_img)
    ImageView mymessageImg;
    @BindView(R.id.myssage_circle)
    View myssageCircle;
    @BindView(R.id.mymessage)
    FrameLayout mymessage;
    @BindView(R.id.touxiangImg)
    ImageView touxiangImg;
    @BindView(R.id.nickName)
    TextView nickName;
    private UserDetail userDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalcenter);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPresenter.getInfo();
    }

    @Override
    public void setUserData(UserLoginData userLoginData) {
        this.userDetail = userLoginData.getUser_detail();
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.morenimg);
        options.centerCrop();
        Glide.with(this)
                .load(userDetail.getHead_img_url())
                .apply(options)
                .into(touxiangImg);
        String nick = "";
        if (userDetail.getNick_name()==null||userDetail.getNick_name().equals("")){
            nick = "您还没有设置昵称";
        }else {
            nick = userDetail.getNick_name();
        }
        nickName.setText(nick);
        myintegralTxt.setText(userLoginData.getUser().getIntegral()+"分");

    }

    @OnClick({R.id.mywallet, R.id.myintegral, R.id.mykucun, R.id.myStatistics, R.id.myAddress, R.id.share, R.id.helpcenter, R.id.contract_us, R.id.myhome, R.id.mysetting, R.id.mymessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mywallet:
//                IntentUtil.startActivity(this,MykucunActivity.class);
                break;
            case R.id.myintegral:
                IntentUtil.startActivity(this,MyintegralActivity.class);
                break;
            case R.id.mykucun:
                IntentUtil.startActivity(this,MykucunActivity.class);
                break;
            case R.id.myStatistics:
                break;
            case R.id.myAddress:
                IntentUtil.startActivity(this, MyAddressActivity.class);
                break;
            case R.id.share:
                break;
            case R.id.helpcenter:
                break;
            case R.id.contract_us:
                break;
            case R.id.myhome:
                PersonalcenterActivity.this.finish();
                break;
            case R.id.mysetting:
                Intent intent10 = new Intent(this, MysettingActivity.class);
                startActivity(intent10);
                break;
            case R.id.mymessage:
                Intent intent11 = new Intent(this, MymessageActivity.class);
                startActivity(intent11);
                break;
        }
    }
}
