package com.yzx.xiaomusic.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.db.entity.SearchHistory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchHistoryAdapter extends CommonBaseAdapter<SearchHistoryAdapter.Holder, SearchHistory> {


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_search_history, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        SearchHistory searchHistory = datas.get(position);
        holder.tvTitle.setText(searchHistory.getTitle());
        holder.ivDelete.setOnClickListener(v -> {
            DBUtils.getSearchHistoryDao().deleteSearchHistory(searchHistory);
            datas.remove(searchHistory);
            notifyItemRangeRemoved(position, 1);
        });


    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
