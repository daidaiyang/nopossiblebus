package com.nopossiblebus.activies.personalcenter.myintegral;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.MyIntegralAdapter;
import com.nopossiblebus.entity.bean.MyIntegralBean;
import com.nopossiblebus.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyintegralActivity extends MVPBaseActivity<MyintegralContract.View, MyintegralPresenter> implements MyintegralContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


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
    @BindView(R.id.bgaLayout)
    BGARefreshLayout bgaLayout;
    @BindView(R.id.rg)
    RadioGroup rg;

    private List<MyIntegralBean> mData;
    private MyIntegralAdapter mAdapter;


    private String type = ""; //0:订单交易奖励 1:分享奖励 2:兑换粮草

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
        mAdapter = new MyIntegralAdapter(getContext(), mData);
        bgaLayout.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(), true);
        holder.setLoadingMoreText("加载中...");
        bgaLayout.setRefreshViewHolder(holder);
        myscoreRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        myscoreRecy.setAdapter(mAdapter);
        rg.setOnCheckedChangeListener(onCheckedChange);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getIntegralNum();
        request(rg.getCheckedRadioButtonId());
    }

    @Override
    public void setListData(List<MyIntegralBean> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        bgaLayout.endRefreshing();
    }

    @Override
    public void setMoreListData(List<MyIntegralBean> data) {
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        bgaLayout.endLoadingMore();
    }



    private void request(int id){
        switch (id){
            case R.id.myscore_all:
                type ="";
                break;
            case R.id.myscore_change:
                type="0";
                break;
            case R.id.myscore_share:
                type="1";
                break;
            case R.id.myscore_changefood:
                type="2";
                break;
        }
        bgaLayout.beginRefreshing();
    }

    @OnClick({R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
        }
    }

    private RadioGroup.OnCheckedChangeListener onCheckedChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            request(checkedId);
        }
    };

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getIntegralList(type);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.getMoreIntegralList(type);
        return false;
    }
}
