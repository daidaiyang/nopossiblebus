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
import com.nopossiblebus.entity.bean.ApplyOrderDataBean;
import com.nopossiblebus.entity.bean.ApplyOrderLineBean;
import com.nopossiblebus.entity.bean.BaseImageList;
import com.nopossiblebus.utils.AppUtil;
import com.nopossiblebus.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyGoodAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<ApplyOrderDataBean> mData;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ApplyGoodAdapter(Context mContext, List<ApplyOrderDataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_applygood, viewGroup, false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.rootView, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                0, 0);
        ApplyOrderDataBean applyOrderDataBean = mData.get(position);
        String status = applyOrderDataBean.getStatus();
        String applyStatus = AppUtil.getApplyStatus(status);
        holder.itemStatus.setText(applyStatus);
        holder.itemTime.setText(TimeUtil.timeStamp2Date(applyOrderDataBean.getCreate_time(),"yyyy.MM.DD HH:mm:ss"));
        List<ApplyOrderLineBean> line_list = applyOrderDataBean.getLine_List();
        holder.itemNum.setText(String.format("共%s个商品",line_list.size()));
        List<String> list = new ArrayList<>();
        for (int i=0;i<line_list.size();i++){
            List<BaseImageList> product_image_list = line_list.get(i).getProduct_image_list();
            if (product_image_list !=null){
                for (int j=0;j<product_image_list.size();j++){
                    BaseImageList baseImageList = product_image_list.get(j);
                    if (baseImageList!=null){
                        list.add(baseImageList.getUrl());
                    }
                }
            }
        }
        if (list.size()>4){
            list = list.subList(0, 4);
        }
        switch (list.size()){
            case 4:
                Glide.with(mContext)
                        .load(list.get(3))
                        .into(holder.itemImg4);
            case 3:
                Glide.with(mContext)
                        .load(list.get(2))
                        .into(holder.itemImg3);
            case 2:
                Glide.with(mContext)
                        .load(list.get(1))
                        .into(holder.itemImg2);
            case 1:
                Glide.with(mContext)
                        .load(list.get(0))
                        .into(holder.itemImg1);
            case 0:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_status)
        TextView itemStatus;
        @BindView(R.id.item_num)
        TextView itemNum;
        @BindView(R.id.item_img1)
        ImageView itemImg1;
        @BindView(R.id.item_img2)
        ImageView itemImg2;
        @BindView(R.id.item_img3)
        ImageView itemImg3;
        @BindView(R.id.item_img4)
        ImageView itemImg4;
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_rootview)
        LinearLayout rootView;
        private OnItemClickListener onItemClickListener;

        ViewHolder(View view,OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.onItemClickListener = onItemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClcik(v,getPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClcik(View view,int position);
    }
}
