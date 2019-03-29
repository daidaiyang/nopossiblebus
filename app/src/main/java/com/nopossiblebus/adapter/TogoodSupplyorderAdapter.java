package com.nopossiblebus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.customview.CircleImageView;
import com.nopossiblebus.customview.ShadowDrawable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TogoodSupplyorderAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<String> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public TogoodSupplyorderAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_togood_supplyorder, viewGroup, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.itemTogoodOrderRoot, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                0, 0);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_img)
        CircleImageView itemImg;
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.item_good_img1)
        ImageView itemGoodImg1;
        @BindView(R.id.item_good_img2)
        ImageView itemGoodImg2;
        @BindView(R.id.item_good_img3)
        ImageView itemGoodImg3;
        @BindView(R.id.item_good_img4)
        ImageView itemGoodImg4;
        @BindView(R.id.item_good_price)
        TextView itemGoodPrice;
        @BindView(R.id.item_good_num)
        TextView itemGoodNum;
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_status_send)
        TextView itemStatusSend;
        @BindView(R.id.item_status_finish)
        TextView itemStatusFinish;
        @BindView(R.id.item_togood_order_root)
        LinearLayout itemTogoodOrderRoot;

        private OnItemClickListener clickListener;

        ViewHolder(View view, OnItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.clickListener = clickListener;
            view.setOnClickListener(this);
            itemStatusSend.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                if (v.getId() == R.id.item_status_unfinish) {
                    clickListener.onOperationClick(v, getPosition());
                } else {
                    clickListener.onItemClick(v, getPosition());
                }
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onOperationClick(View v, int position);
    }
}
