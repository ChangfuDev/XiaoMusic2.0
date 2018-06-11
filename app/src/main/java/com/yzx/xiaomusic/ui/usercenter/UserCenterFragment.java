package com.yzx.xiaomusic.ui.usercenter;

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
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.user.UserSongSheet;
import com.yzx.xiaomusic.ui.adapter.UserCenterPagerAdapter;
import com.yzx.xiaomusic.ui.usercenter.about.UserCenterAboutFragment;
import com.yzx.xiaomusic.ui.usercenter.dynamic.UserCenterDynamicFragment;
import com.yzx.xiaomusic.ui.usercenter.music.UserCenterMusicFragment;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserCenterFragment extends BaseFragment {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_cover)
    View ivCover;
    @BindView(R.id.rl_headContainer)
    RelativeLayout rlHeadContainer;
    @BindView(R.id.ll_extra_operate)
    RelativeLayout llExtraOperate;
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_followed_count)
    TextView tvFollowedCount;
    @BindView(R.id.tv_fans_count)
    TextView tvFansCount;
    @BindView(R.id.ll_extra)
    LinearLayout llExtra;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.iv_send_message)
    ImageView ivSendMessage;
    @BindView(R.id.iv_follow)
    ImageView ivFollow;
    Unbinder unbinder;
    private ArrayList<String> tabTitles;
    private ArrayList<Fragment> fragments;

    public static final String KEY_USER_ID = "userId";


    @Override
    protected int initContentViewId() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);


        Bundle arguments = getArguments();

        tabTitles = new ArrayList<>();
        tabTitles.add(ResourceUtils.parseString(getContext(), R.string.music));
        tabTitles.add(ResourceUtils.parseString(getContext(), R.string.dynamic));
        tabTitles.add(ResourceUtils.parseString(getContext(), R.string.aboutTa));

        fragments = new ArrayList<>();
        UserCenterMusicFragment musicFragment = new UserCenterMusicFragment();
        musicFragment.setArguments(arguments);
        fragments.add(musicFragment);

        UserCenterDynamicFragment dynamicFragment = new UserCenterDynamicFragment();
        musicFragment.setArguments(arguments);
        fragments.add(dynamicFragment);

        UserCenterAboutFragment aboutFragment = new UserCenterAboutFragment();
        aboutFragment.setArguments(arguments);
        fragments.add(aboutFragment);

    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        setToolBar(tb);

        UserCenterPagerAdapter adapter = new UserCenterPagerAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(tabTitles);
        viewPager.setAdapter(adapter);
        tl.setupWithViewPager(viewPager);
        ViewGroup.LayoutParams layoutParams = rlHeadContainer.getLayoutParams();
        int headPx = DensityUtils.dip2px(getContext(), 300);
        int coverPx = DensityUtils.dip2px(getContext(), 220);

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

    /**
     * 更新用户信息
     *
     * @param creatorBean
     */
    public void upData(UserSongSheet.PlaylistBean.CreatorBean creatorBean) {

        GlideUtils.loadImg(getContext(), creatorBean.getBackgroundUrl(), ivBg);
        tvTitle.setText(creatorBean.getNickname());
        GlideUtils.loadImg(getContext(), creatorBean.getAvatarUrl(), ivHead);
        tvNickName.setText(creatorBean.getNickname());
        tvDes.setText(creatorBean.getDescription());
        tvFollowedCount.setText(String.format("关注 %s", "未知"));
        tvFansCount.setText(String.format("粉丝 %s", "未知"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
