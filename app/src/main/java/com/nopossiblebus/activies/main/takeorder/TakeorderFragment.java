package com.nopossiblebus.activies.main.takeorder;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.TakeOrderItemAdapter;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TakeorderFragment extends MVPBaseFragment<TakeorderContract.View, TakeorderPresenter> implements TakeorderContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, TakeOrderItemAdapter.OnItemClickListener {

    @BindView(R.id.takeorder_score)
    TextView takeorderScore;
    @BindView(R.id.takeorder_num)
    TextView takeorderNum;
    @BindView(R.id.takeorder_money)
    TextView takeorderMoney;
    @BindView(R.id.takeorder_rbcanTake)
    RadioButton takeorderRbcanTake;
    @BindView(R.id.takeorder_needSend)
    RadioButton takeorderNeedSend;
    @BindView(R.id.takeorder_rbfinish)
    RadioButton takeorderRbfinish;
    @BindView(R.id.takeorder_rg)
    RadioGroup takeorderRg;
    @BindView(R.id.takeorder_recycler)
    RecyclerView takeorderRecycler;
    @BindView(R.id.takeorder_bgaLayout)
    BGARefreshLayout takeorderBgaLayout;
    Unbinder unbinder;

    private TakeOrderItemAdapter mAdapter;
    private List<String> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_takeorder, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        takeorderBgaLayout.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(),true);
        holder.setLoadingMoreText("加载中");
        takeorderBgaLayout.setIsShowLoadingMoreView(true);
        takeorderBgaLayout.setRefreshViewHolder(holder);
        takeorderRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        takeorderRecycler.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.VERTICAL,
                (int)getResources().getDimension(R.dimen.x10),
                getResources().getColor(R.color.background)));
        mAdapter = new TakeOrderItemAdapter(getContext(),mData);
        takeorderRecycler.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onItemClick(View v, int position) {
        Log.d("ssssssssssssssssssss","itemitemitemitemitem");
    }

    @Override
    public void onTakeClick(View v, int position) {
        Log.d("ssssssssssssssssssss","taketaketaketaketaketake");
    }
}
