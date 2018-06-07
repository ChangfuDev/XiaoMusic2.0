package com.yzx.xiaomusic.ui.adapter;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;

import java.util.List;


public class SearchAdapter extends BaseAdapter {

    List<String> datas;

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = ResourceUtils.parseLayout(parent.getContext(), R.layout.item_search);
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        textView.setText(datas.get(position));
        return view;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
}
