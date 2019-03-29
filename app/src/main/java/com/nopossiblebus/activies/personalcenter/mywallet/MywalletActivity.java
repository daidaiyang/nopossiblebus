package com.nopossiblebus.activies.personalcenter.mywallet;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.MyIntegralAdapter;
import com.nopossiblebus.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MywalletActivity extends MVPBaseActivity<MywalletContract.View, MywalletPresenter> implements MywalletContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.mywallet_withdrawable)
    TextView mywalletWithdrawable;
    @BindView(R.id.mywallet_withdrawal)
    TextView mywalletWithdrawal;
    @BindView(R.id.mywallet_balance)
    TextView mywalletBalance;
    @BindView(R.id.mywallet_frozen)
    TextView mywalletFrozen;
    @BindView(R.id.mywallet_all)
    RadioButton mywalletAll;
    @BindView(R.id.mywallet_income)
    RadioButton mywalletIncome;
    @BindView(R.id.mywallet_expenditure)
    RadioButton mywalletExpenditure;
    @BindView(R.id.mywallet_withdraw)
    RadioButton mywalletWithdraw;
    @BindView(R.id.mywallet_recy)
    RecyclerView mywalletRecy;

    private List<String> mData;
    private MyIntegralAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("我的钱包");
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("");
        }
        mAdapter = new MyIntegralAdapter(getContext(),mData);
        mywalletRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mywalletRecy.setAdapter(mAdapter);
    }

    @OnClick({R.id.title_back, R.id.mywallet_all, R.id.mywallet_income, R.id.mywallet_expenditure, R.id.mywallet_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.mywallet_all:
                break;
            case R.id.mywallet_income:
                break;
            case R.id.mywallet_expenditure:
                break;
            case R.id.mywallet_withdraw:
                break;
        }
    }
}
