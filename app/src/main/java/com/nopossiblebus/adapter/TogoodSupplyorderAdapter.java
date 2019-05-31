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

import com.bumptech.glide.Glide;
import com.nopossiblebus.R;
import com.nopossiblebus.customview.CircleImageView;
import com.nopossiblebus.customview.ShadowDrawable;
import com.nopossiblebus.entity.bean.BaseImageList;
import com.nopossiblebus.entity.bean.OrderLineBean;
import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.utils.AppUtil;
import com.nopossiblebus.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TogoodSupplyorderAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<OrderListBean> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public TogoodSupplyorderAdapter(Context mContext, List<OrderListBean> mData) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.itemTogoodOrderRoot, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                0, 0);
        OrderListBean orderListBean = mData.get(position);
        List<OrderLineBean> order_line = orderListBean.getOrder_line();
        String delivery_name = orderListBean.getDelivery_name();
        holder.itemName.setText(delivery_name==null?"暂无":delivery_name);
        holder.itemGoodPrice.setText("￥"+ AppUtil.get2xiaoshu(orderListBean.getPay_money()));
        float totalNum = 0;
        List<String> imgList = new ArrayList<>();
        for (int i=0;i<order_line.size();i++){
            OrderLineBean orderLineBean = order_line.get(i);
            totalNum += Float.valueOf(orderLineBean.getNum());
            ProductListBean product = orderLineBean.getProduct();
            List<BaseImageList> images_list = product.getImages_list();
            if (images_list!=null&&images_list.size()>0){
                for (int j=0;j<images_list.size();j++){
                    BaseImageList baseImageList = images_list.get(j);
                    if (baseImageList!=null){
                        imgList.add(baseImageList.getUrl());
                    }
                }
            }
        }
        if (imgList.size()>4){
            imgList = imgList.subList(0,4);
        }
        switch (imgList.size()){
            case 4:
                Glide.with(mContext)
                        .load(imgList.get(3))
                        .into(holder.itemGoodImg4);
            case 3:
                Glide.with(mContext)
                        .load(imgList.get(2))
                        .into(holder.itemGoodImg3);
            case 2:
                Glide.with(mContext)
                        .load(imgList.get(1))
                        .into(holder.itemGoodImg2);
            case 1:
                Glide.with(mContext)
                        .load(imgList.get(0))
                        .into(holder.itemGoodImg1);
            case 0:
                break;
        }

        holder.itemGoodNum.setText(String.format("共%s件",totalNum));
        holder.itemTime.setText(TimeUtil.timeStamp2Date(orderListBean.getCreate_time(),"yyyy.MM.DD HH:mm:ss"));
        holder.itemStatusFinish.setText(AppUtil.getStatus(orderListBean.getStatus()));

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
