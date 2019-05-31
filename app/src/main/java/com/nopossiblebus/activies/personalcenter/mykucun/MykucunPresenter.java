package com.nopossiblebus.activies.personalcenter.mykucun;

import android.content.Context;

import com.nopossiblebus.entity.api.GetStoreApi;
import com.nopossiblebus.entity.bean.MyKucunDataBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MykucunPresenter extends BasePresenterImpl<MykucunContract.View> implements MykucunContract.Presenter{


    public void getProductStore(){
        GetStoreApi getStoreApi = new GetStoreApi(get,mView.getThis());
        getStoreApi.setShowProgress(false);
        mView.getManager().doHttpDeal(getStoreApi);
    }


    private HttpOnNextListener<MyKucunDataBean> get = new HttpOnNextListener<MyKucunDataBean>() {
        @Override
        public void onNext(MyKucunDataBean bean) {
            mView.setData(bean);
        }
    };
}
