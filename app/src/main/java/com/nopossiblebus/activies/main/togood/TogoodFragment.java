package com.nopossiblebus.activies.main.togood;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.main.MainActivity;
import com.nopossiblebus.activies.main.togood.analysis.AnalysisFragment;
import com.nopossiblebus.activies.main.togood.applygood.ApplygoodFragment;
import com.nopossiblebus.activies.main.togood.supplygood.SupplygoodFragment;
import com.nopossiblebus.activies.main.togood.supplyorder.SupplyorderFragment;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TogoodFragment extends MVPBaseFragment<TogoodContract.View, TogoodPresenter> implements TogoodContract.View {

    @BindView(R.id.togood_goods_framelayout)
    FrameLayout togoodGoodsFramelayout;
    @BindView(R.id.togood_goods_navigation)
    BottomNavigationView navigation;
    Unbinder unbinder;

    private SupplygoodFragment supplygoodFragment = null;
    private ApplygoodFragment applygoodFragment = null;
    private SupplyorderFragment supplyorderFragment =null;
    private AnalysisFragment analysisFragment =null;

    private FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_togood, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        setDefaultFragment();
    }



    private void setDefaultFragment() {
        transaction = getChildFragmentManager().beginTransaction();
        if (supplygoodFragment == null){
            supplygoodFragment = new SupplygoodFragment();
            transaction.add(R.id.togood_goods_framelayout,supplygoodFragment);
        }else {
            transaction.show(supplygoodFragment);
        }
        transaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            resetToDefaultIcon();
            switch (item.getItemId()) {
                case R.id.navigation_supplygood:
                    item.setIcon(R.mipmap.goods_selected);
                    replaceFragment(0);
                    ((MainActivity)getActivity()).showTitle();
                    return true;
                case R.id.navigation_applygood:
                    item.setIcon(R.mipmap.togood_apply);
                    replaceFragment(1);
                    ((MainActivity)getActivity()).hideTitle();
                    return true;
                case R.id.navigation_supplyorder:
                    item.setIcon(R.mipmap.ingood_selected);
                    replaceFragment(2);
                    ((MainActivity)getActivity()).hideTitle();
                    return true;
                case R.id.navigation_anaylsis2:
                    item.setIcon(R.mipmap.analysis_selected);
                    replaceFragment(3);
                    ((MainActivity)getActivity()).hideTitle();
                    return true;
            }
            return false;
        }
    };

    private void resetToDefaultIcon(){
        MenuItem good = navigation.getMenu().findItem(R.id.navigation_supplygood);
        good.setIcon(R.mipmap.goods_unselected);
        MenuItem applygood = navigation.getMenu().findItem(R.id.navigation_applygood);
        applygood.setIcon(R.mipmap.togood_apply_un);
        MenuItem supplyorder = navigation.getMenu().findItem(R.id.navigation_supplyorder);
        supplyorder.setIcon(R.mipmap.ingood_unselected);
        MenuItem anaylsis = navigation.getMenu().findItem(R.id.navigation_anaylsis2);
        anaylsis.setIcon(R.mipmap.analysis_unselected);
    }

    private void replaceFragment(int position){
        transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment();
        switch (position){
            case 0:
                if (supplygoodFragment == null){
                    supplygoodFragment = new SupplygoodFragment();
                    transaction.add(R.id.togood_goods_framelayout, supplygoodFragment);
                }else {
                    transaction.show(supplygoodFragment);
                }
                transaction.commit();
                break;
            case 1:
                if (applygoodFragment == null){
                    applygoodFragment = new ApplygoodFragment();
                    transaction.add(R.id.togood_goods_framelayout, applygoodFragment);
                }else {
                    transaction.show(applygoodFragment);
                }
                transaction.commit();
                break;
            case 2:
                if (supplyorderFragment == null){
                    supplyorderFragment = new SupplyorderFragment();
                    transaction.add(R.id.togood_goods_framelayout, supplyorderFragment);
                }else {
                    transaction.show(supplyorderFragment);
                }
                transaction.commit();
                break;
            case 3:
                if (analysisFragment == null){
                    analysisFragment = new AnalysisFragment();
                    transaction.add(R.id.togood_goods_framelayout, analysisFragment);
                }else {
                    transaction.show(analysisFragment);
                }
                transaction.commit();
                break;
        }
    }

    private void hideAllFragment(){
        if (supplygoodFragment != null){
            transaction.hide(supplygoodFragment);
        }
        if (applygoodFragment != null){
            transaction.hide(applygoodFragment);
        }
        if (supplyorderFragment != null){
            transaction.hide(supplyorderFragment);
        }
        if (analysisFragment !=null){
            transaction.hide(analysisFragment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
