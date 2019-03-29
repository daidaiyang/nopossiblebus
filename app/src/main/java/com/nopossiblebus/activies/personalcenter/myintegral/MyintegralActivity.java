package com.nopossiblebus.activies.personalcenter.myintegral;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.MyIntegralAdapter;
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

public class MyintegralActivity extends MVPBaseActivity<MyintegralContract.View, MyintegralPresenter> implements MyintegralContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myscore_leftscore)
    TextView myscoreLeftscore;
    @BindView(R.id.myscore_allscore)
    TextView myscoreAllscore;
    @BindView(R.id.myscore_usescore)
    TextView myscoreUsescore;
    @BindView(R.id.myscore_all)
    RadioButton myscoreAll;
    @BindView(R.id.myscore_change)
    RadioButton myscoreChange;
    @BindView(R.id.myscore_share)
    RadioButton myscoreShare;
    @BindView(R.id.myscore_changefood)
    RadioButton myscoreChangefood;
    @BindView(R.id.myscore_recy)
    RecyclerView myscoreRecy;

    private List<String> mData;
    private MyIntegralAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myintegral);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("我的积分");
        mData = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            mData.add("");
        }
        mAdapter = new MyIntegralAdapter(getContext(),mData);
        myscoreRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        myscoreRecy.setAdapter(mAdapter);
    }

    @OnClick({R.id.title_back, R.id.myscore_all, R.id.myscore_change, R.id.myscore_share, R.id.myscore_changefood})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.myscore_all:
                break;
            case R.id.myscore_change:
                break;
            case R.id.myscore_share:
                break;
            case R.id.myscore_changefood:
                break;
        }
    }
}
