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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nopossiblebus.R;
import com.nopossiblebus.customview.ShadowDrawable;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.entity.bean.ShopCarProductBean;
import com.nopossiblebus.utils.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngoodcartItemAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<ShopCarProductBean> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public IngoodcartItemAdapter(Context mContext, List<ShopCarProductBean> mData) {
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
        ShopCarProductBean shopCarProductBean = mData.get(i);
        ProductListBean product = shopCarProductBean.getProduct();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.picture_show);
//        if (product.getImages_list()!=null&&product.getImages_list().get(0)!=null){
//            Glide.with(mContext)
//                    .load(product.getImages_list().get(0))
//                    .apply(requestOptions)
//                    .into(holder.itemImg);
//        }
        holder.itemTitle.setText(product.getName());
        holder.itemXiaoPrice.setText(AppUtil.get2xiaoshu(product.getSell_price()));
        holder.itemJinPrice.setText(AppUtil.get2xiaoshu(product.getStock_price()));
        holder.itemStandar.setText(product.getSpec());
        holder.itemNum.setText(shopCarProductBean.getNum()+"");
        if (shopCarProductBean.isChecked()){
            holder.itemCheck.setChecked(true);
        }else {
            holder.itemCheck.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            itemCheck.setOnClickListener(this);
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
                }else if (v.getId() == R.id.item_check){
                    clickListener.onCheckedClick(v,getPosition(),((CheckBox) v).isChecked());
                }else {
                    clickListener.onItemClick(v,getPosition());
                }
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
