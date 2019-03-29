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

public class ApplyGoodAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<String> mData;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ApplyGoodAdapter(Context mContext, List<String> mData) {
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
