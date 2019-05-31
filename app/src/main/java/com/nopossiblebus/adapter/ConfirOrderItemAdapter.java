package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.entity.bean.ShopCarProductBean;
import com.nopossiblebus.utils.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirOrderItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ShopCarProductBean> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ConfirOrderItemAdapter(Context mContext, List<ShopCarProductBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_confirorder, viewGroup, false);
        return new ViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            if (i == mData.size()-1){
                //最后一个不显示线
                holder.confirItemLine.setVisibility(View.GONE);
            }else {
                holder.confirItemLine.setVisibility(View.VISIBLE);
            }
        ShopCarProductBean shopCarProductBean = mData.get(i);
        ProductListBean bean = shopCarProductBean.getProduct();
        if (bean.getImages_list()!=null&&bean.getImages_list().size()>0){
            Glide.with(mContext)
                    .load(bean.getImages_list().get(0))
                    .into(holder.confirItemImg);
        }

        holder.confirItemTitle.setText(bean.getName());
        holder.confirItemPrice.setText("￥"+ AppUtil.get2xiaoshu(bean.getStock_price())+"/"+bean.getSpec());
        holder.confirItemNum.setText(shopCarProductBean.getNum()+bean.getSpec());
    }

    @Override
    public int getItemCount() {
        return mData ==null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.confir_item_img)
        ImageView confirItemImg;
        @BindView(R.id.confir_item_title)
        TextView confirItemTitle;
        @BindView(R.id.confir_item_price)
        TextView confirItemPrice;
        @BindView(R.id.confir_item_num)
        TextView confirItemNum;
        @BindView(R.id.confir_item_line)
        View confirItemLine;

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
        void onItemClick(View v,int position);
    }
}
