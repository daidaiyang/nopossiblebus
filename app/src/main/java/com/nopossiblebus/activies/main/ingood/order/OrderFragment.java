package com.nopossiblebus.activies.main.ingood.order;


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

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.IngoodorderItemAdapter;
import com.nopossiblebus.dialog.TakeOrderDetailDialog;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import retrofit2.http.GET;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderFragment extends MVPBaseFragment<OrderContract.View, OrderPresenter> implements OrderContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.ingood_order_rb_all)
    RadioButton ingoodOrderRbAll;
    @BindView(R.id.ingood_order_rb_untake)
    RadioButton ingoodOrderRbUntake;
    @BindView(R.id.ingood_order_unevaluate)
    RadioButton ingoodOrderUnevaluate;
    @BindView(R.id.ingood_order_rb_finish)
    RadioButton ingoodOrderRbFinish;
    @BindView(R.id.ingood_order_rg)
    RadioGroup ingoodOrderRg;
    @BindView(R.id.ingood_order_rga_recy)
    RecyclerView ingoodOrderRgaRecy;
    @BindView(R.id.ingood_order_rga)
    BGARefreshLayout ingoodOrderRga;
    Unbinder unbinder;

    private List<String> mData;
    private IngoodorderItemAdapter mAdapter;
    private TakeOrderDetailDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingood_order, container, false);
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
        ingoodOrderRga.setDelegate(this);
        dialog = new TakeOrderDetailDialog(getContext());
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(),true);
        holder.setLoadingMoreText("正在加载中");
        ingoodOrderRga.setRefreshViewHolder(holder);
        ingoodOrderRga.setIsShowLoadingMoreView(true);
        ingoodOrderRgaRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        ingoodOrderRgaRecy.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.VERTICAL,
                (int) getContext().getResources().getDimension(R.dimen.x20),
                getContext().getResources().getColor(R.color.background)));
        mAdapter = new IngoodorderItemAdapter(getContext(),mData);
        mAdapter.setClickListener(clickListener);
        ingoodOrderRgaRecy.setAdapter(mAdapter);
    }



    private IngoodorderItemAdapter.OnItemClickListener clickListener = new IngoodorderItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if (dialog == null){
                dialog = new TakeOrderDetailDialog(getContext());
            }
            dialog.show();
        }

        @Override
        public void onOperationClick(View v, int position) {

        }
    };
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
}
