package com.nopossiblebus.activies.gooddetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooddetail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("商品详情");
        imageList = new ArrayList<>();
        imageList.add("http://img17.3lian.com/d/file/201703/06/ea0b5efc8ab75167dd7655bcc16defca.jpg");
        imageList.add("http://l.b2b168.com/2015/10/22/14/201510221452093659724.jpg");
        imageList.add("http://img3.duitang.com/uploads/item/201508/26/20150826195235_xzB8K.jpeg");
        goodBanner.setImageLoader(new GlideImageLoader());
        goodBanner.setIndicatorGravity(BannerConfig.RIGHT);
        goodBanner.setDelayTime(2000);
        goodBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        goodBanner.start();
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
}
