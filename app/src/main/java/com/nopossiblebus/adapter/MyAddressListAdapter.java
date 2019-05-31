package com.nopossiblebus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.MyAddressListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAddressListAdapter extends RecyclerView.Adapter {

    private List<MyAddressListBean> mData;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MyAddressListAdapter(List<MyAddressListBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_myaddress_item, viewGroup, false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
        MyAddressListBean myAddressListBean = mData.get(i);
        holder.name.setText(myAddressListBean.getContacts());
        holder.tel.setText(myAddressListBean.getPhone());
        holder.address.setText(myAddressListBean.getProvince_name()+myAddressListBean.getCity_name()+myAddressListBean.getDistrict_name()+myAddressListBean.getAddress());
        if (myAddressListBean.getHot()!=null&&myAddressListBean.getHot().equals("1")){
            holder.morenCk.setChecked(true);
        }else {
            holder.morenCk.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.tel)
        TextView tel;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.moren)
        LinearLayout moren;
        @BindView(R.id.edit)
        LinearLayout edit;
        @BindView(R.id.delete)
        LinearLayout delete;
        @BindView(R.id.moren_ck)
        RadioButton morenCk;

        private OnItemClickListener onItemClickListener;

        ViewHolder(View view,OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.onItemClickListener = onItemClickListener;
            view.setOnClickListener(this);
            moren.setOnClickListener(this);
            morenCk.setOnClickListener(this);
            edit.setOnClickListener(this);
            delete.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (onItemClickListener !=null){
                if (v.getId() == R.id.moren||v.getId() == R.id.moren_ck){
                    morenCk.setChecked(true);
                    onItemClickListener.onMorenClick(v,getPosition());
                }else if (v.getId() == R.id.edit){
                    onItemClickListener.onEditClick(v,getPosition());
                }else if (v.getId() == R.id.delete){
                    onItemClickListener.onDeleteClick(v,getPosition());
                }else {
                    onItemClickListener.onItemClick(v,getPosition());
                }
            }


        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onMorenClick(View view, int position);
        void onEditClick(View view, int position);
        void onDeleteClick(View view, int posotion);
    }


}
