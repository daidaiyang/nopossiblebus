package com.nopossiblebus.activies.main.ingood.cart;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.confirorder.ConfirorderActivity;
import com.nopossiblebus.activies.identify.IdentifyActivity;
import com.nopossiblebus.adapter.IngoodcartItemAdapter;
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
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CartFragment extends MVPBaseFragment<CartContract.View, CartPresenter> implements CartContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.ingood_cart_manage)
    TextView ingoodCartManage;
    @BindView(R.id.ingood_cart_check)
    CheckBox ingoodCartCheck;
    @BindView(R.id.ingood_cart_num)
    TextView ingoodCartNum;
    @BindView(R.id.ingood_cart_allprice)
    TextView ingoodCartAllprice;
    @BindView(R.id.ingood_cart_account)
    TextView ingoodCartAccount;
    Unbinder unbinder;
    @BindView(R.id.ingood_cart_bga_recy)
    RecyclerView ingoodCartBgaRecy;
    @BindView(R.id.ingood_cart_bga)
    BGARefreshLayout ingoodCartBga;

    private List<String> mData;
    private IngoodcartItemAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingood_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        
        return view;
    }

    private void initView() {
        ingoodCartBga.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(),true);
        ingoodCartBga.setRefreshViewHolder(holder);
        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时显示加载更多控件
        ingoodCartBga.setIsShowLoadingMoreView(true);
        // 设置正在加载更多时的文本
        holder.setLoadingMoreText("加载中...");

        ingoodCartBgaRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        ingoodCartBgaRecy.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.VERTICAL,
                (int) getContext().getResources().getDimension(R.dimen.x20),
                getContext().getResources().getColor(R.color.background)));
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new IngoodcartItemAdapter(getContext(),mData);
        ingoodCartBgaRecy.setAdapter(mAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ingood_cart_manage, R.id.ingood_cart_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ingood_cart_manage:
                break;
            case R.id.ingood_cart_account:
                Intent intent = new Intent(getContext(), ConfirorderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
}
