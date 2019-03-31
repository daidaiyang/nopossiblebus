package com.nopossiblebus.activies.onekeysaveorder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.OneKeyLeftItemAdapter;
import com.nopossiblebus.adapter.OneKeyRightItemAdapter;
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

public class OnekeysaveorderActivity extends MVPBaseActivity<OnekeysaveorderContract.View, OnekeysaveorderPresenter> implements OnekeysaveorderContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.empty_content)
    LinearLayout emptyContent;
    @BindView(R.id.onekey_leftRecy)
    RecyclerView onekeyLeftRecy;
    @BindView(R.id.onekey_rightRecy)
    RecyclerView onekeyRightRecy;
    @BindView(R.id.onekey_selectAll)
    CheckBox onekeySelectAll;
    @BindView(R.id.onekey_num)
    TextView onekeyNum;
    @BindView(R.id.onekey_price)
    TextView onekeyPrice;
    @BindView(R.id.onekey_confir)
    TextView onekeyConfir;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.title)
    TextView title;

    private OneKeyRightItemAdapter rightAdapter;
    private List<String> rightData;
    private OneKeyLeftItemAdapter leftAdapter;
    private List<String> leftData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onekeysaveorder);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("一键下单");
        initData();
        rightAdapter = new OneKeyRightItemAdapter(getContext(), rightData);
        leftAdapter = new OneKeyLeftItemAdapter(getContext(), leftData);
        rightAdapter.setClickListener(rightItemClick);
        leftAdapter.setClickListener(leftItemClick);
        onekeyLeftRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        onekeyRightRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        onekeyLeftRecy.setAdapter(leftAdapter);
        onekeyRightRecy.setAdapter(rightAdapter);
    }

    private void initData() {
        rightData = new ArrayList<>();
        rightData.add("");
        rightData.add("");
        rightData.add("");
        leftData = new ArrayList<>();
        leftData.add("");
        leftData.add("");
        leftData.add("");
    }

    @OnClick({R.id.title_back, R.id.onekey_selectAll, R.id.onekey_confir})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                OnekeysaveorderActivity.this.finish();
                break;
            case R.id.onekey_selectAll:
                break;
            case R.id.onekey_confir:
                break;
        }
    }

    OneKeyLeftItemAdapter.OnItemClickListener leftItemClick = new OneKeyLeftItemAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {

        }
    };
    OneKeyRightItemAdapter.OnItemClickListener rightItemClick = new OneKeyRightItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {

        }

        @Override
        public void onSubClick(View v, int position) {

        }

        @Override
        public void onPlusClick(View v, int position) {

        }
    };
}
