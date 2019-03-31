package com.nopossiblebus.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.TakeOrderDetailLeftItemAdapter;
import com.nopossiblebus.adapter.TakeOrderDetailRightItemAdapter;
import com.nopossiblebus.customview.StarBar;
import com.nopossiblebus.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakeOrderDetailDialog extends Dialog {
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.unfinish_info)
    TextView info;
    @BindView(R.id.unfinish_lefttime)
    TextView leftTime;
    @BindView(R.id.unfinish_ll)
    LinearLayout unfinishLl;
    @BindView(R.id.finish_topBar)
    StarBar topBar;
    @BindView(R.id.finish_commont_detail)
    LinearLayout detail;
    @BindView(R.id.finish_goodsBar)
    StarBar goodsBar;
    @BindView(R.id.finish_serviceBar)
    StarBar serviceBar;
    @BindView(R.id.finish_commont)
    EditText commont;
    @BindView(R.id.finish_tag1)
    CheckBox finishTag1;
    @BindView(R.id.finish_tag2)
    CheckBox finishTag2;
    @BindView(R.id.finish_tag3)
    CheckBox finishTag3;
    @BindView(R.id.finish_commont_ll)
    LinearLayout commontLl;
    @BindView(R.id.finish_ll)
    LinearLayout finishLl;
    @BindView(R.id.takeorder_detail_ordernum)
    LinearLayout ordernum;
    @BindView(R.id.takeorder_detail_createTime)
    TextView createTime;
    @BindView(R.id.takeorder_detail_finishTime)
    TextView finishTime;
    @BindView(R.id.takeorder_detail_finish_ll)
    LinearLayout finishTimeLl;
    @BindView(R.id.takeorder_detail_address_img)
    ImageView addressImg;
    @BindView(R.id.takeorder_detail_address_name)
    TextView addressName;
    @BindView(R.id.takeorder_detail_address_distance)
    TextView addressDistance;
    @BindView(R.id.takeorder_detail_address)
    TextView detailAddress;
    @BindView(R.id.takeorder_detail_address_personname)
    TextView addressPersonname;
    @BindView(R.id.takeorder_detail_print)
    TextView detailPrint;
    @BindView(R.id.takeorder_detail_left_recy)
    RecyclerView leftRecy;
    @BindView(R.id.takeorder_detail_right_recy)
    RecyclerView rightRecy;
    @BindView(R.id.takeorder_detail_total_num)
    TextView totalNum;
    @BindView(R.id.takeorder_detailtotal_price)
    TextView totalPrice;
    @BindView(R.id.takeorder_detail_startSend)
    TextView startSend;
    private Context mContext;
    private int type;//type :0-抢单详情   1-配送详情  2评价详情

    private List<String> mData;
    private List<String> mType;

    private TakeOrderDetailRightItemAdapter mRightAdapter;
    private TakeOrderDetailLeftItemAdapter mLeftAdapter;


    public TakeOrderDetailDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        mContext = context;
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_takeorder_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mType = new ArrayList<>();
        mType.add("");
        mType.add("");
        mType.add("");
        mType.add("");
        mType.add("");
        mType.add("");
        leftRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        rightRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        rightRecy.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL,
                (int) getContext().getResources().getDimension(R.dimen.x3),
                getContext().getResources().getColor(R.color.text_black_dd)));
        mLeftAdapter = new TakeOrderDetailLeftItemAdapter(getContext(), mType);
        mRightAdapter = new TakeOrderDetailRightItemAdapter(getContext(), mData);
        rightRecy.setAdapter(mRightAdapter);
        leftRecy.setAdapter(mLeftAdapter);
    }


    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = (int) getContext().getResources().getDimension(R.dimen.y1736);
        getWindow().setAttributes(layoutParams);
    }

    @OnClick({R.id.close, R.id.takeorder_detail_print, R.id.takeorder_detail_startSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                TakeOrderDetailDialog.this.cancel();
                break;
            case R.id.takeorder_detail_print:
                break;
            case R.id.takeorder_detail_startSend:
                break;
        }
    }
}
