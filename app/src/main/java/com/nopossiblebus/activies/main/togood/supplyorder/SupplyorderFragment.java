package com.nopossiblebus.activies.main.togood.supplyorder;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.TogoodSupplyorderAdapter;
import com.nopossiblebus.dialog.TakeOrderDetailDialog;
import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.mvp.MVPBaseFragment;

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

public class SupplyorderFragment extends MVPBaseFragment<SupplyorderContract.View, SupplyorderPresenter> implements SupplyorderContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.supplyorder_rb_all)
    RadioButton supplyorderRbAll;
    @BindView(R.id.supplyorder_rb_un)
    RadioButton supplyorderRbUn;
    @BindView(R.id.supplyorder_rb_already)
    RadioButton supplyorderRbAlready;
    @BindView(R.id.supplyorder_rg)
    RadioGroup supplyorderRg;
    @BindView(R.id.supplyorder_recy)
    RecyclerView supplyorderRecy;
    Unbinder unbinder;
    @BindView(R.id.bgaLayout)
    BGARefreshLayout bgaLayout;


    private TogoodSupplyorderAdapter mAdapter;
    private List<OrderListBean> mData;
    private TakeOrderDetailDialog dialog;

    private String status = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_togood_supplyorder, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        titleBack.setVisibility(View.GONE);
        title.setText("供货单管理");
        dialog = new TakeOrderDetailDialog(getContext(), getThis());
        mData = new ArrayList<>();
        mAdapter = new TogoodSupplyorderAdapter(getContext(), mData);
        bgaLayout.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(),true);
        holder.setLoadingMoreText("加载中...");
        bgaLayout.setRefreshViewHolder(holder);
        supplyorderRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        supplyorderRecy.setAdapter(mAdapter);
        mAdapter.setClickListener(click);
        supplyorderRg.setOnCheckedChangeListener(checkChange);
        requestData(supplyorderRg.getCheckedRadioButtonId());
    }

    @Override
    public void setData(List<OrderListBean> list) {
        mData.clear();
        mData.addAll(list);
        mAdapter.notifyDataSetChanged();
        bgaLayout.endRefreshing();
    }

    @Override
    public void setMoreData(List<OrderListBean> list) {
        mData.addAll(mData.size(),list);
        mAdapter.notifyDataSetChanged();
        bgaLayout.endLoadingMore();
    }

    private void requestData(int id) {
        switch (id) {
            case R.id.supplyorder_rb_all:
                status = "";
                break;
            case R.id.supplyorder_rb_un:
                status = "6";
                break;
            case R.id.supplyorder_rb_already:
                status = "9";
                break;
        }
        bgaLayout.beginRefreshing();
    }

    TogoodSupplyorderAdapter.OnItemClickListener click = new TogoodSupplyorderAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if (dialog == null){
                dialog = new TakeOrderDetailDialog(getContext(), getThis());
            }
            OrderListBean orderListBean = mData.get(position);
            dialog.setData(orderListBean);
            dialog.show();
        }

        @Override
        public void onOperationClick(View v, int position) {

        }
    };


    private RadioGroup.OnCheckedChangeListener checkChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            requestData(checkedId);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getOrderList(status);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.getMoreOrderList(status);
        return false;
    }
}
