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
import com.nopossiblebus.entity.bean.BaseImageList;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.utils.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyKucunRightItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ProductListBean> mData;

    public MyKucunRightItemAdapter(Context mContext, List<ProductListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mykucun_right, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ProductListBean productListBean = mData.get(position);
        List<BaseImageList> images_list = productListBean.getImages_list();
        if (images_list!=null&&images_list.size()>0){
            BaseImageList baseImageList = images_list.get(0);
            Glide.with(mContext)
                    .load(baseImageList.getUrl())
                    .into(holder.rightitemImg);
        }
        holder.rightitemTitle.setText(productListBean.getName());
        holder.rightitemPriceSale.setText("￥"+AppUtil.get2xiaoshu(productListBean.getSell_price()));
        holder.rightitemPriceBuy.setText("￥"+AppUtil.get2xiaoshu(productListBean.getStock_price()));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rightitem_img)
        ImageView rightitemImg;
        @BindView(R.id.rightitem_title)
        TextView rightitemTitle;
        @BindView(R.id.rightitem_price_sale)
        TextView rightitemPriceSale;
        @BindView(R.id.rightitem_price_buy)
        TextView rightitemPriceBuy;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
