package com.nopossiblebus.activies.findpass;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.RegexUtils;
import com.nopossiblebus.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class FindpassActivity extends MVPBaseActivity<FindpassContract.View, FindpassPresenter> implements FindpassContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView titleTxt;
    @BindView(R.id.findpass_step1_username)
    EditText step1Username;
    @BindView(R.id.findpass_step1_code)
    EditText step1Code;
    @BindView(R.id.findpass_step1_getcode)
    TextView step1Getcode;
    @BindView(R.id.findpass_step1)
    LinearLayout step1;
    @BindView(R.id.findpass_step2_pass)
    EditText step2Pass;
    @BindView(R.id.findpass_step2_pass_cansee)
    CheckBox step2PassCansee;
    @BindView(R.id.findpass_step2_pass_again)
    EditText step2PassAgain;
    @BindView(R.id.findpass_step2_pass_cansee_again)
    CheckBox sStep2PassCanseeAgain;
    @BindView(R.id.findpass_step2)
    LinearLayout step2;
    @BindView(R.id.findpass_next)
    TextView findpassNext;
    private boolean isgetCode = false;


    private String phone="";
    private String code="";
    private String pwd="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpass);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        titleTxt.setText("找回密码");
        step2Pass.setTransformationMethod(new PasswordTransformationMethod());
        step2PassAgain.setTransformationMethod(new PasswordTransformationMethod());
        findpassNext.setTag(1);
        findpassNext.setClickable(false);
        step1Code.addTextChangedListener(watcher);
    }


    //获取短信验证码
    private void getCode() {
        phone = step1Username.getText().toString();
        if (RegexUtils.checkMobile(phone)){
            mPresenter.getCode(phone);
        }else {
            ToastUtil.showBottomToast("请输入正取的手机号码");
        }

    }
    //倒计时
    public void setTimer(int leftTime) {
        isgetCode = true;
        if (leftTime < 0) {
            step1Getcode.setText("重新获取验证码");
            step1Getcode.setClickable(true);
        } else {
            step1Getcode.setClickable(false);
            step1Getcode.setText(String.format("%d秒后重新获取", leftTime));
        }
    }
    //下一步，输入新密码
    @Override
    public void nextUi() {
        step1.setVisibility(View.GONE);
        step2.setVisibility(View.VISIBLE);
        findpassNext.setText("找回密码");
        findpassNext.setClickable(true);
        findpassNext.setBackgroundResource(R.drawable.rect_circle_x42_0f);
    }

    //验证短信验证码
    private void verfiyCode() {
        phone = step1Username.getText().toString();
        code = step1Code.getText().toString();
        if (RegexUtils.checkMobile(phone)){
            mPresenter.verfityCode(phone,code);
        }else {
            ToastUtil.showBottomToast("请输入正取的手机号码");
        }
    }


    //完成找回密码
    private void toFinishFind() {
       pwd =  step2Pass.getText().toString();
       String pwd2 = step2PassAgain.getText().toString();
       if (pwd.equals(pwd2)&&!pwd.equals("")){

       }else {
           ToastUtil.showBottomToast("两次输入的密码不一致");
       }
    }
    //找回密码成功
    @Override
    public void findSuccess() {
        this.finish();
    }

    @OnClick({R.id.title_back, R.id.findpass_step1_getcode, R.id.findpass_step2_pass_cansee_again,
            R.id.findpass_next,R.id.findpass_step2_pass_cansee})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                if ((int)findpassNext.getTag() == 2){
                    step1.setVisibility(View.VISIBLE);
                    step2.setVisibility(View.GONE);
                    findpassNext.setText("下一步");
                }else {
                    FindpassActivity.this.finish();
                }
                break;
            case R.id.findpass_step1_getcode:
                    getCode();
                break;
            case R.id.findpass_step2_pass_cansee_again:
                if (sStep2PassCanseeAgain.isChecked()) {
                    step2Pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    step2Pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.findpass_step2_pass_cansee:
                if (step2PassCansee.isChecked()) {
                    step2PassAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    step2PassAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.findpass_next:
                    if ((int)findpassNext.getTag() == 1){
                        verfiyCode();
                    }else {
                        toFinishFind();
                    }
                break;
        }
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (isgetCode&&s.length() == 6){
                findpassNext.setClickable(true);
                findpassNext.setBackgroundResource(R.drawable.rect_circle_x42_0f);
            }else {
                findpassNext.setClickable(false);
                findpassNext.setBackgroundResource(R.drawable.rect_circle_x63_cc);
            }
        }
    };
}
