package com.yzx.commonlibrary.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yzx
 * @date 2018/4/26
 * Description
 */
public abstract class CommonBaseAdapter<VH extends RecyclerView.ViewHolder, B> extends RecyclerView.Adapter<VH> {

    public List<B> datas;
    private OnItemClickListener onItemClickListener;

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        /**
         * 条目点击事件
         */
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(holder.itemView, position);
            }
        });

    }

    /**
     * 设置数据
     *
     * @param originalData
     */
    public void setData(List<B> originalData) {
        if (originalData != null) {
            datas = originalData;
            notifyItemRangeInserted(0, originalData.size());
        }
    }

    /**
     * 添加数据
     *
     * @param newData
     */
    public void addData(List<B> newData) {
        if (newData == null) {
            return;
        }

        if (datas == null) {
            datas = newData;
            notifyItemRangeInserted(0, newData.size());
        } else {
            datas.addAll(newData);
            notifyItemRangeInserted(datas.size() - newData.size(), newData.size());
        }
    }

    /**
     * 添加数据
     *
     * @param newData
     */
    public void addOneData(B newData) {
        if (newData == null) {
            return;
        }

        if (datas == null) {
            datas = new ArrayList<B>();
            datas.add(newData);
            notifyItemRangeInserted(0, 1);
        } else {
            datas.add(newData);
            notifyItemRangeInserted(datas.size(), 1);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
