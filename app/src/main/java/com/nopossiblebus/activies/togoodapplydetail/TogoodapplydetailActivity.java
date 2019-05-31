package com.nopossiblebus.activies.togoodapplydetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossiblebus.R;
import com.nopossiblebus.adapter.ApplyDetailItemAdapter;
import com.nopossiblebus.entity.bean.ApplyOrderDataBean;
import com.nopossiblebus.entity.bean.ApplyOrderLineBean;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.TimeUtil;

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

public class TogoodapplydetailActivity extends MVPBaseActivity<TogoodapplydetailContract.View, TogoodapplydetailPresenter> implements TogoodapplydetailContract.View {


    @BindView(R.id.applydetail_status_img)
    ImageView statusImg;
    @BindView(R.id.applydetail_time)
    TextView time;
    @BindView(R.id.applydetail_status_txt)
    TextView statusTxt;
    @BindView(R.id.applydetail_status_txt2)
    TextView statusTxt2;
    @BindView(R.id.applydetail_recy)
    RecyclerView recy;
    @BindView(R.id.applydetail_name)
    TextView name;
    @BindView(R.id.applydetail_tel)
    TextView tel;
    @BindView(R.id.applydetail_zhizhao)
    ImageView zhizhao;
    @BindView(R.id.applydetail_food)
    ImageView food;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.commit_again)
    TextView commitAgain;
    @BindView(R.id.info_info)
    TextView infoInfo;


    private ApplyOrderDataBean bean;

    private ApplyDetailItemAdapter mAdapter;
    private List<ApplyOrderLineBean> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togoodapplydetail);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        title.setText("申请详情");
        mData = new ArrayList<>();
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ApplyDetailItemAdapter(getContext(),mData);
        recy.setAdapter(mAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void dealEvent(ApplyOrderDataBean bean) {
        this.bean = bean;
        EventBus.getDefault().removeStickyEvent(ApplyOrderDataBean.class);
        if (bean.getStatus().equals("0")) {
            //审核中
            Glide.with(this)
                    .load(R.drawable.apply_ing)
                    .into(statusImg);
            infoInfo.setBackgroundColor(getResources().getColor(R.color.text_black_0f));
            statusTxt.setText("申请中");
            statusTxt2.setText("正在审核");
            statusTxt.setTextColor(getResources().getColor(R.color.text_black_0f));
        } else if (bean.getStatus().equals("1")) {
            //审核通过+
            Glide.with(this)
                    .load(R.drawable.apply_success)
                    .into(statusImg);
            infoInfo.setBackgroundColor(getResources().getColor(R.color.apply_green));
            statusTxt.setText("已通过");
            statusTxt.setTextColor(getResources().getColor(R.color.apply_green));
            statusTxt2.setText("审核时间:"+ TimeUtil.timeStamp2Date(bean.getUpdate_time(),"yyyy.MM.dd HH.mm:ss"));
        } else {
            //审核失败
            Glide.with(this)
                    .load(R.drawable.apply_faile)
                    .into(statusImg);
            infoInfo.setBackgroundColor(getResources().getColor(R.color.apply_red));
            statusTxt.setText("未通过");
            statusTxt2.setText("审核时间:"+ TimeUtil.timeStamp2Date(bean.getUpdate_time(),"yyyy.MM.dd HH.mm:ss"));
            statusTxt.setTextColor(getResources().getColor(R.color.apply_red));
        }
        List<ApplyOrderLineBean> line_list = bean.getLine_List();
        time.setText(TimeUtil.timeStamp2Date(bean.getCreate_time(),"yyyy.MM.dd HH.mm:ss"));
        mData.clear();
        mData.addAll(line_list);
        mAdapter.notifyDataSetChanged();
        name.setText(bean.getContacts()==null?"":bean.getContacts());
        tel.setText(bean.getPhone()==null?"":bean.getPhone());
        Glide.with(this)
                .load(bean.getBusiness_license())
                .into(zhizhao);
        Glide.with(this)
                .load(bean.getCirculate_license())
                .into(food);

    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        TogoodapplydetailActivity.this.finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
