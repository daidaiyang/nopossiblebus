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

public class AnalysisSingleNumberAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<String> mData;


    public AnalysisSingleNumberAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_analysis_single, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.singleItemNum.setText((i+1)+"");
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.single_item_num)
        TextView singleItemNum;
        @BindView(R.id.single_item_img)
        ImageView singleItemImg;
        @BindView(R.id.single_item_title)
        TextView singleItemTitle;
        @BindView(R.id.single_item_yeserday_num)
        TextView singleItemYeserdayNum;
        @BindView(R.id.single_item_all_num)
        TextView singleItemAllNum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
