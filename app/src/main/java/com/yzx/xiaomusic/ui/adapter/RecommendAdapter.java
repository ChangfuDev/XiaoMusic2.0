package com.yzx.xiaomusic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.ui.songsheet.list.SongSheetListFragment;
import com.yzx.xiaomusic.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by yzx on 2018/6/23.
 * Description 推荐页面Adapter
 */
public class RecommendAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_FOUR = 2;
    public static final int TYPE_TITLE = 3;
    private final SupportFragment parentFragment;

    public RecommendAdapter(SupportFragment parentFragment) {
        this.parentFragment = parentFragment;
    }
//    public static final int TYPE_DEFAULT = 3;

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_BANNER;
            case 1:
                return TYPE_FOUR;
            case 2:
                return TYPE_TITLE;
            default:
                return super.getItemViewType(position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        switch (viewType) {
            case TYPE_BANNER:
                return new BannerHolder(ResourceUtils.parseLayout(context, R.layout.banner, parent));
            case TYPE_FOUR:
                return new FourHolder(ResourceUtils.parseLayout(context, R.layout.item_four, parent));
            case TYPE_TITLE:
                return new TitleHolder(ResourceUtils.parseLayout(context, R.layout.item_title, parent));
            default:
                return new SixHolder(ResourceUtils.parseLayout(context, R.layout.item_recyclerview, parent));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case TYPE_BANNER:
                dealBanner((BannerHolder) holder);
                break;
            case TYPE_FOUR:
                dealFour((FourHolder) holder);
                break;
            case TYPE_TITLE:
                TitleHolder titleHolder = (TitleHolder) holder;
                if (position == 2) {
                    titleHolder.tvTitle.setText(R.string.reommendSongSheet);
                }
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void dealBanner(BannerHolder holder) {
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(holder.recyclerView);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        BannerAdapter adapter = new BannerAdapter();
        holder.recyclerView.setAdapter(adapter);
        Observable
                .interval(3, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    holder.recyclerView.smoothScrollToPosition(aLong.intValue());
                });

    }

    private void dealFour(FourHolder holder) {
        holder.llPrivateFm.setOnClickListener(this);
        holder.llDayRecommend.setOnClickListener(this);
        holder.llSongSheet.setOnClickListener(this);
        holder.llRank.setOnClickListener(this);
        holder.tvToday.setText(TimeUtils.getToday());
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_song_sheet:
                parentFragment.start(new SongSheetListFragment());
                break;
        }
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FourHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_private_fm)
        LinearLayout llPrivateFm;
        @BindView(R.id.ll_day_recommend)
        LinearLayout llDayRecommend;
        @BindView(R.id.ll_song_sheet)
        LinearLayout llSongSheet;
        @BindView(R.id.ll_rank)
        LinearLayout llRank;
        @BindView(R.id.tv_today)
        TextView tvToday;

        public FourHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 三列六个内容
     */
    class SixHolder extends RecyclerView.ViewHolder {

        public SixHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
