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
import com.nopossiblebus.customview.ShadowDrawable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakeOrderItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener clickListener;

    public TakeOrderItemAdapter(Context mContext, List<String> mData) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.rootView, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                0, 0);
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
