package com.nopossiblebus.activies.myaddress.addresslist;

import com.nopossiblebus.entity.bean.MyAddressListBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddresslistContract {
    interface View extends BaseView {
        void setListData(List<MyAddressListBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
