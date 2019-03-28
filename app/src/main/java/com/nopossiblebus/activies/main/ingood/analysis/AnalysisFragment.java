package com.nopossiblebus.activies.main.ingood.analysis;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.AnalysisIngoodNumberAdapter;
import com.nopossiblebus.adapter.AnalysisNameNumberAdapter;
import com.nopossiblebus.adapter.AnalysisSingleNumberAdapter;
import com.nopossiblebus.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AnalysisFragment extends MVPBaseFragment<AnalysisContract.View, AnalysisPresenter> implements AnalysisContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.analysis_info)
    TextView analysisInfo;
    @BindView(R.id.analysis_money)
    TextView analysisMoney;
    @BindView(R.id.analysis_num)
    TextView analysisNum;
    @BindView(R.id.analysis_singlegood_number)
    RecyclerView singleRecy;
    @BindView(R.id.analysis_ingood_number)
    RecyclerView ingoodRecy;
    @BindView(R.id.analysis_name_number)
    RecyclerView nameRecy;
    Unbinder unbinder;


    private AnalysisSingleNumberAdapter mSingleAdapter;
    private AnalysisNameNumberAdapter mNameAdapter;
    private AnalysisIngoodNumberAdapter mIngoodAdapter;

    private List<String> mSingleList;
    private List<String> mNameList;
    private List<String> mIngoodList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingood_analysis, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        title.setText("进货分析");
        titleBack.setVisibility(View.GONE);
        mSingleList = new ArrayList<>();
        mNameList = new ArrayList<>();
        mIngoodList = new ArrayList<>();
        for (int i=0;i<10;i++){
            mSingleList.add("");
            mNameList.add("");
            mIngoodList.add("");
        }
        singleRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        ingoodRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        nameRecy.setLayoutManager(new GridLayoutManager(getContext(),3));
        singleRecy.setNestedScrollingEnabled(false);//禁止滑动
        ingoodRecy.setNestedScrollingEnabled(false);//禁止滑动
        nameRecy.setNestedScrollingEnabled(false);//禁止滑动
        mSingleAdapter = new AnalysisSingleNumberAdapter(getContext(),mSingleList);
        mNameAdapter = new AnalysisNameNumberAdapter(getContext(),mNameList);
        mIngoodAdapter = new AnalysisIngoodNumberAdapter(getContext(),mIngoodList);
        singleRecy.setAdapter(mSingleAdapter);
        ingoodRecy.setAdapter(mIngoodAdapter);
        nameRecy.setAdapter(mNameAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
