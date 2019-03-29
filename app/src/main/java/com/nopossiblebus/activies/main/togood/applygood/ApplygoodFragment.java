package com.nopossiblebus.activies.main.togood.applygood;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.togoodapply.TogoodApplyActivity;
import com.nopossiblebus.activies.togoodapplydetail.TogoodapplydetailActivity;
import com.nopossiblebus.adapter.ApplyGoodAdapter;
import com.nopossiblebus.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ApplygoodFragment extends MVPBaseFragment<ApplygoodContract.View, ApplygoodPresenter> implements ApplygoodContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.applygood_write)
    TextView applygoodWrite;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.togood_rb_all)
    RadioButton togoodRbAll;
    @BindView(R.id.togood_rb_passing)
    RadioButton togoodRbPassing;
    @BindView(R.id.togood_rb_pass)
    RadioButton togoodRbPass;
    @BindView(R.id.togood_rb_unpass)
    RadioButton togoodRbUnpass;
    @BindView(R.id.ingood_order_rg)
    RadioGroup ingoodOrderRg;
    @BindView(R.id.togood_applygood_recy)
    RecyclerView togoodApplygoodRecy;
    @BindView(R.id.togood_applygood_bga)
    BGARefreshLayout togoodApplygoodBga;
    Unbinder unbinder;

    private ApplyGoodAdapter mAdapter;
    private List<String> mData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_togood_applygood, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        titleBack.setVisibility(View.GONE);
        title.setText("供货申请");
        togoodApplygoodBga.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(),true);
        holder.setLoadingMoreText("正在加载中");
        togoodApplygoodBga.setRefreshViewHolder(holder);
        togoodApplygoodBga.setIsShowLoadingMoreView(true);
        togoodApplygoodRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mData = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            mData.add("");
        }
        mAdapter = new ApplyGoodAdapter(getContext(),mData);
        togoodApplygoodRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemclick);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.title_back, R.id.applygood_write})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.applygood_write:
                Intent intent = new Intent(getContext(),TogoodApplyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    private ApplyGoodAdapter.OnItemClickListener onItemclick = new ApplyGoodAdapter.OnItemClickListener() {
        @Override
        public void onItemClcik(View view, int position) {
            Intent intent = new Intent(getContext(),TogoodapplydetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };
}
