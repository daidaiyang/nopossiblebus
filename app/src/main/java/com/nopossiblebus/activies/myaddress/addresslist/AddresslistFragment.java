package com.nopossiblebus.activies.myaddress.addresslist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.myaddress.MyAddressEventBackBean;
import com.nopossiblebus.activies.myaddress.MyAddressEventBean;
import com.nopossiblebus.adapter.MyAddressListAdapter;
import com.nopossiblebus.entity.bean.MyAddressListBean;
import com.nopossiblebus.mvp.MVPBaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddresslistFragment extends MVPBaseFragment<AddresslistContract.View, AddresslistPresenter> implements AddresslistContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView titleTxt;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    MyAddressListAdapter mAdapter;
    private List<MyAddressListBean> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myaddress_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        titleTxt.setText("地址列表");
        empty.setVisibility(View.GONE);
        mData = new ArrayList<>();
        mAdapter = new MyAddressListAdapter(mData,getContext());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClick);
        mPresenter.getAddressList();
    }

    @Override
    public void setListData(List<MyAddressListBean> list) {
        mData.clear();
        mData.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            mPresenter.getAddressList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick({R.id.title_back, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                EventBus.getDefault().post(new MyAddressEventBackBean(0));
                break;
            case R.id.add:
                EventBus.getDefault().post(new MyAddressEventBackBean(2));
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private MyAddressListAdapter.OnItemClickListener onItemClick = new MyAddressListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            MyAddressListBean myAddressListBean = mData.get(position);
            EventBus.getDefault().post(myAddressListBean);
            EventBus.getDefault().post(new MyAddressEventBackBean(0));
        }

        @Override
        public void onMorenClick(View view, int position) {
            mPresenter.setMoren(mData.get(position).getId());
        }

        @Override
        public void onEditClick(View view, int position) {
            MyAddressListBean myAddressListBean = mData.get(position);
            MyAddressEventBean bean = new MyAddressEventBean();
            bean.setId(myAddressListBean.getId());
            bean.setProvince_name(myAddressListBean.getProvince_name());
            bean.setProvince_no(myAddressListBean.getProvince_no());
            bean.setCity_name(myAddressListBean.getCity_name());
            bean.setCity_no(myAddressListBean.getCity_no());
            bean.setDistrict_name(myAddressListBean.getDistrict_name());
            bean.setDistrict_no(myAddressListBean.getDistrict_no());
            bean.setAddress(myAddressListBean.getAddress());
            bean.setContacts(myAddressListBean.getContacts());
            bean.setPhone(myAddressListBean.getPhone());
            EventBus.getDefault().postSticky(bean);
            EventBus.getDefault().post(new MyAddressEventBackBean(2));
        }

        @Override
        public void onDeleteClick(View view, int posotion) {
            mPresenter.delete(mData.get(posotion).getId());
        }
    };
}
