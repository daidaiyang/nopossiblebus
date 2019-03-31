package com.nopossiblebus.activies.main.ingood;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.main.MainActivity;
import com.nopossiblebus.activies.main.ingood.analysis.AnalysisFragment;
import com.nopossiblebus.activies.main.ingood.cart.CartFragment;
import com.nopossiblebus.activies.main.ingood.goods.GoodsFragment;
import com.nopossiblebus.activies.main.ingood.order.OrderFragment;
import com.nopossiblebus.activies.main.takeorder.TakeorderFragment;
import com.nopossiblebus.activies.main.togood.TogoodFragment;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class IngoodFragment extends MVPBaseFragment<IngoodContract.View, IngoodPresenter> implements IngoodContract.View {


    @BindView(R.id.ingood_goods_framelayout)
    FrameLayout framelayout;
    @BindView(R.id.ingood_goods_navigation)
    BottomNavigationView navigation;
    Unbinder unbinder;

    private GoodsFragment goodsFragment = null;
    private CartFragment cartFragment = null;
    private OrderFragment orderFragment =null;
    private AnalysisFragment analysisFragment =null;

    private FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingood, container, false);
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
        if (goodsFragment == null){
            goodsFragment = new GoodsFragment();
            transaction.add(R.id.ingood_goods_framelayout,goodsFragment);
        }else {
            transaction.show(goodsFragment);
        }
        transaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            resetToDefaultIcon();
            switch (item.getItemId()) {
                case R.id.navigation_goods:
                    item.setIcon(R.mipmap.goods_selected);
                    replaceFragment(0);
                    ((MainActivity)getActivity()).showTitle();
                    return true;
                case R.id.navigation_cart:
                    item.setIcon(R.mipmap.cart_selected);
                    replaceFragment(1);
                    ((MainActivity)getActivity()).showTitle();
                    return true;
                case R.id.navigation_order:
                    item.setIcon(R.mipmap.ingood_selected);
                    replaceFragment(2);
                    ((MainActivity)getActivity()).showTitle();
                    return true;
                case R.id.navigation_anaylsis:
                    item.setIcon(R.mipmap.analysis_selected);
                    replaceFragment(3);
                    ((MainActivity)getActivity()).hideTitle();
                    return true;
            }
            return false;
        }
    };


    private void resetToDefaultIcon(){
        MenuItem good = navigation.getMenu().findItem(R.id.navigation_goods);
        good.setIcon(R.mipmap.goods_unselected);
        MenuItem cart = navigation.getMenu().findItem(R.id.navigation_cart);
        cart.setIcon(R.mipmap.cart_unselected);
        MenuItem order = navigation.getMenu().findItem(R.id.navigation_order);
        order.setIcon(R.mipmap.ingood_unselected);
        MenuItem anaylsis = navigation.getMenu().findItem(R.id.navigation_anaylsis);
        anaylsis.setIcon(R.mipmap.analysis_unselected);
    }

    private void replaceFragment(int position){
        transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment();
        switch (position){
            case 0:
                if (goodsFragment == null){
                    goodsFragment = new GoodsFragment();
                    transaction.add(R.id.ingood_goods_framelayout, goodsFragment);
                }else {
                    transaction.show(goodsFragment);
                }
                transaction.commit();
                break;
            case 1:
                if (cartFragment == null){
                    cartFragment = new CartFragment();
                    transaction.add(R.id.ingood_goods_framelayout, cartFragment);
                }else {
                    transaction.show(cartFragment);
                }
                transaction.commit();
                break;
            case 2:
                if (orderFragment == null){
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.ingood_goods_framelayout, orderFragment);
                }else {
                    transaction.show(orderFragment);
                }
                transaction.commit();
                break;
            case 3:
                if (analysisFragment == null){
                    analysisFragment = new AnalysisFragment();
                    transaction.add(R.id.ingood_goods_framelayout, analysisFragment);
                }else {
                    transaction.show(analysisFragment);
                }
                transaction.commit();
                break;
        }
    }

    private void hideAllFragment(){
        if (goodsFragment != null){
            transaction.hide(goodsFragment);
        }
        if (cartFragment != null){
            transaction.hide(cartFragment);
        }
        if (orderFragment != null){
            transaction.hide(orderFragment);
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
