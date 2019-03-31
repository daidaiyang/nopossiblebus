package com.nopossiblebus.activies.togoodapplydetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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

public class TogoodapplydetailActivity extends MVPBaseActivity<TogoodapplydetailContract.View, TogoodapplydetailPresenter> implements TogoodapplydetailContract.View {


    @BindView(R.id.applydetail_status_img)
    ImageView statusImg;
    @BindView(R.id.applydetail_time)
    TextView time;
    @BindView(R.id.applydetail_status_txt)
    TextView statusTxt;
    @BindView(R.id.applydetail_status_txt2)
    TextView statusTxt2;
    @BindView(R.id.applydetail_recy)
    RecyclerView recy;
    @BindView(R.id.applydetail_name)
    TextView name;
    @BindView(R.id.applydetail_tel)
    TextView tel;
    @BindView(R.id.applydetail_zhizhao)
    ImageView zhizhao;
    @BindView(R.id.applydetail_food)
    ImageView food;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.commit_again)
    TextView commitAgain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togoodapplydetail);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        title.setText("申请详情");
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        TogoodapplydetailActivity.this.finish();
    }
}
