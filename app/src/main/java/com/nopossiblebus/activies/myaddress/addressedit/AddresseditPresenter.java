package com.nopossiblebus.activies.myaddress.addressedit;

import com.nopossiblebus.activies.myaddress.MyAddressEventBean;
import com.nopossiblebus.entity.api.GetCityTreeApi;
import com.nopossiblebus.entity.api.ModifyLocationApi;
import com.nopossiblebus.entity.bean.PrivinceBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddresseditPresenter extends BasePresenterImpl<AddresseditContract.View> implements AddresseditContract.Presenter{



    public void getAreaInfo(){
        GetCityTreeApi getCityTreeApi = new GetCityTreeApi(getCityTree,mView.getThis());
        mView.getManager().doHttpDeal(getCityTreeApi);
    }


    public void saveAddress(MyAddressEventBean bean){
        ModifyLocationApi modifyLocationApi =  new ModifyLocationApi(modify,mView.getThis());
        modifyLocationApi.initData(bean);
        mView.getManager().doHttpDeal(modifyLocationApi);

    }


    public void editArea(MyAddressEventBean bean){
        ModifyLocationApi modifyLocationApi = new ModifyLocationApi(modify,mView.getThis());
        modifyLocationApi.initData(bean);
        mView.getManager().doHttpDeal(modifyLocationApi);
    }



    private HttpOnNextListener<String> modify = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            mView.saveFinish();
        }
    };


    private HttpOnNextListener<List<PrivinceBean>> getCityTree = new HttpOnNextListener<List<PrivinceBean>>() {
        @Override
        public void onNext(List<PrivinceBean> list) {
            mView.setAreaData(list);
        }
    };
}
