package com.nopossiblebus.activies.main.togood.supplyorder;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nopossiblebus.R;
import com.nopossiblebus.mvp.MVPBaseFragment;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SupplyorderFragment extends MVPBaseFragment<SupplyorderContract.View, SupplyorderPresenter> implements SupplyorderContract.View {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_togood_supplyorder,container,false);
        return view;
    }
}
