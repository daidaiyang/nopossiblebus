package com.nopossiblebus.activies.personalcenter.mykucun;


import android.app.ProgressDialog;
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
import com.nopossiblebus.entity.bean.MyKucunDataBean;
import com.nopossiblebus.entity.bean.MyKucunDataListBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.entity.bean.TypeBean;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private List<TypeBean> mLeftData;

    private MyKucunRightItemAdapter mRightAdapter;
    private List<ProductListBean> mRightData;

    private Map<String,List<ProductListBean>> map ;


    private ProgressDialog pd = null;

    private MyKucunDataBean dataBean;

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
        map = new HashMap<>();
        pd = new ProgressDialog(getContext());
        mRightAdapter = new MyKucunRightItemAdapter(getContext(), mRightData);
        mLeftAdapter = new OneKeyLeftItemAdapter(getContext(), mLeftData);
        mLeftAdapter.setClickListener(onLeftClick);
        mykucunLeftrecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mykucunRightrecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mykucunLeftrecy.setAdapter(mLeftAdapter);
        mykucunRightrecy.setAdapter(mRightAdapter);
        pd.show();
        mPresenter.getProductStore();
    }

    @Override
    public void setData(MyKucunDataBean bean) {
        dataBean = bean;
        mLeftData.clear();
        map.clear();
        mykucunNum.setText(String.format("共%s件商品",bean.getData_list().size()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MyKucunDataListBean> data_list = dataBean.getData_list();
                for (int i=0;i<data_list.size();i++){
                    MyKucunDataListBean listBean = data_list.get(i);
                    ProductListBean product = listBean.getProduct();
                    String kind_name = product.getKind_name();
                    List<ProductListBean> list = map.get(kind_name);
                    if (list == null){
                        list = new ArrayList<>();
                        list.add(product);
                        map.put(kind_name,list);
                    }else {
                        list.add(product);
                    }
                }
                Iterator<Map.Entry<String, List<ProductListBean>>> entries = map.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, List<ProductListBean>> entry = entries.next();
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    TypeBean typeBean = new TypeBean();
                    typeBean.setTitle(entry.getKey());
                    typeBean.setChecked(false);
                    mLeftData.add(typeBean);
                }
                mLeftData.get(0).setChecked(true);
                List<ProductListBean> list = map.get(mLeftData.get(0).getTitle());
                mRightData.clear();
                mRightData.addAll(list);
                MykucunActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLeftAdapter.notifyDataSetChanged();
                        mRightAdapter.notifyDataSetChanged();
                        pd.cancel();
                    }
                });
            }
        }).start();
    }

    private OneKeyLeftItemAdapter.OnItemClickListener onLeftClick = new OneKeyLeftItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, final int position) {
            pd.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<mLeftData.size();i++){
                        mLeftData.get(i).setChecked(false);
                    }
                    TypeBean typeBean = mLeftData.get(position);
                    typeBean.setChecked(true);
                    mLeftData.remove(position);
                    mLeftData.add(position,typeBean);
                    List<ProductListBean> list = map.get(typeBean.getTitle());
                    mRightData.clear();
                    mRightData.addAll(list);
                    MykucunActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLeftAdapter.notifyDataSetChanged();
                            mRightAdapter.notifyDataSetChanged();
                            pd.cancel();
                        }
                    });
                }
            }).start();
        }
    };


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
