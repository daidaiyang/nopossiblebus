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
import com.nopossiblebus.entity.bean.OrderLineBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.utils.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakeOrderDetailRightItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<OrderLineBean> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public TakeOrderDetailRightItemAdapter(Context mContext, List<OrderLineBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_takeorder_detail, viewGroup, false);
        return new ViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            ViewHolder holder = (ViewHolder) viewHolder;
        OrderLineBean orderLineBean = mData.get(position);
        ProductListBean product = orderLineBean.getProduct();
        List<BaseImageList> images_list = product.getImages_list();
        if (images_list!=null&&images_list.size()>0){
            Glide.with(mContext)
                    .load(images_list.get(0).getUrl())
                    .into(holder.detailImg);
        }
        holder.detailTitle.setText(product.getName());
        holder.detailNum.setText(AppUtil.get2xiaoshu(orderLineBean.getNum())+product.getSpec());
        holder.detailPrice.setText("￥"+AppUtil.get2xiaoshu(product.getSell_price()));
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.detail_img)
        ImageView detailImg;
        @BindView(R.id.detail_title)
        TextView detailTitle;
        @BindView(R.id.detail_price)
        TextView detailPrice;
        @BindView(R.id.detail_num)
        TextView detailNum;
        private OnItemClickListener clickListener;

        ViewHolder(View view,OnItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.clickListener = clickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener!=null)
            clickListener.onItemClick(v,getPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

}
