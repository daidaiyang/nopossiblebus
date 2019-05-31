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
import com.nopossiblebus.entity.api.GetProductKindListApi;
import com.nopossiblebus.entity.bean.OrderLineBean;
import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.entity.bean.ProductKindBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.entity.bean.TypeBean;
import com.nopossiblebus.http.http.HttpManager;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.utils.AppUtil;
import com.nopossiblebus.utils.RecycleViewDivider;
import com.nopossiblebus.utils.TimeUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.order_no)
    TextView orderNo;
    private Context mContext;
    private int type;//type :0-抢单详情   1-配送详情  2评价详情

    private List<OrderLineBean> mData;
    private List<TypeBean> mType;
    private float total = 0;
    private float priceAll = 0;

    private OrderListBean bean;
    private RxAppCompatActivity activity;


    private TakeOrderDetailRightItemAdapter mRightAdapter;
    private TakeOrderDetailLeftItemAdapter mLeftAdapter;

    public void setData(OrderListBean bean) {
        this.bean = bean;
    }


    public TakeOrderDetailDialog(@NonNull Context context,RxAppCompatActivity activity) {
        super(context, R.style.BottomDialogStyle);
        mContext = context;
        this.activity = activity;
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_takeorder_detail);
        ButterKnife.bind(this);
        init();
    }

    private void setView() {
        if (bean.getDelivery_time()!=null){
            String s = TimeUtil.timeStamp2Date(bean.getDelivery_time(), "dd日HH:mm");
            info.setText("买家要求" + s + "完成配送");
        }

        Long deliverTime = Long.valueOf(bean.getDelivery_time());
        Long nowTime = Long.valueOf(TimeUtil.timeStamp() + "000");
        long l = deliverTime - nowTime;
        long l1 = l / (1000 * 60);
        leftTime.setText(String.format("剩余%s分钟", l1));
        orderNo.setText(bean.getNo());
        createTime.setText(TimeUtil.timeStamp2Date(bean.getCreate_time(),"yyyy.MM.dd HH:mm:ss"));
        finishTime.setText(TimeUtil.timeStamp2Date(bean.getDelivery_time(),"yyyy.MM.dd HH:mm:ss"));
        addressName.setText(bean.getAddress());
        detailAddress.setText(bean.getProvince_name()+" "+bean.getCity_name()+" "+bean.getDistrict_name()+" "+bean.getAddress());
        addressPersonname.setText(bean.getTake_contacts());
        String status = bean.getStatus();
        startSend.setTag(status);
        startSend.setVisibility(View.VISIBLE);
        if (status.equals("7")){
        startSend.setText("确认收货");
        }else if (status.equals("8")){
            startSend.setText("发表评价");
        }else  if (status.equals("9")){
            startSend.setVisibility(View.GONE);
        }else if (status.equals("2")){
            startSend.setText("抢单");
        }else if (status.equals("3")){
            startSend.setText("开始配送");
        }
        mType.clear();
        mData.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<OrderLineBean> order_line = bean.getOrder_line();
                for (int i=0;i<order_line.size();i++){
                    ProductListBean product = order_line.get(i).getProduct();
                    String kind_name = product.getKind_name();
                    boolean has = false;
                    for (int j=0;j<mType.size();j++){
                        TypeBean typeBean = mType.get(j);
                        if (typeBean.getTitle().equals(kind_name)) {
                            has = true;
                            break;
                        }
                    }
                    if (!has){
                        TypeBean bean = new TypeBean();
                        bean.setTitle(kind_name);
                        if (i==0){
                            bean.setChecked(true);
                        }else {
                            bean.setChecked(false);
                        }
                        mType.add(bean);
                    }
                }
                for (int i=0;i<mType.size();i++){
                    TypeBean typeBean = mType.get(i);
                    if (typeBean.isChecked()){
                        String title = typeBean.getTitle();
                        for (int j=0;j<order_line.size();j++){
                            OrderLineBean orderLineBean = order_line.get(j);
                            String kind_name = orderLineBean.getProduct().getKind_name();
                            if (kind_name.equals(title)){
                                mData.add(orderLineBean);
                            }
                        }
                    }
                }
                total = 0;
                priceAll =0;
                for (int i=0;i<order_line.size();i++){
                    OrderLineBean orderLineBean = order_line.get(i);
                    total +=Float.valueOf(orderLineBean.getNum());
                    priceAll+=Float.valueOf(orderLineBean.getMoney());
                }


                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLeftAdapter.notifyDataSetChanged();
                        mRightAdapter.notifyDataSetChanged();
                        totalNum.setText(String.format("共%s件",AppUtil.get2xiaoshu(total)));
                        totalPrice.setText(String.format("￥%s", AppUtil.get2xiaoshu(priceAll)));
                    }
                });
            }
        }).start();


    }

    private void init() {
        mData = new ArrayList<>();
        mType = new ArrayList<>();
        getProductkind();
        leftRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        rightRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        rightRecy.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL,
                (int) getContext().getResources().getDimension(R.dimen.x3),
                getContext().getResources().getColor(R.color.text_black_dd)));
        mLeftAdapter = new TakeOrderDetailLeftItemAdapter(getContext(), mType);
        mLeftAdapter.setItemClickListener(onLeft);
        mRightAdapter = new TakeOrderDetailRightItemAdapter(getContext(), mData);
        rightRecy.setAdapter(mRightAdapter);
        leftRecy.setAdapter(mLeftAdapter);
    }


    private void getProductkind(){
        GetProductKindListApi getProductKindListApi = new GetProductKindListApi(kindList, activity);
        HttpManager.getInstance().doHttpDeal(getProductKindListApi);

    }


    private HttpOnNextListener<List<ProductKindBean>> kindList = new HttpOnNextListener<List<ProductKindBean>>() {
        @Override
        public void onNext(List<ProductKindBean> list) {

        }
    };

    @Override
    public void show() {
        super.show();
        setView();
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

    private TakeOrderDetailLeftItemAdapter.OnItemClickListener onLeft= new TakeOrderDetailLeftItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            List<TypeBean> list = new ArrayList<>();
            for (int i=0;i<mType.size();i++){
                if (i == position){
                    mType.get(i).setChecked(true);
                }else {
                    mType.get(i).setChecked(false);
                }
                list.add(mType.get(i));
            }
            mType.clear();
            mType.addAll(list);
            mLeftAdapter.notifyDataSetChanged();

        }
    };
}
