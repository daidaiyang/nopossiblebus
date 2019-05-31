package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.MyIntegralBean;
import com.nopossiblebus.utils.AppUtil;
import com.nopossiblebus.utils.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyIntegralAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<MyIntegralBean> mData;

    public MyIntegralAdapter(Context mContext, List<MyIntegralBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_myintegral, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        MyIntegralBean myIntegralBean = mData.get(position);
        holder.itemScoreType.setText(myIntegralBean.getRemark());
        holder.itemLeftScore.setText(AppUtil.get2xiaoshu(myIntegralBean.getBalance()));
        holder.itemScoreTime.setText(TimeUtil.timeStamp2Date(myIntegralBean.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
        String trans_type = myIntegralBean.getTrans_type();
        if (trans_type.equals("2")){
            holder.itemScore.setText("-"+AppUtil.get2xiaoshu(myIntegralBean.getTrans_num()));
            holder.itemScore.setTextColor(mContext.getResources().getColor(R.color.text_fb4));
        }else {
            holder.itemScore.setText("+"+AppUtil.get2xiaoshu(myIntegralBean.getTrans_num()));
            holder.itemScore.setTextColor(mContext.getResources().getColor(R.color.text_black_e0c));
        }
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_score_type)
        TextView itemScoreType;
        @BindView(R.id.item_score_time)
        TextView itemScoreTime;
        @BindView(R.id.item_score)
        TextView itemScore;
        @BindView(R.id.item_leftScore)
        TextView itemLeftScore;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
