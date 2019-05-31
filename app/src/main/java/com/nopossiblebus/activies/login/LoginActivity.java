package com.nopossiblebus.activies.login;


import android.content.Intent;
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
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.findpass.FindpassActivity;
import com.nopossiblebus.activies.main.MainActivity;
import com.nopossiblebus.activies.register.RegisterActivity;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.IntentUtil;
import com.nopossiblebus.utils.RegexUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView titleTxt;
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_login)
    TextView loginLogin;
    @BindView(R.id.login_regist)
    TextView loginRegist;
    @BindView(R.id.login_forgetpassword)
    TextView loginForgetpassword;
    @BindView(R.id.login_seepassword)
    CheckBox loginSeepassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("登录");
        loginLogin.setClickable(false);
        loginPassword.setTransformationMethod(new PasswordTransformationMethod());
        loginPassword.addTextChangedListener(watcher);
    }

    @OnClick({R.id.title_back, R.id.login_login, R.id.login_regist, R.id.login_forgetpassword,R.id.login_seepassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.login_login:
                String userName = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();
                mPresenter.login(userName,password);
                break;
            case R.id.login_regist:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_forgetpassword:
                Intent passIntent = new Intent(LoginActivity.this,FindpassActivity.class);
                startActivity(passIntent);
                break;
            case R.id.login_seepassword:
                if (loginSeepassword.isChecked()) {
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }


    @Override
    public void loginSuccess() {
        IntentUtil.startActivity(this,MainActivity.class);
        this.finish();
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
            if (s.length()>0&&RegexUtils.checkMobile(loginUsername.getText().toString())){
                loginLogin.setClickable(true);
                loginLogin.setBackgroundResource(R.drawable.rect_circle_x42_0f);
            }else {
                loginLogin.setClickable(false);
                loginLogin.setBackgroundResource(R.drawable.rect_circle_x63_cc);
            }
        }
    };
}
