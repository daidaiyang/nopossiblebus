package com.nopossiblebus.activies.gooddetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.BaseImageList;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.AppUtil;
import com.nopossiblebus.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GooddetailActivity extends MVPBaseActivity<GooddetailContract.View, GooddetailPresenter> implements GooddetailContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.good_banner)
    Banner goodBanner;
    @BindView(R.id.good_title)
    TextView goodTitle;
    @BindView(R.id.good_xiao_price)
    TextView goodXiaoPrice;
    @BindView(R.id.good_jin_price)
    TextView goodJinPrice;
    @BindView(R.id.good_addcart)
    TextView goodAddcart;
    @BindView(R.id.good_name)
    TextView goodName;
    @BindView(R.id.good_peiliao)
    TextView goodPeiliao;
    @BindView(R.id.good_num)
    TextView goodNum;
    @BindView(R.id.good_web)
    WebView goodWeb;

    private List<String> imageList;

    private ProductListBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooddetail);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        title.setText("商品详情");
        imageList = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras==null){
            goodAddcart.setVisibility(View.GONE);
        }else {
            goodAddcart.setVisibility(View.VISIBLE);
        }

    }

    private void initData(){
        List<BaseImageList> images_list = bean.getImages_list();
        if (images_list!=null&&images_list.size()>0){
            for (int i=0;i<images_list.size();i++){
                imageList.add(images_list.get(i).getUrl());
            }
        }
        goodBanner.setImages(imageList);
        goodBanner.setImageLoader(new GlideImageLoader());
        goodBanner.setIndicatorGravity(BannerConfig.RIGHT);
        goodBanner.setDelayTime(2000);
        goodBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        goodBanner.start();
        goodTitle.setText(bean.getTitle());
        goodXiaoPrice.setText("￥"+ AppUtil.get2xiaoshu(bean.getSell_price()));
        goodJinPrice.setText("￥"+AppUtil.get2xiaoshu(bean.getStock_price()));
        goodName.setText(bean.getBrand());
        goodPeiliao.setText("");
        goodNum.setText(bean.getCo()==null?"":bean.getCo());
    }

    @OnClick({R.id.title_back, R.id.good_addcart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                GooddetailActivity.this.finish();
                break;
            case R.id.good_addcart:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void dealEvent(ProductListBean bean){
        this.bean = bean;
        initData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
