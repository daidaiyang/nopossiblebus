package com.nopossiblebus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.nopossiblebus.R;
import com.nopossiblebus.dialog.SearchResultPopu;
import com.nopossiblebus.entity.api.GetProductListApi;
import com.nopossiblebus.entity.bean.ApplyGoodReaultBean;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.http.http.HttpManager;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TogoodapplyAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ApplyGoodReaultBean> mData;
    private String content = "";
    private Handler handler;
    private onTextChange onTextChange;

    public void setOnTextChange(TogoodapplyAdapter.onTextChange onTextChange) {
        this.onTextChange = onTextChange;
    }

    public TogoodapplyAdapter(Context mContext, List<ApplyGoodReaultBean> mData, Handler handler) {
        this.mContext = mContext;
        this.mData = mData;
        this.handler = handler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_togoodapply, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        holder.root.setFocusable(true);
        holder.root.setFocusableInTouchMode(true);
        holder.itemName.setText(mData.get(position).getName());
        holder.itemName.setTag(mData.get(position).getId());
        holder.itemName.clearFocus();
        holder.itemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (onTextChange!=null){
                    onTextChange.onTextChaneg(holder.itemName,position,s.toString());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        EditText itemName;
        @BindView(R.id.root)
        RelativeLayout root;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public interface  onTextChange{
        void onTextChaneg(View view,int position,String content);
    }
}
