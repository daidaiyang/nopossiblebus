package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.GroupTypeBean;
import com.nopossiblebus.entity.bean.TypeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IdentifyGroupAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<GroupTypeBean> mData;

    private OnItemclick onItemclick;

    public void setOnItemclick(OnItemclick onItemclick) {
        this.onItemclick = onItemclick;
    }

    public IdentifyGroupAdapter(Context mContext, List<GroupTypeBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_identify_group_item, viewGroup, false);
        return new ViewHolder(view,onItemclick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            ViewHolder holder = (ViewHolder) viewHolder;
            GroupTypeBean typeBean = mData.get(position);
            holder.checkbox.setChecked(typeBean.isChecked());
            holder.textInfo.setText(typeBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.checkbox)
        CheckBox checkbox;
        @BindView(R.id.text_info)
        TextView textInfo;
        @BindView(R.id.root)
        LinearLayout root;
        private OnItemclick onItemclick;

        ViewHolder(View view,OnItemclick onItemclick) {
            super(view);
            ButterKnife.bind(this, view);
            this.onItemclick = onItemclick;
            root.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemclick != null){
                onItemclick.onItemClick(v,getPosition());
            }
        }
    }


    public interface  OnItemclick{
        void onItemClick(View view,int position);
    }
}
