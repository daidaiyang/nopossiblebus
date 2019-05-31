package com.nopossiblebus.activies.confirorder;


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
import com.nopossiblebus.activies.myaddress.MyAddressActivity;
import com.nopossiblebus.adapter.ConfirOrderItemAdapter;
import com.nopossiblebus.customview.ShadowDrawable;
import com.nopossiblebus.dialog.ConfirmPayDialog;
import com.nopossiblebus.entity.bean.MyAddressListBean;
import com.nopossiblebus.entity.bean.SaveOrderBean;
import com.nopossiblebus.entity.bean.ShopCarProductBean;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.AppUtil;
import com.nopossiblebus.utils.IntentUtil;

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

public class ConfirorderActivity extends MVPBaseActivity<ConfirorderContract.View, ConfirorderPresenter> implements ConfirorderContract.View {

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
    @BindView(R.id.confirmorder_address_rl_no)
    RelativeLayout confirmorderAddressRlNo;
    @BindView(R.id.num)
    TextView num;

    private List<ShopCarProductBean> mData = new ArrayList<>();
    private ConfirOrderItemAdapter mAdapter;
    private MyAddressListBean bean;

    private ConfirmPayDialog payDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirorder);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        title.setText("确认下单");
        mData = new ArrayList<>();
        mAdapter = new ConfirOrderItemAdapter(getContext(), mData);
        confirRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        confirRecy.setNestedScrollingEnabled(false);//禁止RecyclerView滑动
        confirRecy.setAdapter(mAdapter);
        payDialog = new ConfirmPayDialog(getContext());
        payDialog.setOnDialogClickListener(payClick);
        ShadowDrawable.setShadowDrawable(confirAddressRl, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x10),
                0, 0);
        ShadowDrawable.setShadowDrawable(confirmorderAddressRlNo, Color.parseColor("#ffffff"),
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


    @Override
    public void saveOrderSuccess(SaveOrderBean s) {
        if (payDialog == null){
            payDialog = new ConfirmPayDialog(getContext());
            payDialog.setOnDialogClickListener(payClick);
        }
        payDialog.setMoney(s.getTotal_money());
        payDialog.show();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void msgDeal(List<ShopCarProductBean> list) {
        mData.clear();
        mData.addAll(list);
        mAdapter.notifyDataSetChanged();
        int totalNum = 0;
        double totalPrice = 0;
        for (int i = 0; i < mData.size(); i++) {
            ShopCarProductBean shopCarProductBean = mData.get(i);
            int num = shopCarProductBean.getNum();
            totalNum += num;
            String stock_price = shopCarProductBean.getProduct().getStock_price();
            double v = num * Double.valueOf(stock_price);
            totalPrice += v;
        }
        num.setText("共"+totalNum);
        confirPrice.setText("￥"+ AppUtil.get2xiaoshu(totalPrice));
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void addressDeal(MyAddressListBean bean) {
        this.bean = bean;
        confirmorderAddressRlNo.setVisibility(View.GONE);
        confirAddressRl.setVisibility(View.VISIBLE);
        confirName.setText(bean.getContacts());
        confirTel.setText(bean.getPhone());
        confirAddress.setText(bean.getProvince_name() + bean.getCity_name() + bean.getDistrict_name() + bean.getAddress());
    }


    private ConfirmPayDialog.OnDialogClickListener payClick = new ConfirmPayDialog.OnDialogClickListener() {
        @Override
        public void onPayClick(View view, int payType, String money) {
            if (payType == 0) {//支付宝

            } else if (payType == 1) {//微信
                mPresenter.payByWeChat(money);
            }
            payDialog.cancel();
        }
    };


    @OnClick({R.id.confir_address_rl, R.id.confir_price, R.id.title_back, R.id.confirmorder_address_rl_no,R.id.confir_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confir_address_rl:
                break;
            case R.id.confir_price:
                break;
            case R.id.title_back:
                ConfirorderActivity.this.finish();
                break;
            case R.id.confirmorder_address_rl_no:
                IntentUtil.startActivity(this, MyAddressActivity.class);
                break;
            case R.id.confir_order:
                mPresenter.createOrder(bean,mData,"2019-5-22");
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
