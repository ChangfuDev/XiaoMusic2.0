package com.yzx.xiaomusic.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseFragmentPagerAdapter;
import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.NavigationHeadAdapter;
import com.yzx.xiaomusic.ui.main.discover.DiscoverFragment;
import com.yzx.xiaomusic.ui.main.music.MusicFragment;
import com.yzx.xiaomusic.ui.main.video.VideoFragment;
import com.yzx.xiaomusic.ui.search.SearchFragment;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.widget.CircleProgress;
import com.yzx.xiaomusic.widget.simplelistenner.SimpleTabChangeListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_SERVICE_CREATED;

/**
 * @author yzx
 */
public class MainFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener {
    private static final String TAG = "yglMainFragment";
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
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
    private ArrayList<Integer> navigationMenuTitles;
    private ArrayList<Integer> navigationMenuIcons;
    private ArrayList<Fragment> fragments;
    private MusicInfo musicInfo;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        //menu文案
        navigationMenuTitles = new ArrayList<>();
        navigationMenuTitles.add(R.string.myMessage);
        navigationMenuTitles.add(R.string.vip);
        navigationMenuTitles.add(R.string.shoppingMall);
        navigationMenuTitles.add(R.string.gameRecommend);
        navigationMenuTitles.add(R.string.listenOnLine);

        navigationMenuTitles.add(R.string.myFriend);
        navigationMenuTitles.add(R.string.nearbyPerson);

        navigationMenuTitles.add(R.string.personalSkin);
        navigationMenuTitles.add(R.string.findSongByListen);
        navigationMenuTitles.add(R.string.stopPlayTimely);
        navigationMenuTitles.add(R.string.scan);
        navigationMenuTitles.add(R.string.musicAlarm);
        navigationMenuTitles.add(R.string.driveMode);
        navigationMenuTitles.add(R.string.musicCloudDisk);
        navigationMenuTitles.add(R.string.coupon);

        //menuIcon
        navigationMenuIcons = new ArrayList<>();
        navigationMenuIcons.add(R.drawable.ak4);
        navigationMenuIcons.add(R.drawable.akc);
        navigationMenuIcons.add(R.drawable.ak_);
        navigationMenuIcons.add(R.drawable.ak1);
        navigationMenuIcons.add(R.drawable.ajz);

        navigationMenuIcons.add(R.drawable.ak0);
        navigationMenuIcons.add(R.drawable.ak6);

        navigationMenuIcons.add(R.drawable.ak9);
        navigationMenuIcons.add(R.drawable.ak2);
        navigationMenuIcons.add(R.drawable.aka);
        navigationMenuIcons.add(R.drawable.ak6);
        navigationMenuIcons.add(R.drawable.akb);
        navigationMenuIcons.add(R.drawable.aju);
        navigationMenuIcons.add(R.drawable.ajv);
        navigationMenuIcons.add(R.drawable.ajx);

        fragments = new ArrayList<>();
        fragments.add(new MusicFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new VideoFragment());

        service = ServiceManager.getInstance().getService();
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        EventBusUtils.register(this);
        initBottomMusicController(layoutBottomMusicController);
        initNavigationView();
        tb.setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        tb.inflateMenu(R.menu.menu_main);
        tb.setOnMenuItemClickListener(this);
        tl.addTab(tl.newTab().setIcon(R.drawable.ic_music));
        tl.addTab(tl.newTab().setIcon(R.drawable.ic_discover));
        tl.addTab(tl.newTab().setIcon(R.drawable.ic_video));

        CommonBaseFragmentPagerAdapter pagerAdapter = new CommonBaseFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.setFragments(fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tl.getTabAt(position).select();
            }
        });
        tl.addOnTabSelectedListener(new SimpleTabChangeListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    /**
     * 初始化NavigationView
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initNavigationView() {

        LinearLayout navigationViewHeaderView = (LinearLayout) navigationView.getHeaderView(0);
        ViewGroup.LayoutParams layoutParams = navigationViewHeaderView.getLayoutParams();
        layoutParams.height = getResources().getDisplayMetrics().heightPixels;
        navigationViewHeaderView.setLayoutParams(layoutParams);

        RecyclerView recyclerView = (RecyclerView) navigationViewHeaderView.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (recyclerView.getChildLayoutPosition(view) == 4 || recyclerView.getChildLayoutPosition(view) == 6) {
                    outRect.bottom = DensityUtils.dip2px(getContext(), 5);
                }
            }
        });
        NavigationHeadAdapter adapter = new NavigationHeadAdapter();
        adapter.setData(navigationMenuIcons, navigationMenuTitles);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                easyStart(new SearchFragment());
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        } else {
            //返回到桌面
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            startActivity(homeIntent);
            return true;
        }
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
                musicInfo = service.getMusicInfo();
                Integer content = (Integer) event.getContent();
                ivPlayPause.setMax((int) musicInfo.getDuration());
                ivPlayPause.setProgress(content);
                break;
            case TYPE_SERVICE_CREATED:
                service = ServiceManager.getInstance().getService();
                initBottomMusicController(layoutBottomMusicController);
                break;
        }
    }
}
