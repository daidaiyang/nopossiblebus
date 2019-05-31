package com.nopossiblebus.activies.main.ingood.order;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.IngoodorderItemAdapter;
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

    private List<OrderListBean> mData;
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
        ingoodOrderRga.setDelegate(this);
        dialog = new TakeOrderDetailDialog(getContext(), getThis());
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(), true);
        holder.setLoadingMoreText("正在加载中");
        ingoodOrderRga.setRefreshViewHolder(holder);
        ingoodOrderRga.setIsShowLoadingMoreView(true);
        ingoodOrderRgaRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        ingoodOrderRgaRecy.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.VERTICAL,
                (int) getContext().getResources().getDimension(R.dimen.x20),
                getContext().getResources().getColor(R.color.background)));
        mAdapter = new IngoodorderItemAdapter(getContext(), mData);
        mAdapter.setClickListener(clickListener);
        ingoodOrderRgaRecy.setAdapter(mAdapter);
        ingoodOrderRbAll.setChecked(true);
        ingoodOrderRg.setOnCheckedChangeListener(checkedChange);
        ingoodOrderRga.beginRefreshing();
        mPresenter.getList("");
    }


    public void setData(List<OrderListBean> list) {
        ingoodOrderRga.endRefreshing();
        mData.clear();
        mData.addAll(list);
        mAdapter.notifyDataSetChanged();
        ingoodOrderRgaRecy.scrollToPosition(0);
    }

    @Override
    public void setMoreData(List<OrderListBean> list) {
        ingoodOrderRga.endLoadingMore();
        int size = mData.size();
        mData.addAll(size,list);
        mAdapter.notifyDataSetChanged();
        ingoodOrderRgaRecy.scrollToPosition(size);
    }

    private IngoodorderItemAdapter.OnItemClickListener clickListener = new IngoodorderItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if (dialog == null) {
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

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        int checkedRadioButtonId = ingoodOrderRg.getCheckedRadioButtonId();
        switch (checkedRadioButtonId){
            case R.id.ingood_order_rb_all:
                mPresenter.getList("");
                break;
            case R.id.ingood_order_rb_untake:
                mPresenter.getList("7");
                break;
            case R.id.ingood_order_unevaluate:
                mPresenter.getList("8");
                break;
            case R.id.ingood_order_rb_finish:
                mPresenter.getList("9");
                break;
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        int checkedRadioButtonId = ingoodOrderRg.getCheckedRadioButtonId();
        switch (checkedRadioButtonId){
            case R.id.ingood_order_rb_all:
                mPresenter.getMore("");
                break;
            case R.id.ingood_order_rb_untake:
                mPresenter.getMore("7");
                break;
            case R.id.ingood_order_unevaluate:
                mPresenter.getMore("8");
                break;
            case R.id.ingood_order_rb_finish:
                mPresenter.getMore("9");
                break;
        }
        return false;
    }


    private RadioGroup.OnCheckedChangeListener checkedChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.ingood_order_rb_all:
                        mPresenter.getList("");
                        break;
                    case R.id.ingood_order_rb_untake:
                        mPresenter.getList("7");
                        break;
                    case R.id.ingood_order_unevaluate:
                        mPresenter.getList("8");
                        break;
                    case R.id.ingood_order_rb_finish:
                        mPresenter.getList("9");
                        break;
                }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
