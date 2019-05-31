package com.nopossiblebus.activies.myaddress.addressedit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.myaddress.MyAddressEventBackBean;
import com.nopossiblebus.activies.myaddress.MyAddressEventBean;
import com.nopossiblebus.dialog.AreaSelectDialog;
import com.nopossiblebus.entity.bean.CityBean;
import com.nopossiblebus.entity.bean.DistrictBean;
import com.nopossiblebus.entity.bean.PrivinceBean;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.LogUtil;
import com.nopossiblebus.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddresseditFragment extends MVPBaseFragment<AddresseditContract.View, AddresseditPresenter> implements AddresseditContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView titleTxt;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phonenum)
    EditText phonenum;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.save)
    TextView save;
    Unbinder unbinder;

    private List<PrivinceBean> list;
    private AreaSelectDialog mDialog;


    private MyAddressEventBean myAddressEventBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myaddress_edit, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        EventBus.getDefault().register(this);
        return view;
    }

    private void initView() {
        myAddressEventBean = new MyAddressEventBean();
        titleTxt.setText("新增地址");
        mDialog = new AreaSelectDialog(getContext());
        mDialog.setOnAreaSelectListener(areaClick);
    }


    @Override
    public void setAreaData(List<PrivinceBean> list) {
        this.list = list;
        mDialog.setList(list);
        mDialog.show();
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getEventMsg(MyAddressEventBean bean){
        myAddressEventBean = bean;
        if (bean!=null){
            name.setText(myAddressEventBean.getContacts());
            phonenum.setText(myAddressEventBean.getPhone());
            area.setText(bean.getProvince_name()+","+bean.getCity_name()+","+bean.getDistrict_name());
            address.setText(bean.getAddress());
        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.title_back, R.id.area, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                EventBus.getDefault().post(new MyAddressEventBackBean(1));
                break;
            case R.id.area:
                LogUtil.d("点击了");
                if (list == null){
                    mPresenter.getAreaInfo();
                }else {
                    mDialog.show();
                }
                break;
            case R.id.save:
                LogUtil.d("变成");
                saveAddress();
                break;
        }
    }

    private void saveAddress() {
//        String username = name.getText().toString();
        String username = "杨官帅";
        String tel = phonenum.getText().toString();
        String addressInfo = address.getText().toString();
        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(tel)||TextUtils.isEmpty(addressInfo)){
            ToastUtil.showBottomToast("请填写完整的信息后继续");
        }else {
            myAddressEventBean.setAddress(addressInfo);
            myAddressEventBean.setPhone(tel);
            myAddressEventBean.setContacts(username);
            if (myAddressEventBean.getId()==null||myAddressEventBean.getId().equals("")){
                mPresenter.saveAddress(myAddressEventBean);
            }else {
                mPresenter.editArea(myAddressEventBean);
            }

        }
    }

    @Override
    public void saveFinish() {
        EventBus.getDefault().post(new MyAddressEventBackBean(1));
    }

    private AreaSelectDialog.OnAreaSelectListener areaClick = new AreaSelectDialog.OnAreaSelectListener() {
        @Override
        public void onConfirmClick(View view, int pPosition, int cPosition, int dPosition) {
            PrivinceBean bean = list.get(pPosition);
            CityBean cityBean = bean.getChildList().get(cPosition);
            DistrictBean districtBean = cityBean.getChildList().get(dPosition);
            myAddressEventBean.setProvince_name(bean.getName());
            myAddressEventBean.setProvince_no(bean.getId());
            myAddressEventBean.setCity_name(cityBean.getName());
            myAddressEventBean.setCity_no(cityBean.getId());
            myAddressEventBean.setDistrict_name(districtBean.getName());
            myAddressEventBean.setDistrict_no(districtBean.getId());

            area.setText(myAddressEventBean.getProvince_name()+","+myAddressEventBean.getCity_name()+","+myAddressEventBean.getDistrict_name());
        }
    };
}
