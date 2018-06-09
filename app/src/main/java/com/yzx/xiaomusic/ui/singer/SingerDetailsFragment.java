package com.yzx.xiaomusic.ui.singer;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.search.SearchSingerResult.ResultBean.ArtistsBean;
import com.yzx.xiaomusic.ui.adapter.SingerDetailPagerAdapter;
import com.yzx.xiaomusic.ui.main.MainActivity;
import com.yzx.xiaomusic.ui.singer.top.Top50Fragment;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.widget.ShapeTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SingerDetailsFragment extends BaseFragment {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.iv_cover)
    View ivCover;
    @BindView(R.id.rl_headContainer)
    RelativeLayout rlHeadContainer;
    @BindView(R.id.stv_collect)
    ShapeTextView stvCollect;
    @BindView(R.id.stv_personal_pager)
    ShapeTextView stvPersonalPager;
    @BindView(R.id.ll_extra_operate)
    LinearLayout llExtraOperate;
    private ArrayList<String> tabTitles;
    private ArrayList<Fragment> fragments;
    private SingerDetailPagerAdapter adapter;

    public static final String KEY_INFO_SINGER = "singerInfo";
    private ArtistsBean singerInfo;
    private ColorDrawable forgroundDrawable;
    private ViewGroup.LayoutParams layoutParams;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_detail_singer;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            singerInfo = (ArtistsBean) arguments.getSerializable(KEY_INFO_SINGER);
        }

        tabTitles = new ArrayList<>();
        tabTitles.add("热门50");
        tabTitles.add("专辑");
        tabTitles.add("视频");
        tabTitles.add("歌手信息");

        fragments = new ArrayList<>();
        fragments.add(new Top50Fragment());
        fragments.add(new AlbumFragment());
        fragments.add(new VideoFragment());
        fragments.add(new InfoFragment());
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(tb);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        //初始化信息
        GlideUtils.loadImg(getContext(), singerInfo.getPicUrl(), ivHead);
        tvTitle.setText(singerInfo.getName());
        adapter = new SingerDetailPagerAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(tabTitles);
        viewPager.setAdapter(adapter);
        tl.setupWithViewPager(viewPager);

//        ViewGroup.LayoutParams layoutParams = ivHead.getLayoutParams();

        layoutParams = rlHeadContainer.getLayoutParams();
        int headPx = DensityUtils.dip2px(getContext(), 300);
        int coverPx = DensityUtils.dip2px(getContext(), 220);

        forgroundDrawable = new ColorDrawable();
        smartRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                super.onHeaderPulling(header, percent, offset, headerHeight, extendHeight);
                if (offset > 0) {
                    float rate = ((float) offset) / ((float) headPx);
                    layoutParams.height = (int) (headPx * (1f + rate));
                    rlHeadContainer.setLayoutParams(layoutParams);
                }
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int footerHeight, int extendHeight) {
                super.onHeaderReleasing(header, percent, offset, footerHeight, extendHeight);
                if (offset > 0) {
                    float rate = ((float) offset) / ((float) headPx);
                    layoutParams.height = (int) (headPx * (1f + rate));
                    rlHeadContainer.setLayoutParams(layoutParams);
                }
            }
        });

        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset <= 0) {
                layoutParams.height = headPx + verticalOffset;
                rlHeadContainer.setLayoutParams(layoutParams);
                float alpha = -((float) verticalOffset) / ((float) coverPx);
                ivCover.setAlpha(alpha);
                llExtraOperate.setAlpha(1 - alpha);
            }
        });
    }


    @OnClick({R.id.stv_collect, R.id.stv_personal_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_collect:
                break;
            case R.id.stv_personal_pager:
                break;
        }
    }
}
