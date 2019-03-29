package com.nopossiblebus.activies.togoodapply;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.TogoodapplyAdapter;
import com.nopossiblebus.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TogoodApplyActivity extends MVPBaseActivity<TogoodApplyContract.View, TogoodApplyPresenter> implements TogoodApplyContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.togoodapply_name)
    EditText applyName;
    @BindView(R.id.togoodapply_tel)
    EditText applyTel;
    @BindView(R.id.togoodapply_upidcard_zheng_rl)
    RelativeLayout upidcardZhengRl;
    @BindView(R.id.togoodapply_upidcard_img)
    ImageView upidcardImg;
    @BindView(R.id.togoodapply_upidcard_fan_rl)
    RelativeLayout upidcardFanRl;
    @BindView(R.id.togoodapply_idcard_fan_img)
    ImageView applyIdcardFanImg;
    @BindView(R.id.togoodapply_upyingye_rl)
    RelativeLayout upyingyeRl;
    @BindView(R.id.togoodapply_upyingye_img)
    ImageView upyingyeImg;
    @BindView(R.id.togoodapply_ypfood_rl)
    RelativeLayout ypfoodRl;
    @BindView(R.id.togoodapply_ypfood_img)
    ImageView ypfoodImg;
    @BindView(R.id.togoodapply_recy)
    RecyclerView recy;
    @BindView(R.id.togoodapply_recyadd)
    TextView recyadd;
    @BindView(R.id.togoodapply_commit)
    TextView commit;


    private List<String> mData;
    private TogoodapplyAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togoodapply);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("填写申请信息");
        mData = new ArrayList<>();
        mData.add("");
        mAdapter = new TogoodapplyAdapter(getContext(),mData);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy.setNestedScrollingEnabled(false);
        recy.setAdapter(mAdapter);

    }

    @OnClick({R.id.title_back,R.id.togoodapply_upidcard_zheng_rl, R.id.togoodapply_upidcard_img, R.id.togoodapply_upidcard_fan_rl, R.id.togoodapply_idcard_fan_img, R.id.togoodapply_upyingye_rl, R.id.togoodapply_upyingye_img, R.id.togoodapply_ypfood_rl, R.id.togoodapply_ypfood_img, R.id.togoodapply_recyadd, R.id.togoodapply_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                TogoodApplyActivity.this.finish();
                break;
            case R.id.togoodapply_upidcard_zheng_rl:
                break;
            case R.id.togoodapply_upidcard_img:
                break;
            case R.id.togoodapply_upidcard_fan_rl:
                break;
            case R.id.togoodapply_idcard_fan_img:
                break;
            case R.id.togoodapply_upyingye_rl:
                break;
            case R.id.togoodapply_upyingye_img:
                break;
            case R.id.togoodapply_ypfood_rl:
                break;
            case R.id.togoodapply_ypfood_img:
                break;
            case R.id.togoodapply_recyadd:
                List<String> mData1 = mData;
                mData.clear();
                mData1.add("");
                mData.addAll(mData1);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.togoodapply_commit:
                break;
        }
    }
}
