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

public class IngoodorderItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public IngoodorderItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ingood_order, viewGroup, false);
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
        return mData==null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_gongimg)
        ImageView gongimg;
        @BindView(R.id.item_gongname)
        TextView gongname;
        @BindView(R.id.item_good_img1)
        ImageView img1;
        @BindView(R.id.item_good_img2)
        ImageView img2;
        @BindView(R.id.item_good_img3)
        ImageView img3;
        @BindView(R.id.item_good_img4)
        ImageView img4;
        @BindView(R.id.item_good_price)
        TextView price;
        @BindView(R.id.item_good_num)
        TextView num;
        @BindView(R.id.item_time)
        TextView time;
        @BindView(R.id.item_status_unfinish)
        TextView unfinish;
        @BindView(R.id.item_status_finish)
        TextView finish;
        @BindView(R.id.item_ingood_order_root)
        LinearLayout rootView;

        private OnItemClickListener clickListener;

        ViewHolder(View view,OnItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.clickListener = clickListener;
            view.setOnClickListener(this);
            unfinish.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener !=null){
                if (v.getId() == R.id.item_status_unfinish){
                    clickListener.onOperationClick(v,getPosition());
                }else{
                    clickListener.onItemClick(v,getPosition());
                }
            }

        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onOperationClick(View v,int position);
    }
}
