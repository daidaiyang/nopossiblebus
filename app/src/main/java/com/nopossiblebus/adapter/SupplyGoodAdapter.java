package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblebus.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupplyGoodAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SupplyGoodAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_togood_supplygood, viewGroup, false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (i == mData.size()-1){
            holder.itemLine.setVisibility(View.GONE);
        }else {
            holder.itemLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_img)
        ImageView itemImg;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_already_num)
        TextView itemAlreadyNum;
        @BindView(R.id.item_price)
        TextView itemPrice;
        @BindView(R.id.item_line)
        View itemLine;

        private OnItemClickListener onItemClickListener;

        ViewHolder(View view,OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.onItemClickListener = onItemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener!=null)
            onItemClickListener.onItemClick(v,getPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
