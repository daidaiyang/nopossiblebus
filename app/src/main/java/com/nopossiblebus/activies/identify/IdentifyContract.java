package com.nopossiblebus.activies.identify;

import android.content.Context;

import com.nopossiblebus.entity.bean.GroupTypeBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class IdentifyContract {
    interface View extends BaseView {
        void saveAuthFinish();
        void setGroupData(List<GroupTypeBean> groupList);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
