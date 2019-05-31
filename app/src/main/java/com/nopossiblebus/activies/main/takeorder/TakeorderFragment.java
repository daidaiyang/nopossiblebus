package com.nopossiblebus.activies.main.takeorder;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.nopossiblebus.dialog.IdentifyDialog;
import com.nopossiblebus.dialog.TakeOrderDetailDialog;
import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.RecycleViewDivider;

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
    private List<OrderListBean> mData;
    private TakeOrderDetailDialog dialog;

    private IdentifyDialog identifyDialog = null;

    private String status ="2";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_takeorder, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        identifyDialog = new IdentifyDialog(getContext());
        mData = new ArrayList<>();
        takeorderBgaLayout.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(), true);
        holder.setLoadingMoreText("加载中");
        takeorderBgaLayout.setIsShowLoadingMoreView(true);
        takeorderBgaLayout.setRefreshViewHolder(holder);
        takeorderRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        takeorderRecycler.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL,
                (int) getResources().getDimension(R.dimen.x10),
                getResources().getColor(R.color.background)));
        mAdapter = new TakeOrderItemAdapter(getContext(), mData);
        takeorderRecycler.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
        takeorderRg.setOnCheckedChangeListener(checkedChange);
        takeorderBgaLayout.beginRefreshing();
    }


    @Override
    public void setData(List<OrderListBean> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        takeorderBgaLayout.endRefreshing();
    }

    @Override
    public void setMoreData(List<OrderListBean> data) {
        mData.addAll(mData.size(),data);
        mAdapter.notifyDataSetChanged();
        takeorderBgaLayout.endLoadingMore();
    }



    private RadioGroup.OnCheckedChangeListener checkedChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            refreshData(checkedId);
        }
    };

    private void refreshData(int id){
        switch (id){
            case R.id.takeorder_rbcanTake:
                status ="2";
                break;
            case R.id.takeorder_needSend:
                status="3";
                break;
            case R.id.takeorder_rbfinish:
                status="9";
                break;
        }
        takeorderBgaLayout.beginRefreshing();
    }

    @Override
    public void refresh() {
        takeorderBgaLayout.beginRefreshing();
    }

    @Override
    public void onItemClick(View v, int position) {
        if (dialog == null) {
            dialog = new TakeOrderDetailDialog(getContext(),getThis());
        }
        OrderListBean orderListBean = mData.get(position);
        dialog.setData(orderListBean);
        dialog.show();
    }

    @Override
    public void onTakeClick(View v, int position) {
        String tag = (String) v.getTag();
        OrderListBean orderListBean = mData.get(position);
        String id = orderListBean.getId();
        if (tag .equals("2")){
            mPresenter.takeOrder(id);
        }else if (tag.equals("3")){
            mPresenter.deliverOrder(id);
        }
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getOrderlist(status);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.getMoreOrderList(status);
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
