package com.nopossiblebus.activies.main.ingood.cart;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.confirorder.ConfirorderActivity;
import com.nopossiblebus.activies.gooddetail.GooddetailActivity;
import com.nopossiblebus.adapter.IngoodcartItemAdapter;
import com.nopossiblebus.entity.bean.ShopCarProductBean;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.AppUtil;
import com.nopossiblebus.utils.IntentUtil;
import com.nopossiblebus.utils.RecycleViewDivider;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.bottom)
    RelativeLayout bottom;

    private List<ShopCarProductBean> mData;
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
        BGARefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(), true);
        ingoodCartBga.setRefreshViewHolder(holder);
        // 设置正在加载更多时的文本
        holder.setLoadingMoreText("加载中...");
        ingoodCartBgaRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        ingoodCartBgaRecy.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.VERTICAL,
                (int) getContext().getResources().getDimension(R.dimen.x20),
                getContext().getResources().getColor(R.color.background)));
        mData = new ArrayList<>();
        mAdapter = new IngoodcartItemAdapter(getContext(), mData);
        mAdapter.setClickListener(clickListener);
        ingoodCartBgaRecy.setAdapter(mAdapter);
        ingoodCartCheck.setChecked(false);
        mPresenter.getShopCarData();
    }


    /**
     * 首次加载数据
     *
     * @param data
     */
    public void initData(List<ShopCarProductBean> data) {
        mData.clear();
        mData.addAll(data);
        if (mData.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            bottom.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
        countPrice();
    }

    /**
     * 刷新数据
     *
     * @param data
     */
    public void refreshData(List<ShopCarProductBean> data) {
        mData.clear();
        mData.addAll(data);
        if (mData.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            bottom.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
        ingoodCartBga.endRefreshing();
        countPrice();
    }

    /**
     * 加载数据
     *
     * @param data
     */
    public void loadData(List<ShopCarProductBean> data) {
        mData.addAll(mData.size(), data);
        mAdapter.notifyDataSetChanged();
        ingoodCartBga.endLoadingMore();
        countPrice();
    }


    private void countPrice() {
        double allPrice = 0;
        int num = 0;
        for (ShopCarProductBean bean : mData
                ) {
            if (bean.isChecked()) {
                num++;
                double money = Double.valueOf(bean.getMoney());
                allPrice += money;
            }
        }
        ingoodCartNum.setText(String.format("已选%d件，合计：",num));
        ingoodCartAllprice.setText(AppUtil.get2xiaoshu(allPrice));
    }

    private void changeData() {
        List<ShopCarProductBean> data = new ArrayList<>();
        for (ShopCarProductBean bean : mData
                ) {
            data.add(bean);
        }
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        countPrice();
    }

    private void checkAllProduct() {
        List<ShopCarProductBean> data = new ArrayList<>();
        for (ShopCarProductBean bean : mData) {
            bean.setChecked(ingoodCartCheck.isChecked());
            data.add(bean);
        }
        this.mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    private IngoodcartItemAdapter.OnItemClickListener clickListener = new IngoodcartItemAdapter.OnItemClickListener() {
        @Override
        public void onCheckedClick(View v, int position, boolean isChecked) {
            mData.get(position).setChecked(isChecked);
            changeData();
        }

        @Override
        public void onSubClick(View v, int position) {
            Log.d("onSubClick",position+"");
            ShopCarProductBean shopCarProductBean = mData.get(position);
            int num = shopCarProductBean.getNum();
            num--;
            mPresenter.changeNum(shopCarProductBean.getProduct_id(), num);
        }

        @Override
        public void onPlusClick(View v, int position) {
            Log.d("onPlusClick",position+"");
            ShopCarProductBean shopCarProductBean = mData.get(position);
            int num = shopCarProductBean.getNum();
            num++;
            mPresenter.changeNum(shopCarProductBean.getProduct_id(), num);
        }

        @Override
        public void onItemClick(View v, int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("product", mData.get(position));
            IntentUtil.startActivity(getContext(), GooddetailActivity.class, bundle);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ingood_cart_manage, R.id.ingood_cart_account,R.id.ingood_cart_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ingood_cart_manage:

                break;
            case R.id.ingood_cart_account:
                count();
                break;
            case R.id.ingood_cart_check:
                checkAllProduct();
                countPrice();
                break;
        }
    }

    private void count() {
        List<ShopCarProductBean> list = new ArrayList<>();
        for (int i=0;i<mData.size();i++){
            ShopCarProductBean shopCarProductBean = mData.get(i);
            if (shopCarProductBean.isChecked){
                list.add(shopCarProductBean);
            }
        }
        IntentUtil.startActivity(getContext(),ConfirorderActivity.class);
        EventBus.getDefault().postSticky(list);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.freshData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.loadData();
        return false;
    }
}
