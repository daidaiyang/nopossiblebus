package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblebus.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.constraint.Constraints.TAG;

public class OneKeyRightItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public OneKeyRightItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_onekeysaveorder_right, viewGroup, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }



    static
    class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.rightitem_img)
        ImageView rightitemImg;
        @BindView(R.id.rightitem_selected)
        CheckBox rightitemSelected;
        @BindView(R.id.rightitem_title)
        TextView rightitemTitle;
        @BindView(R.id.rightitem_xiao_price)
        TextView rightitemXiaoPrice;
        @BindView(R.id.rightitem_jin_price)
        TextView rightitemJinPrice;
        @BindView(R.id.rightitem_sub)
        ImageView rightitemSub;
        @BindView(R.id.rightitem_num)
        TextView rightitemNum;
        @BindView(R.id.rightitem_plus)
        ImageView rightitemPlus;

        private OnItemClickListener clickListener;

        ViewHolder(View view, OnItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            ButterKnife.bind(this, view);
            this.clickListener = clickListener;
            view.setOnClickListener(this);
            rightitemPlus.setOnClickListener(this);
            rightitemSub.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int num = Integer.parseInt(rightitemNum.getText().toString());
            switch (v.getId()) {
                case R.id.rightitem_plus:
                    clickListener.onPlusClick(v, getPosition());
                    Log.d(TAG, "onClick: rightitem_plus");
                    break;
                case R.id.rightitem_sub:
                    clickListener.onSubClick(v, getPosition());
                    Log.d(TAG, "onClick: rightitem_sub");
                    break;
                default:
                    clickListener.onItemClick(v, getPosition());
                    Log.d(TAG, "onClick: default");
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onSubClick(View v, int position);

        void onPlusClick(View v, int position);
    }
}
