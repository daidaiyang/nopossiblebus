package com.nopossiblebus.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.entity.bean.ProductListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultPopu extends PopupWindow {


    @BindView(R.id.listView)
    ListView listView;
    private View contentView;
    private List<ProductListBean> mData;
    private Context mContext;
    private OnItemClick onItemClick;
    private PopAdapter mAdapter;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public SearchResultPopu(Context context,List<ProductListBean> mData) {
        super(context);
        mContext = context;
        this.mData = mData;
        contentView = LayoutInflater.from(mContext)
                .inflate(R.layout.popuwindow_list, null);
        this.setContentView(contentView);
        ButterKnife.bind(this,contentView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        initData();
    }


    public void setmData(List<ProductListBean> mData) {
        this.mData.clear();
        this.mData.addAll(mData);
        mAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mAdapter = new PopAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemClick!=null){
                    ProductListBean productListBean = mData.get(position);
                    onItemClick.onItemClick(view,productListBean.getName(),productListBean.getId());
                    SearchResultPopu.this.dismiss();
                }
            }
        });

    }


    public interface OnItemClick{
        void onItemClick(View view,String name,String id);
    }


    public class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.recycler_popu_item,null);
            holder.text = convertView.findViewById(R.id.text);
            ProductListBean productListBean = mData.get(position);
            holder.text.setText(productListBean.getName());
            holder.text.setTag(productListBean.getId());
            return convertView;
        }


        private class ViewHolder {
            private TextView text;
        }
    }

}
