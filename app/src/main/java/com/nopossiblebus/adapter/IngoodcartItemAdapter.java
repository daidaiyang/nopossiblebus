package com.nopossiblebus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.customview.ShadowDrawable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngoodcartItemAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<String> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public IngoodcartItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ingood_cart, viewGroup, false);
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

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        @BindView(R.id.item_check)
        CheckBox itemCheck;
        @BindView(R.id.item_img)
        ImageView itemImg;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_xiao_price)
        TextView itemXiaoPrice;
        @BindView(R.id.item_jin_price)
        TextView itemJinPrice;
        @BindView(R.id.item_sub)
        ImageView itemSub;
        @BindView(R.id.item_num)
        TextView itemNum;
        @BindView(R.id.item_plus)
        ImageView itemPlus;
        @BindView(R.id.item_standar)
        TextView itemStandar;
        @BindView(R.id.item_rootview)
        RelativeLayout rootView;
        private OnItemClickListener clickListener;

        ViewHolder(View view,OnItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.clickListener = clickListener;
            view.setOnClickListener(this);
            itemCheck.setOnCheckedChangeListener(this);
            itemSub.setOnClickListener(this);
            itemPlus.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (clickListener !=null){
                if (v.getId() == R.id.item_sub){
                    clickListener.onSubClick(v,getPosition());
                }else
                if (v.getId() == R.id.item_plus){
                    clickListener.onPlusClick(v,getPosition());
                }else {
                    clickListener.onItemClick(v,getPosition());
                }
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (clickListener !=null){
                clickListener.onCheckedClick(buttonView,getPosition(),isChecked);
            }
        }
    }

    public interface OnItemClickListener{
        void onCheckedClick(View v,int position,boolean isChecked);
        void onSubClick(View v,int position);
        void onPlusClick(View v,int position);
        void onItemClick(View v,int position);
    }
}
