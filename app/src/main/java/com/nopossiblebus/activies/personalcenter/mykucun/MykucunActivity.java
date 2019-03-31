package com.nopossiblebus.activies.personalcenter.mykucun;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.MyKucunRightItemAdapter;
import com.nopossiblebus.adapter.OneKeyLeftItemAdapter;
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

public class MykucunActivity extends MVPBaseActivity<MykucunContract.View, MykucunPresenter> implements MykucunContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.mykucun_num)
    TextView mykucunNum;
    @BindView(R.id.mykuncun_moren)
    RadioButton mykuncunMoren;
    @BindView(R.id.mykuncun_kucunnum)
    RadioButton mykuncunKucunnum;
    @BindView(R.id.mykuncun_kucunnum_img)
    ImageView mykuncunKucunnumImg;
    @BindView(R.id.mykucun_price)
    RadioButton mykucunPrice;
    @BindView(R.id.mykucun_price_img)
    ImageView mykucunPriceImg;
    @BindView(R.id.mykucun_leftrecy)
    RecyclerView mykucunLeftrecy;
    @BindView(R.id.mykucun_rightrecy)
    RecyclerView mykucunRightrecy;


    private OneKeyLeftItemAdapter mLeftAdapter;
    private List<String> mLeftData;

    private MyKucunRightItemAdapter mRightAdapter;
    private List<String> mRightData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mykuncun);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("我的商品库存");
        mLeftData = new ArrayList<>();
        mRightData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mLeftData.add("");
            mRightData.add("");
        }
        mRightAdapter = new MyKucunRightItemAdapter(getContext(), mRightData);
        mLeftAdapter = new OneKeyLeftItemAdapter(getContext(), mLeftData);
        mykucunLeftrecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mykucunRightrecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mykucunLeftrecy.setAdapter(mLeftAdapter);
        mykucunRightrecy.setAdapter(mRightAdapter);

    }

    @OnClick({R.id.title_back, R.id.mykuncun_moren, R.id.mykuncun_kucunnum, R.id.mykuncun_kucunnum_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.mykuncun_moren:
                break;
            case R.id.mykuncun_kucunnum:
                break;
            case R.id.mykuncun_kucunnum_img:
                break;
        }
    }
}
