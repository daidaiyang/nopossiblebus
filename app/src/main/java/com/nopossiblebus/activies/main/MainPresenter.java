package com.nopossiblebus.activies.main;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import com.nopossiblebus.dialog.IdentifyDialog;
import com.nopossiblebus.entity.api.GetAuthApi;
import com.nopossiblebus.entity.bean.SchemeListBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.SpUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    private IdentifyDialog identifyDialog = null;

    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            new RxPermissions(mView.getThis()).requestEach(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.LOCATION_HARDWARE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
                            if (permission.granted) {//同意后调用

                            } else if (permission.shouldShowRequestPermissionRationale) {//禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
//                                mView.exitApp();
                            } else {//禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
//                                mView.exitApp();
                            }
                        }
                    });
        }
    }


    public void getMerchantAuth() {
        GetAuthApi getAuthApi = new GetAuthApi(getAuth, mView.getThis());
        mView.getManager().doHttpDeal(getAuthApi);
    }


    private HttpOnNextListener<SchemeListBean> getAuth = new HttpOnNextListener<SchemeListBean>() {
        @Override
        public void onNext(SchemeListBean bean) {
            if (bean != null && bean.getStatus() != null) {
                String status = bean.getStatus();
                SpUtils.putString(mView.getContext(), "auth", status);
                if (!bean.getStatus().equals("1")) {
                    if (identifyDialog == null) {
                        identifyDialog = new IdentifyDialog(mView.getContext());
                    }
                    identifyDialog.setStatus(bean);
                    identifyDialog.show();
                }else {
                    Boolean isShow = SpUtils.getBoolean(mView.getContext(), "isShow", false);
                    if (!isShow){
                        if (identifyDialog == null) {
                            identifyDialog = new IdentifyDialog(mView.getContext());
                        }
                        identifyDialog.setStatus(bean);
                        identifyDialog.show();
                        SpUtils.putBoolean(mView.getContext(),"isShow",true);
                    }
                }
            } else {
                if (identifyDialog == null) {
                    identifyDialog = new IdentifyDialog(mView.getContext());
                }
                identifyDialog.setStatus(bean);
                identifyDialog.show();

            }

        }
    };
}
