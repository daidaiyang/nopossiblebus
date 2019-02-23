package com.nopossiblebus.activies.identify;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.ConfirOrderItemAdapter;
import com.nopossiblebus.customview.ShadowDrawable;
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

public class IdentifyActivity extends MVPBaseActivity<IdentifyContract.View, IdentifyPresenter> implements IdentifyContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.confir_name)
    TextView confirName;
    @BindView(R.id.confir_tel)
    TextView confirTel;
    @BindView(R.id.confir_address)
    TextView confirAddress;
    @BindView(R.id.confir_address_rl)
    RelativeLayout confirAddressRl;
    @BindView(R.id.confir_recy)
    RecyclerView confirRecy;
    @BindView(R.id.confir_sendtime)
    TextView confirSendtime;
    @BindView(R.id.confir_sendtype)
    TextView confirSendtype;
    @BindView(R.id.confir_comment)
    EditText confirComment;
    @BindView(R.id.confir_order)
    TextView confirOrder;
    @BindView(R.id.confir_price)
    TextView confirPrice;
    @BindView(R.id.confir_plus_ll)
    LinearLayout confirPlusLl;
    @BindView(R.id.confir_price_desc)
    TextView confirPriceDesc;

    private List<String> mData;
    private ConfirOrderItemAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("确认下单");
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new ConfirOrderItemAdapter(getContext(), mData);
        confirRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        confirRecy.setNestedScrollingEnabled(false);//禁止RecyclerView滑动
        confirRecy.setAdapter(mAdapter);
        ShadowDrawable.setShadowDrawable(confirAddressRl, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x10),
                0, 0);
        ShadowDrawable.setShadowDrawable(confirRecy, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x10),
                0, 0);
        ShadowDrawable.setShadowDrawable(confirPlusLl, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x10),
                0, 0);
    }

    @OnClick({R.id.confir_address_rl, R.id.confir_price, R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confir_address_rl:
                break;
            case R.id.confir_price:
                break;
            case R.id.title_back:
                IdentifyActivity.this.finish();
                break;
        }
    }
}
