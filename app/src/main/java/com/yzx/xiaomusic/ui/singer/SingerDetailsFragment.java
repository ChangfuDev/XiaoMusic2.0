package com.yzx.xiaomusic.ui.singer;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.yzx.xiaomusic.model.entity.SingerTopInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.SingerDetailPagerAdapter;
import com.yzx.xiaomusic.ui.singer.album.AlbumFragment;
import com.yzx.xiaomusic.ui.singer.info.InfoFragment;
import com.yzx.xiaomusic.ui.singer.top.Top50Fragment;
import com.yzx.xiaomusic.ui.singer.video.VideoFragment;
import com.yzx.xiaomusic.ui.usercenter.UserCenterFragment;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.widget.CircleProgress;
import com.yzx.xiaomusic.widget.ShapeTextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;
import static com.yzx.xiaomusic.ui.usercenter.UserCenterFragment.KEY_USER_ID;

/**
 * @author yzx
 */
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
    Unbinder unbinder;
    private ArrayList<String> tabTitles;
    private ArrayList<Fragment> fragments;

    public static final String KEY_ID_SINGER = "singerId";

    private String singerId;
    private SingerDetailPagerAdapter adapter;
    private MusicInfo musicInfo;
    private int accountId;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_detail_singer;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            singerId = arguments.getString(KEY_ID_SINGER);
        }

        tabTitles = new ArrayList<>();
        tabTitles.add("热门50");
        tabTitles.add("专辑");
        tabTitles.add("视频");
        tabTitles.add("歌手信息");

    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        initToolBar(tb);

        //初始化信息
        viewPager.setOffscreenPageLimit(4);
        adapter = new SingerDetailPagerAdapter(getChildFragmentManager());
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

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);

        fragments = new ArrayList<>();
        Top50Fragment top50Fragment = new Top50Fragment();
        top50Fragment.setArguments(getArguments());
        fragments.add(top50Fragment);

        AlbumFragment albumFragment = new AlbumFragment();
        albumFragment.setArguments(getArguments());
        fragments.add(albumFragment);
        fragments.add(new VideoFragment());

        InfoFragment infoFragment = new InfoFragment();
        infoFragment.setArguments(getArguments());
        fragments.add(infoFragment);

        adapter.setFragments(fragments);
    }

    @OnClick({R.id.stv_collect, R.id.stv_personal_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_collect:
                break;
            case R.id.stv_personal_pager:
                Bundle bundle = new Bundle();
                bundle.putString(KEY_USER_ID, String.valueOf(accountId));
                easyStart(new UserCenterFragment(), bundle);
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_share, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                showToast(R.string.share);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        EventBusUtils.register(this);
        initBottomMusicController(layoutBottomMusicController);
        musicInfo = service.getMusicInfo();
    }

    public void setData(SingerTopInfo.ArtistBean artist) {
        GlideUtils.loadImg(getContext(), artist.getPicUrl(), GlideUtils.TYPE_BG_SINGER, ivHead, false);
        tvTitle.setText(artist.getName());
        accountId = artist.getAccountId();
    }


    @Override
    public void onDestroy() {
        EventBusUtils.unregister(this);
        super.onDestroy();
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
