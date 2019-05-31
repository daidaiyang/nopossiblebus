package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.TypeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 一鍵下單以及我的商品庫存復用一個
 */
public class OneKeyLeftItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<TypeBean> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public OneKeyLeftItemAdapter(Context mContext, List<TypeBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_onekeysaveorder_left, viewGroup, false);
        return new ViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        TypeBean typeBean = mData.get(position);
        if (typeBean.isChecked()){
            holder.leftitemLeftLine.setVisibility(View.VISIBLE);
        }else {
            holder.leftitemLeftLine.setVisibility(View.GONE);
        }
        holder.leftitemTitle.setChecked(typeBean.isChecked());
        holder.leftitemTitle.setText(typeBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.leftitem_leftLine)
        View leftitemLeftLine;
        @BindView(R.id.leftitem_title)
        RadioButton leftitemTitle;
        @BindView(R.id.leftitem_root)
        RelativeLayout leftitemRoot;
        private OnItemClickListener clickListener;

        ViewHolder(View view,OnItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.clickListener = clickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener!=null){
                clickListener.onItemClick(v,getPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }
}
