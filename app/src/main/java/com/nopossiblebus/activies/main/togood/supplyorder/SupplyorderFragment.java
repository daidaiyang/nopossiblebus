package com.nopossiblebus.activies.main.togood.supplyorder;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.adapter.TogoodSupplyorderAdapter;
import com.nopossiblebus.dialog.TakeOrderDetailDialog;
import com.nopossiblebus.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.http.GET;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SupplyorderFragment extends MVPBaseFragment<SupplyorderContract.View, SupplyorderPresenter> implements SupplyorderContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.supplyorder_rb_all)
    RadioButton supplyorderRbAll;
    @BindView(R.id.supplyorder_rb_un)
    RadioButton supplyorderRbUn;
    @BindView(R.id.supplyorder_rb_already)
    RadioButton supplyorderRbAlready;
    @BindView(R.id.supplyorder_rg)
    RadioGroup supplyorderRg;
    @BindView(R.id.supplyorder_recy)
    RecyclerView supplyorderRecy;
    Unbinder unbinder;


    private TogoodSupplyorderAdapter mAdapter;
    private List<String> mData;
    private TakeOrderDetailDialog dialog;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_togood_supplyorder, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        titleBack.setVisibility(View.GONE);
        title.setText("供货单管理");
        dialog = new TakeOrderDetailDialog(getContext());
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("");
        }
        mAdapter = new TogoodSupplyorderAdapter(getContext(),mData);
        supplyorderRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        supplyorderRecy.setAdapter(mAdapter);
        mAdapter.setClickListener(click);
    }


    TogoodSupplyorderAdapter.OnItemClickListener click = new TogoodSupplyorderAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            dialog.show();
        }

        @Override
        public void onOperationClick(View v, int position) {

        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
