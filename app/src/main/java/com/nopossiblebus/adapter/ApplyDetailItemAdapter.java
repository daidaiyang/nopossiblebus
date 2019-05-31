package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.ApplyOrderLineBean;
import com.nopossiblebus.entity.bean.BaseImageList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyDetailItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ApplyOrderLineBean> mData;

    public ApplyDetailItemAdapter(Context mContext, List<ApplyOrderLineBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_apply_detail_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            ViewHolder holder = (ViewHolder) viewHolder;
        ApplyOrderLineBean applyOrderLineBean = mData.get(position);
        if (position == mData.size()-1){
            holder.line.setVisibility(View.GONE);
        }else {
            holder.line.setVisibility(View.VISIBLE);
        }
        List<BaseImageList> product_image_list = applyOrderLineBean.getProduct_image_list();
        if (product_image_list !=null&&product_image_list.size()>0){
            Glide.with(mContext)
                    .load(product_image_list.get(0).getUrl())
                    .into(holder.img);
        }
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.line)
                View line;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
