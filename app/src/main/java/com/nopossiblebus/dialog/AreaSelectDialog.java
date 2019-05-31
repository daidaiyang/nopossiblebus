package com.nopossiblebus.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.nopossiblebus.R;
import com.nopossiblebus.customview.wheelview.WheelView;
import com.nopossiblebus.entity.bean.CityBean;
import com.nopossiblebus.entity.bean.DistrictBean;
import com.nopossiblebus.entity.bean.PrivinceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AreaSelectDialog extends Dialog {


    @BindView(R.id.cancle)
    TextView cancle;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.province_wv)
    WheelView provinceWv;
    @BindView(R.id.city_wv)
    WheelView cityWv;
    @BindView(R.id.district_wv)
    WheelView districtWv;



    private List<PrivinceBean> list;
    private ArrayList<String> privinceList;
    private ArrayList<String> cityList;
    private ArrayList<String> districtList;


    private int provincePosition = 0;
    private int cityPosition = 0;
    private int districtPosition = 0;

    private OnAreaSelectListener onAreaSelectListener;


    public void setOnAreaSelectListener(OnAreaSelectListener onAreaSelectListener) {
        this.onAreaSelectListener = onAreaSelectListener;
    }

    public void setList(List<PrivinceBean> list) {
        this.list = list;
    }

    public List<PrivinceBean> getList() {
        return list;
    }

    public AreaSelectDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selectarea);
        ButterKnife.bind(this);
        getProvinces();
        provinceWv.setItems(privinceList,0);
        cityWv.setItems(cityList,0);
        districtWv.setItems(districtList,0);
        provinceWv.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                provincePosition = selectedIndex;
                getCitys(selectedIndex);
                cityWv.setItems(cityList,0);
                districtWv.setItems(districtList,0);
            }
        });
        cityWv.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                cityPosition = selectedIndex;
                getDistrict(provincePosition,selectedIndex);
                districtWv.setItems(districtList,0);
            }
        });
        districtWv.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                districtPosition = selectedIndex;
            }
        });



        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);
    }
    //获取省
    private void getProvinces(){
        privinceList = new ArrayList<>();
        for (PrivinceBean pB: list
             ) {
            privinceList.add(pB.getName());
        }
        if (cityList == null)
            cityList = new ArrayList<>();
        else
            cityList.clear();
        List<CityBean> childList = list.get(0).getChildList();
        for (CityBean cB: childList
             ) {
            cityList.add(cB.getName());
        }
        if (districtList == null)
            districtList = new ArrayList<>();
        else
            districtList.clear();

        List<DistrictBean> childList1 = childList.get(0).getChildList();
        for (DistrictBean dB: childList1
             ) {
            districtList.add(dB.getName());
        }

    }


    private void getCitys(int position){
        if (cityList == null)
            cityList = new ArrayList<>();
        else
            cityList.clear();
        List<CityBean> childList = list.get(position).getChildList();
        for (CityBean cB: childList
             ) {
            cityList.add(cB.getName());
        }
        if (districtList == null)
            districtList = new ArrayList<>();
        else
            districtList.clear();
        List<DistrictBean> childList1 = childList.get(0).getChildList();
        for (DistrictBean dB: childList1
             ) {
            districtList.add(dB.getName());
        }
    }

    private void getDistrict(int pPosition,int cPosition){
        if (districtList == null)
            districtList = new ArrayList<>();
        else
            districtList.clear();
        List<DistrictBean> childList1 = list.get(pPosition).getChildList().get(cPosition).getChildList();
        for (DistrictBean dB: childList1
                ) {
            districtList.add(dB.getName());
        }
    }

    @OnClick({R.id.cancle, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle:
                this.cancel();
                break;
            case R.id.confirm:
                    if (onAreaSelectListener !=null){
                        onAreaSelectListener.onConfirmClick(view,provincePosition,cityPosition,districtPosition);
                    }
                this.cancel();
                break;
        }
    }


    public interface OnAreaSelectListener{
        void onConfirmClick(View view, int pPosition, int cPosition, int dPosition);
    }
}
