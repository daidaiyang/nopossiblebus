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

public class TakeOrderItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<OrderListBean> mData;
    private OnItemClickListener clickListener;

    public TakeOrderItemAdapter(Context mContext, List<OrderListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_takeorder, viewGroup, false);
        return new ViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.rootView, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                0, 0);
        OrderListBean orderListBean = mData.get(position);
        List<OrderLineBean> order_line = orderListBean.getOrder_line();
        String delivery_name = orderListBean.getDelivery_name();
        holder.itemTakeorderName.setText(delivery_name==null?"暂无":delivery_name);
        holder.itemTakeorderPrice.setText("￥"+ AppUtil.get2xiaoshu(orderListBean.getPay_money()));
        float totalNum = 0;
        List<String> imgList = new ArrayList<>();
        for (int i=0;i<order_line.size();i++){
            OrderLineBean orderLineBean = order_line.get(i);
            totalNum +=Float.valueOf(orderLineBean.getNum());
            ProductListBean product = orderLineBean.getProduct();
            List<BaseImageList> images_list = product.getImages_list();
            if (images_list!=null){
                for (int j=0;j<images_list.size();j++){
                    BaseImageList baseImageList = images_list.get(j);
                    imgList.add(baseImageList.getUrl());
                }
            }
        }
        holder.itemTakeorderNum.setText(String.format("共%s件",totalNum));
        if (imgList.size()>4){
            imgList = imgList.subList(0,4);
        }
        switch (imgList.size()){
            case 4:
                Glide.with(mContext)
                        .load(imgList.get(3))
                        .into(holder.itemTakeorderImg4);
            case 3:
                Glide.with(mContext)
                        .load(imgList.get(2))
                        .into(holder.itemTakeorderImg3);
            case 2:
                Glide.with(mContext)
                        .load(imgList.get(1))
                        .into(holder.itemTakeorderImg2);
            case 1:
                Glide.with(mContext)
                        .load(imgList.get(0))
                        .into(holder.itemTakeorderImg1);
            case 0:
                break;
        }

        String delivery_time = orderListBean.getDelivery_time();
        String create_time = orderListBean.getCreate_time();
        Long dn = Long.valueOf(delivery_time);
        String s = TimeUtil.distanceMin(System.currentTimeMillis() + "", dn);
        holder.itemTakeorderLeftTime.setText(TimeUtil.timeBefore(s));
        String s1 = TimeUtil.distanceMin(create_time, System.currentTimeMillis());
        holder.itemTakeorderTime.setText(TimeUtil.timeBefore(s1));
        holder.itemTakeorderTake.setTag(orderListBean.getStatus());
        if (orderListBean.getStatus().equals("2")){
            holder.itemTakeorderTake.setVisibility(View.VISIBLE);
            holder.itemTakeorderTake.setText("抢单");
        }else if (orderListBean.getStatus().equals("3")){
            holder.itemTakeorderTake.setVisibility(View.VISIBLE);
            holder.itemTakeorderTake.setText("开始配送");
        }else {
            holder.itemTakeorderTake.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_takeorder_img)
        ImageView itemTakeorderImg;
        @BindView(R.id.item_takeorder_name)
        TextView itemTakeorderName;
        @BindView(R.id.item_takeorder_leftTime)
        TextView itemTakeorderLeftTime;
        @BindView(R.id.item_takeorder_img1)
        ImageView itemTakeorderImg1;
        @BindView(R.id.item_takeorder_img2)
        ImageView itemTakeorderImg2;
        @BindView(R.id.item_takeorder_img3)
        ImageView itemTakeorderImg3;
        @BindView(R.id.item_takeorder_img4)
        ImageView itemTakeorderImg4;
        @BindView(R.id.item_takeorder_price)
        TextView itemTakeorderPrice;
        @BindView(R.id.item_takeorder_num)
        TextView itemTakeorderNum;
        @BindView(R.id.item_takeorder_distance)
        TextView itemTakeorderDistance;
        @BindView(R.id.item_takeorder_time)
        TextView itemTakeorderTime;
        @BindView(R.id.item_takeorder_take)
        TextView itemTakeorderTake;
        @BindView(R.id.item_takeorder_rootView)
        LinearLayout rootView;
        @BindView(R.id.item_takeorder_time_ll)
        LinearLayout timeLl;
        private OnItemClickListener clickListener;

        ViewHolder(View view,OnItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.clickListener = clickListener;
            view.setOnClickListener(this);
            itemTakeorderTake.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener !=null){
                if (v.getId() == R.id.item_takeorder_take){
                    clickListener.onTakeClick(v,getPosition());
                }else {
                    clickListener.onItemClick(v,getPosition());
                }
            }

        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onTakeClick(View v,int position);
    }
}
