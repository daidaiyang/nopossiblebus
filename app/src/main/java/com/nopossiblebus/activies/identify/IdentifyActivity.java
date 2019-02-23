package com.nopossiblebus.activies.identify;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
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

public class IdentifyActivity extends MVPBaseActivity<IdentifyContract.View, IdentifyPresenter> implements IdentifyContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.identify_cancle)
    TextView cancle;
    @BindView(R.id.identify_upyingye_rl)
    RelativeLayout yingyeRl;
    @BindView(R.id.identify_upyingye_img)
    ImageView yingyeImg;
    @BindView(R.id.identify_ypfood_rl)
    RelativeLayout foodRl;
    @BindView(R.id.identify_ypfood_img)
    ImageView foodImg;
    @BindView(R.id.identify_name)
    EditText name;
    @BindView(R.id.identify_tel)
    EditText tel;
    @BindView(R.id.identify_merchname)
    EditText merchname;
    @BindView(R.id.identify_commit)
    TextView commit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("商家认证");
        titleBack.setVisibility(View.GONE);
    }

    @OnClick({R.id.identify_cancle, R.id.identify_upyingye_rl, R.id.identify_upyingye_img, R.id.identify_ypfood_rl, R.id.identify_ypfood_img, R.id.identify_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.identify_cancle:
                break;
            case R.id.identify_upyingye_rl:
                break;
            case R.id.identify_upyingye_img:
                break;
            case R.id.identify_ypfood_rl:
                break;
            case R.id.identify_ypfood_img:
                break;
            case R.id.identify_commit:
                break;
        }
    }
}
