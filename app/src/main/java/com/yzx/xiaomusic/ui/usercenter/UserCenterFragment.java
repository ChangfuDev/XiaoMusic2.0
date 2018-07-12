package com.yzx.xiaomusic.ui.usercenter;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.othershe.library.NiceImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.model.entity.user.UserSongSheet;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.UserCenterPagerAdapter;
import com.yzx.xiaomusic.ui.usercenter.about.UserCenterAboutFragment;
import com.yzx.xiaomusic.ui.usercenter.dynamic.UserCenterDynamicFragment;
import com.yzx.xiaomusic.ui.usercenter.music.UserCenterMusicFragment;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.widget.CircleProgress;
import com.yzx.xiaomusic.widget.tab.TabEntity;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;

/**
 * @author yzx
 */
public class UserCenterFragment extends BaseFragment {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_cover)
    View ivCover;
    @BindView(R.id.rl_headContainer)
    RelativeLayout rlHeadContainer;
    @BindView(R.id.ll_extra_operate)
    RelativeLayout llExtraOperate;
    @BindView(R.id.tabLayout)
    CommonTabLayout tabLayout;
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
    NiceImageView ivHead;
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
    @BindView(R.id.iv_music_cover)
    ImageView ivMusicCover;
    @BindView(R.id.tv_music_name)
    TextView tvMusicName;
    @BindView(R.id.tv_music_singer)
    TextView tvMusicSinger;
    @BindView(R.id.iv_play_pause)
    CircleProgress ivPlayPause;
    @BindView(R.id.iv_song_sheet)
    ImageView ivSongSheet;
    @BindView(R.id.layout_bottom_music_controller)
    LinearLayout layoutBottomMusicController;
    private ArrayList<CustomTabEntity> tabEntities;
    private ArrayList<Fragment> fragments;

    public static final String KEY_USER_ID = "userId";
    private UserCenterPagerAdapter adapter;
    private MusicInfo musicInfo;


    @Override
    protected int initContentViewId() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("音乐"));
        tabEntities.add(new TabEntity("动态"));
        tabEntities.add(new TabEntity("关于TA"));
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        initToolBar(tb);
        adapter = new UserCenterPagerAdapter(getChildFragmentManager());
        setUpViewPager(viewPager, tabLayout, adapter, fragments, tabEntities);
        initElasticHead(rlHeadContainer, smartRefreshLayout, appBarLayout, ivCover, llExtraOperate);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        Bundle arguments = getArguments();

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
        adapter.setFragments(fragments);
    }

    /**
     * 更新用户信息
     *
     * @param creatorBean
     */
    public void upData(UserSongSheet.PlaylistBean.CreatorBean creatorBean) {

        GlideUtils.loadImg(getContext(), creatorBean.getBackgroundUrl(), GlideUtils.TYPE_BG_USER, ivBg, false);
        tvTitle.setText(creatorBean.getNickname());
        GlideUtils.loadCircleImg(getContext(), creatorBean.getAvatarUrl(), ivHead);
        tvNickName.setText(creatorBean.getNickname());
        tvDes.setText(creatorBean.getDescription());
        tvDes.setVisibility(TextUtils.isEmpty(creatorBean.getDescription()) ? View.GONE : View.VISIBLE);
        tvFollowedCount.setText(String.format("关注 %s", "未知"));
        tvFansCount.setText(String.format("粉丝 %s", "未知"));
    }


    @Override
    public void onResume() {
        super.onResume();
        EventBusUtils.register(this);
        initBottomMusicController(layoutBottomMusicController);
        musicInfo = service.getMusicInfo();
    }

    @Override
    public void onDestroyView() {
        EventBusUtils.unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case TYPE_MUSIC_CHANGED:
                service = ServiceManager.getInstance().getService();
                musicInfo = service.getMusicInfo();
                tvMusicName.setText(musicInfo.getMusicName());
                tvMusicSinger.setText(MusicDataUtils.getSingers(musicInfo));
                if (!musicInfo.isLocal()) {
                    GlideUtils.loadImg(getContext(), musicInfo.getAlbumCoverPath(), ivMusicCover);
                }
                break;
            case TYPE_MUSIC_PLAYING:
                ivPlayPause.setState(CircleProgress.STATE_PLAY);
                break;
            case TYPE_MUSIC_PAUSE:
                ivPlayPause.setState(CircleProgress.STATE_PAUSE);
                break;
            case TYPE_MUSIC_UPDATE_PROGRESS:
                Integer content = (Integer) event.getContent();
                ivPlayPause.setMax((int) musicInfo.getDuration());
                ivPlayPause.setProgress(content);
                break;
        }
    }
}
