package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.ProductListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopuRecyclerAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<ProductListBean> mData;

    private OnPopuItemClick onPopuItemClick;

    public void setOnPopuItemClick(OnPopuItemClick onPopuItemClick) {
        this.onPopuItemClick = onPopuItemClick;
    }

    public PopuRecyclerAdapter(Context mContext, List<ProductListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_popu_item, viewGroup, false);
        return new ViewHolder(view,onPopuItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.text.setText(mData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.text)
        TextView text;
        private OnPopuItemClick onPopuItemClick;

        ViewHolder(View view,OnPopuItemClick onPopuItemClick) {
            super(view);
            ButterKnife.bind(this, view);
            text.setOnClickListener(this);
            this.onPopuItemClick = onPopuItemClick;
        }

        @Override
        public void onClick(View v) {
            if (onPopuItemClick !=null){
                onPopuItemClick.onItemClick(v,getPosition());
            }
        }
    }


    public interface OnPopuItemClick{
        void onItemClick(View v,int position);
    }
}
