package com.nopossiblebus.activies.myaddress.addressedit;

import com.nopossiblebus.entity.bean.PrivinceBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddresseditContract {
    interface View extends BaseView {
        void setAreaData(List<PrivinceBean> list);
        void saveFinish();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
