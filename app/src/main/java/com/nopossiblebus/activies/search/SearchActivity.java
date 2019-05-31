package com.nopossiblebus.activies.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.gooddetail.GooddetailActivity;
import com.nopossiblebus.adapter.SearchGoodItemAdapter;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.IntentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SearchActivity extends MVPBaseActivity<SearchContract.View, SearchPresenter> implements SearchContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.result_desc)
    TextView resultDesc;
    @BindView(R.id.micro)
    ImageView micro;
    @BindView(R.id.first_result)
    RelativeLayout firstResult;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.bgaLayout)
    BGARefreshLayout bgaLayout;

    private SearchGoodItemAdapter mAdapter;
    private List<ProductListBean> mData;
    private String key = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        key = getIntent().getExtras().getString("key");
        result.setText(String.format("\"%s\"", key));
        resultDesc.setText(String.format("您要找的\"%s\"已为您找到", key));
        bgaLayout.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(),true);
        bgaLayout.setRefreshViewHolder(holder);
        mData = new ArrayList<>();
        mAdapter = new SearchGoodItemAdapter(getContext(), mData);
        mAdapter.setmListener(adapterListener);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(mAdapter);
        initData();
    }

    /**
     * 首次刷新数据
     */
    private void initData() {
            bgaLayout.beginRefreshing();
            mPresenter.search("胡大师牌醋");
    }


    /**
     * 首次加载数据
     */
    public void upDataData(List<ProductListBean> mData) {
        this.mData.clear();
        this.mData.addAll(mData);
        mAdapter.notifyDataSetChanged();
        bgaLayout.endLoadingMore();
    }

    /**
     * 刷新数据
     *
     * @param mData
     */
    public void refreshData(List<ProductListBean> mData) {
        this.mData.clear();
        this.mData.addAll(mData);
        mAdapter.notifyDataSetChanged();
        bgaLayout.endRefreshing();
    }

    /**
     * 加载数据
     *
     * @param mData
     */
    public void loadData(List<ProductListBean> mData) {
        this.mData.addAll(this.mData.size(), mData);
        mAdapter.notifyDataSetChanged();
        bgaLayout.endRefreshing();
    }



    SearchGoodItemAdapter.OnItemClickListener adapterListener = new SearchGoodItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("product",mData.get(position));
            IntentUtil.startActivity(getContext(), GooddetailActivity.class,bundle);
        }

        @Override
        public void onAddCartClick(View v, int position) {
            String id = mData.get(position).getId();
            mPresenter.addtocart(id);
        }
    };
    @OnClick(R.id.title_back)
    public void onViewClicked() {
        this.finish();
    }



    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        mPresenter.refresh("胡大师牌醋");
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.load("胡大师牌醋");
        return false;
    }
}
