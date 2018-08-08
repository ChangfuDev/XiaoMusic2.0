package com.yzx.xiaomusic.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseFragmentPagerAdapter;
import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.app.MusicApplication;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.NavigationHeadAdapter;
import com.yzx.xiaomusic.ui.login.LoginFragment;
import com.yzx.xiaomusic.ui.main.discover.DiscoverFragment;
import com.yzx.xiaomusic.ui.main.music.MusicFragment;
import com.yzx.xiaomusic.ui.main.navigation.GradeFragment;
import com.yzx.xiaomusic.ui.main.navigation.ListeningToSongFragment;
import com.yzx.xiaomusic.ui.main.navigation.SignInFragment;
import com.yzx.xiaomusic.ui.main.video.VideoFragment;
import com.yzx.xiaomusic.ui.search.SearchFragment;
import com.yzx.xiaomusic.ui.setting.SettingFragment;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.FragmentStartUtils;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.widget.CircleProgress;
import com.yzx.xiaomusic.widget.simplelistenner.SimpleTabChangeListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_SERVICE_CREATED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_USER_INFOR_CHANGED;

/**
 * @author yzx
 */
public class MainFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener, NavigationHeadAdapter.OnItemClickListenner {
    private static final String TAG = "yglMainFragment";
    public static final int NAV_MY_MESSAGE = 0;
    public static final int NAV_VIP = 1;
    public static final int NAV_SHOP = 2;
    public static final int NAV_GAME = 3;
    public static final int NAV_FREE_LISTEN = 4;
    public static final int NAV_MY_FRIEND = 5;
    public static final int NAV_NEARBY_PERSON = 6;
    public static final int NAV_CHNAGE_SKIN = 7;
    public static final int NAV_LISTENING_TO_SONG = 8;
    public static final int NAV_STOP_TIMELY = 9;
    public static final int NAV_SCAN = 10;
    public static final int NAV_MUSIC_ALRAM = 11;
    public static final int NAV_DRIVE_MODE = 12;
    public static final int NAV_CLOUD = 13;
    public static final int NAV_COUPON = 14;
    public static final int NAV_LOGIN = 15;
    public static final int NAV_SETTING = 16;
    public static final int NAV_SIGN_IN = 17;
    public static final int NAV_GRADE = 18;
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.navigationLayout)
    LinearLayout navigationLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.drawerLayout)
    public DrawerLayout drawerLayout;
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
    @BindView(R.id.ll_night_mode)
    LinearLayout llNightMode;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.ll_exit)
    LinearLayout llExit;
    @BindView(R.id.layout_bottom_music_controller)
    LinearLayout layoutBottomMusicController;

    private ArrayList<Fragment> fragments;
    private MusicInfo musicInfo;
    private NavigationHeadAdapter adapter;
    private MainFragment mainFragment;
    private String[] titles;
    private int[] icons;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        mainFragment = this;
        //menu文案
        titles = getResources().getStringArray(R.array.navigationListTitle);
        //图标
        icons = new int[]{R.drawable.ak4, R.drawable.akc, R.drawable.ak_, R.drawable.ak1, R.drawable.ajz,
                R.drawable.ak0, R.drawable.ak6,
                R.drawable.ak9, R.drawable.ak2, R.drawable.aka, R.drawable.ak6,
                R.drawable.akb, R.drawable.aju, R.drawable.ajv, R.drawable.ajx};

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

        //根据tag跳转至目标Fragment
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                SupportFragment toTargetFragment = null;
                switch ((int) drawerLayout.getTag(R.id.drawerLayout)) {
                    case NAV_LISTENING_TO_SONG:
                        toTargetFragment = new ListeningToSongFragment();
                        break;
                    case NAV_LOGIN:
                        toTargetFragment = new LoginFragment();
                        break;
                    case NAV_SETTING:
                        toTargetFragment = new SettingFragment();
                        break;
                    case NAV_SIGN_IN:
                        toTargetFragment = new SignInFragment();
                        break;
                    case NAV_GRADE:
                        toTargetFragment = new GradeFragment();
                        break;
                }

                if (toTargetFragment == null) {
                    return;
                }
                FragmentStartUtils.startFragment(mainFragment, toTargetFragment);
            }
        });

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

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (recyclerView.getChildLayoutPosition(view) == 5 || recyclerView.getChildLayoutPosition(view) == 7) {
                    outRect.bottom = DensityUtils.dip2px(getContext(), 5);
                }
            }
        });
        adapter = new NavigationHeadAdapter();
        adapter.setData(this, titles, icons);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
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
            case TYPE_USER_INFOR_CHANGED:
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick({R.id.ll_night_mode, R.id.ll_setting, R.id.ll_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_night_mode:
                break;
            case R.id.ll_setting:
                closeNavigationAndSetTag(NAV_SETTING);
                break;
            case R.id.ll_exit:
                MusicApplication.getContext().unbindService(ServiceManager.getInstance().getConn());
                getActivity().finish();
                break;
        }
    }

    /**
     * 关闭DrawerLayout
     *
     * @param tag
     */
    public void closeNavigationAndSetTag(int tag) {
        drawerLayout.closeDrawer(Gravity.START);
        drawerLayout.setTag(R.id.drawerLayout, tag);

    }

    @Override
    public void onItemClick(View itemView, int position) {
        closeNavigationAndSetTag(position);
    }
}
