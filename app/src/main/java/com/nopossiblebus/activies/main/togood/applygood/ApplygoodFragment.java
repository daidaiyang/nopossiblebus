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
import com.nopossiblebus.entity.bean.ApplyOrderDataBean;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.IntentUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;

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
    private List<ApplyOrderDataBean> mData;
    private String status = "";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_togood_applygood, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        EventBus.getDefault().register(this);
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
        mAdapter = new ApplyGoodAdapter(getContext(),mData);
        togoodApplygoodRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemclick);
        requestData(ingoodOrderRg.getCheckedRadioButtonId());
        ingoodOrderRg.setOnCheckedChangeListener(radioChange);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void isFresh(ApplyRefresh refreshBean){
        EventBus.getDefault().removeStickyEvent(ApplyRefresh.class);
        if (refreshBean.isRefresh()){
            requestData(ingoodOrderRg.getCheckedRadioButtonId());
        }
    }


    @Override
    public void setData(BasePageBean<ApplyOrderDataBean> bean) {
        List<ApplyOrderDataBean> data = bean.getData();
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        togoodApplygoodBga.endRefreshing();
    }

    @Override
    public void setMoreData(BasePageBean<ApplyOrderDataBean> bean) {
        List<ApplyOrderDataBean> data = bean.getData();
        mData.addAll(mData.size(),data);
        mAdapter.notifyDataSetChanged();
        togoodApplygoodBga.endLoadingMore();
    }

    @OnClick({R.id.title_back, R.id.applygood_write})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                getActivity().finish();
                break;
            case R.id.applygood_write:
                IntentUtil.startActivityFor(getActivity(),TogoodApplyActivity.class,0x0012);
                break;
        }
    }

    private RadioGroup.OnCheckedChangeListener radioChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            requestData(checkedId);
        }


    };
    private void requestData(int checkedId) {
        switch (checkedId){
            case R.id.togood_rb_all:
                status ="";
                break;
            case R.id.togood_rb_passing:
                status ="0";
                break;
            case R.id.togood_rb_pass:
                status ="1";
                break;
            case R.id.togood_rb_unpass:
                status ="2";
                break;
        }
        togoodApplygoodBga.beginRefreshing();
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getApplyList(status);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.getApplyListMore(status);
        return false;
    }


    private ApplyGoodAdapter.OnItemClickListener onItemclick = new ApplyGoodAdapter.OnItemClickListener() {
        @Override
        public void onItemClcik(View view, int position) {
            ApplyOrderDataBean applyOrderDataBean = mData.get(position);
            EventBus.getDefault().postSticky(applyOrderDataBean);
            IntentUtil.startActivity(getContext(),TogoodapplydetailActivity.class);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

}
