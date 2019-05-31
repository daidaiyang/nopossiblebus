package com.nopossiblebus.activies.main.togood.supplygood;


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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.gooddetail.GooddetailActivity;
import com.nopossiblebus.activies.togoodapplydetail.TogoodapplydetailActivity;
import com.nopossiblebus.adapter.SupplyGoodAdapter;
import com.nopossiblebus.entity.bean.ApplyOrderDataBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.IntentUtil;
import com.nopossiblebus.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

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

public class SupplygoodFragment extends MVPBaseFragment<SupplygoodContract.View, SupplygoodPresenter> implements SupplygoodContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.supplygood_apply)
    TextView supplygoodApply;
    @BindView(R.id.supplygood_nopower)
    LinearLayout supplygoodNopower;
    @BindView(R.id.supplygood_nownum)
    TextView supplygoodNownum;
    @BindView(R.id.supplygood_todaymoney)
    TextView supplygoodTodaymoney;
    @BindView(R.id.supplygood_allmoney)
    TextView supplygoodAllmoney;
    @BindView(R.id.supplygood_recy)
    RecyclerView supplygoodRecy;
    @BindView(R.id.supplygood_getpower)
    LinearLayout supplygoodGetpower;
    Unbinder unbinder;
    @BindView(R.id.no_img)
    ImageView noImg;
    @BindView(R.id.no_txt)
    TextView noTxt;
    @BindView(R.id.no_root)
    RelativeLayout noRoot;
    @BindView(R.id.apply_shouquan)
    TextView applyShouquan;
    @BindView(R.id.nodata_ll)
    LinearLayout nodataLl;
    @BindView(R.id.bgaLayout)
    BGARefreshLayout bgaLayout;


    private SupplyGoodAdapter mAdapter;
    private List<ProductListBean> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_togood_supplygood, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mData = new ArrayList<>();
        bgaLayout.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(),true);
        holder.setLoadingMoreText("加载中...");
        bgaLayout.setRefreshViewHolder(holder);
        mAdapter = new SupplyGoodAdapter(getContext(), mData);
        supplygoodRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        supplygoodRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClick);
        bgaLayout.beginRefreshing();
    }

    @Override
    public void setData(List<ProductListBean> list) {
            mData.clear();
            mData.addAll(list);
            mAdapter.notifyDataSetChanged();
            bgaLayout.endRefreshing();
    }

    @Override
    public void setMoreDate(List<ProductListBean> list) {
            mData.addAll(mData.size(),list);
            mAdapter.notifyDataSetChanged();
            bgaLayout.endLoadingMore();

    }

    SupplyGoodAdapter.OnItemClickListener onItemClick = new SupplyGoodAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            ProductListBean productListBean = mData.get(position);
            EventBus.getDefault().postSticky(productListBean);
            IntentUtil.startActivity(getContext(), GooddetailActivity.class);
        }
    };


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
            mPresenter.getProductList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.getProductListMore();
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
