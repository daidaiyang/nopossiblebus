package com.nopossiblebus.activies.main.ingood.analysis;


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

public class AnalysisFragment extends MVPBaseFragment<AnalysisContract.View, AnalysisPresenter> implements AnalysisContract.View {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingood_analysis,container,false);
        return view;
    }
}
