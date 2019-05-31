package com.nopossiblebus.activies.togoodapply;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.nopossiblebus.entity.bean.ApplyGoodReaultBean;
import com.nopossiblebus.mvp.BasePresenter;
import com.nopossiblebus.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TogoodApplyContract {
    interface View extends BaseView {
        void setGoods(String name,String id);
        void applyFinish();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
