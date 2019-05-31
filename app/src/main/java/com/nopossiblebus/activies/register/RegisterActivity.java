package com.nopossiblebus.activies.register;


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
 * 邮箱 784787081@qq.com
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView titleTxt;
    @BindView(R.id.register_username)
    EditText registerUsername;
    @BindView(R.id.register_code)
    EditText registerCode;
    @BindView(R.id.register_getcode)
    TextView registerGetcode;
    @BindView(R.id.register_next)
    TextView registerNext;
    @BindView(R.id.register_checked)
    CheckBox registerChecked;
    @BindView(R.id.register_register_agreement)
    TextView registerRegisterAgreement;
    @BindView(R.id.register_secret_agreement)
    TextView registerSecretAgreement;
    @BindView(R.id.register_agreement_ll)
    LinearLayout registerAgreementLl;
    @BindView(R.id.register_step1)
    LinearLayout registerStep1;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_seepassword)
    CheckBox registerSeepassword;
    @BindView(R.id.register_input_password)
    LinearLayout registerInputPassword;
    @BindView(R.id.register_password_again)
    EditText registerPasswordAgain;
    @BindView(R.id.register_seepassword_again)
    CheckBox registerSeepasswordAgain;
    @BindView(R.id.register_input_password_again)
    LinearLayout registerInputPasswordAgain;
    @BindView(R.id.register_step2)
    LinearLayout registerStep2;
    @BindView(R.id.login)
    LinearLayout toLogin;


    private boolean isgetCode = false;


    private String mobile = "";
    private String code = "";
    private String password = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("注册");
        registerNext.setClickable(false);
        registerNext.setText("下一步");
        registerCode.addTextChangedListener(codeWatch);
        registerPassword.setTransformationMethod(new PasswordTransformationMethod());
        registerPasswordAgain.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick({R.id.title_back, R.id.register_getcode, R.id.register_next,
            R.id.register_checked, R.id.register_register_agreement, R.id.register_seepassword_again,
            R.id.register_seepassword,R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                if (registerNext.getText().toString().equals("注册")) {
                    registerStep1.setVisibility(View.VISIBLE);
                    registerStep2.setVisibility(View.GONE);
                    registerNext.setText("下一步");
                } else {
                    RegisterActivity.this.finish();
                }
                break;
            case R.id.register_getcode:
                getCode();
                break;
            case R.id.register_next:
                if (registerNext.getText().toString().equals("下一步")) {
                    nextStep();
                } else {
                    againPass();
                }
                break;
            case R.id.register_register_agreement:
                registerChecked.setChecked(!registerChecked.isChecked());
                break;
            case R.id.register_seepassword:
                //查看密码
                if (registerSeepassword.isChecked()) {
                    registerPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    registerPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                break;
            case R.id.register_seepassword_again:
                //查看确认密码
                if (registerSeepasswordAgain.isChecked()) {
                    registerPasswordAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    registerPasswordAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.login:
                registerSuccess();
                break;
        }
    }

    //确认密码
    private void againPass() {
        String password1 = registerPassword.getText().toString();
        String password2 = registerPasswordAgain.getText().toString();
        if (password1.equals(password2)&&!password1.equals("")) {
            mPresenter.userEnroll(mobile,code,password1);
        } else {
            ToastUtil.showBottomToast(getContext(), "轻确保两次输入的密码一致后继续");
        }

    }

    //下一步
    private void nextStep() {
        String username = registerUsername.getText().toString();
        String code = registerCode.getText().toString();
        mobile = username;
        this.code = code;
        if (registerChecked.isChecked()) {
            mPresenter.verfityCode(username, code);
        } else {
            ToastUtil.showBottomToast(getContext(), "请先同意注册协议和隐私协议后继续");
        }

    }

    //确认密码
    public void nextUi() {
        registerStep1.setVisibility(View.GONE);
        registerStep2.setVisibility(View.VISIBLE);
        registerNext.setText("注册");
        registerNext.setClickable(true);
    }

    @Override
    public void registerSuccess() {
        RegisterActivity.this.finish();
    }

    //获取验证码
    private void getCode() {
        String username = registerUsername.getText().toString();
        if (RegexUtils.checkMobile(username)) {
            mPresenter.getCode(username);
        } else {
            ToastUtil.showBottomToast(getContext(), "请输入正确的手机号码");
        }

    }

    //倒计时
    public void setTimer(int leftTime) {
        isgetCode = true;
        if (leftTime < 0) {
            registerGetcode.setText("重新获取验证码");
            registerGetcode.setClickable(true);
        } else {
            registerGetcode.setClickable(false);
            registerGetcode.setText(String.format("%d秒后重新获取", leftTime));
        }
    }


    TextWatcher codeWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 6 && isgetCode && registerUsername.getText().toString().length() == 11) {
                registerNext.setClickable(true);
                registerNext.setBackgroundResource(R.drawable.rect_circle_x42_0f);
            } else {
                registerNext.setClickable(false);
                registerNext.setBackgroundResource(R.drawable.rect_circle_x63_cc);
            }
        }
    };

}
