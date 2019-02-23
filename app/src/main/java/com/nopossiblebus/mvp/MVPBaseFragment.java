package com.nopossiblebus.mvp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.nopossiblebus.R;

import java.lang.reflect.ParameterizedType;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public abstract class MVPBaseFragment<V extends BaseView,T extends BasePresenterImpl<V>> extends Fragment implements BaseView{
    public T mPresenter;
    /**
     * 简单的退出和跳转动画
     */
    private boolean animCon = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void startActivity(Intent intent) {
        if (animCon)
            getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        super.startActivity(intent);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (animCon)
            getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.detachView();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    public  <T> T getInstance(Object o, int i) {
            try {
                return ((Class<T>) ((ParameterizedType) (o.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }
}
