package com.nopossiblebus.activies.myaddress;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import com.nopossiblebus.R;
import com.nopossiblebus.activies.myaddress.addressedit.AddresseditFragment;
import com.nopossiblebus.activies.myaddress.addresslist.AddresslistFragment;
import com.nopossiblebus.mvp.MVPBaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyAddressActivity extends MVPBaseActivity<MyAddressContract.View, MyAddressPresenter> implements MyAddressContract.View {


    @BindView(R.id.frame)
    FrameLayout frame;

    private AddresslistFragment listFragment;
    private AddresseditFragment editFragment;

    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaddress);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        toListFragment();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addressEvent(MyAddressEventBean bean){

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addressBackEvent(MyAddressEventBackBean bean){
        if (bean.getCode() == 1){
            toListFragment();
        }else if (bean.getCode() == 0){
            MyAddressActivity.this.finish();
        }else if (bean.getCode() == 2){
            toEditFragment();
        }
    }

    private void toListFragment() {
        hideAll();
        if (listFragment == null){
            listFragment = new AddresslistFragment();
            transaction.add(R.id.frame,listFragment,"list");
        }else {
            if (!listFragment.isAdded()&&manager.findFragmentByTag("list")==null){
                transaction.add(R.id.frame,listFragment,"list");
            }else {
                transaction.show(listFragment);
            }
        }
        transaction.commit();
    }

    private void toEditFragment() {
        hideAll();
        if (editFragment == null){
            editFragment = new AddresseditFragment();
            transaction.add(R.id.frame,editFragment,"edit");
        }else {
            if (!editFragment.isAdded()&&manager.findFragmentByTag("edit")==null){
                transaction.add(R.id.frame,editFragment,"edit");
            }else {
                transaction.show(editFragment);
            }
        }
        transaction.commit();
    }


    private void hideAll(){
        transaction = manager.beginTransaction();
        if (listFragment != null){
            transaction.hide(listFragment);
        }
        if (editFragment !=null){
            transaction.hide(editFragment);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
