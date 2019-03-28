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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.gooddetail.GooddetailActivity;
import com.nopossiblebus.adapter.SupplyGoodAdapter;
import com.nopossiblebus.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SupplygoodFragment extends MVPBaseFragment<SupplygoodContract.View, SupplygoodPresenter> implements SupplygoodContract.View {


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


    private SupplyGoodAdapter mAdapter;
    private List<String> mData;

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
        for (int i = 0; i < 10; i++) {
            mData.add("");
        }
        mAdapter = new SupplyGoodAdapter(getContext(),mData);
        supplygoodRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        supplygoodRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClick);
    }


    SupplyGoodAdapter.OnItemClickListener onItemClick = new SupplyGoodAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(getContext(),GooddetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
