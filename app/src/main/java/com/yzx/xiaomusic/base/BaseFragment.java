package com.yzx.xiaomusic.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.yzx.commonlibrary.base.CommonBaseFragment;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.dialog.BottomSongSheetDialog;
import com.yzx.xiaomusic.ui.play.PlayFragment;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.widget.CircleProgress;
import com.yzx.xiaomusic.widget.loadsir.ErrorCallback;
import com.yzx.xiaomusic.widget.loadsir.LoadingCallback;

import java.lang.reflect.Method;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.widget.CircleProgress.STATE_PAUSE;
import static com.yzx.xiaomusic.widget.CircleProgress.STATE_PLAY;

/**
 * @author yzx
 */
public abstract class BaseFragment extends CommonBaseFragment {

    public LoadService loadService;
    public MusicService service;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        service = ServiceManager.getInstance().getService();
        View view = super.onCreateView(inflater, container, savedInstanceState);
        loadService = LoadSir.getDefault().register(view, (Callback.OnReloadListener) v -> reload(v));
        loadService.showSuccess();

        return loadService.getLoadLayout();
    }

    public void initToolBar(Toolbar toolbar) {
        toolbar.setTitle(null);
        setMenuIconVisible(toolbar);
        toolbar.setNavigationOnClickListener(v -> pop());
    }

    private void setMenuIconVisible(Toolbar toolbar) {
        Menu menu = toolbar.getMenu();
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    @SuppressLint("PrivateApi")
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 初始化底部音乐控件
     *
     * @param musicController
     */
    public void initBottomMusicController(LinearLayout musicController) {
        Log.i("yglmusicController", "initBottomMusicController: " + this.getClass().getSimpleName() + service);
        if (service != null) {
            MusicInfo musicInfo = service.getMusicInfo();
            ImageView musicCover = (ImageView) musicController.findViewById(R.id.iv_music_cover);
            TextView musicName = (TextView) musicController.findViewById(R.id.tv_music_name);
            TextView musicSinger = (TextView) musicController.findViewById(R.id.tv_music_singer);
            CircleProgress playPause = (CircleProgress) musicController.findViewById(R.id.iv_play_pause);
            if (musicInfo != null) {
                musicName.setText(musicInfo.getMusicName());
                musicSinger.setText(MusicDataUtils.getSingers(musicInfo));
                if (!musicInfo.isLocal()) {
                    GlideUtils.loadImg(getContext(), musicInfo.getAlbumCoverPath(), musicCover);
                }
                playPause.setState(service.isPlaying() ? STATE_PLAY : STATE_PAUSE);
                playPause.setMax((int) musicInfo.getDuration());
            }
        }
        musicController.findViewById(R.id.iv_play_pause).setOnClickListener(v -> {
            ServiceManager.getInstance().getService().playPause();
        });
        musicController.findViewById(R.id.iv_song_sheet).setOnClickListener(v -> {
            BottomSongSheetDialog songSheetDialog = new BottomSongSheetDialog();
            songSheetDialog.show(getChildFragmentManager(), "songSheet");
        });
        musicController.setOnClickListener(v -> start(new PlayFragment(), SINGLETASK));
    }

    /**
     * 普通开启
     *
     * @param fragment
     */
    public void easyStart(SupportFragment fragment) {
        easyStart(fragment, null);
    }

    public void easyParentStart(SupportFragment fragment) {
        easyParentStart(fragment, null);
    }

    /**
     * 父Fragment开启，viewPager情况下
     *
     * @param fragment
     * @param args
     */
    public void easyParentStart(SupportFragment fragment, Bundle args) {
        if (args != null) {
            fragment.setArguments(args);
        }
        SupportFragment parentFragment = (SupportFragment) this.getParentFragment();
        parentFragment.start(fragment);
    }

    public void easyStart(SupportFragment fragment, Bundle args) {
        if (args != null) {
            fragment.setArguments(args);
        }
        start(fragment);
    }

    /**
     * 单例开启
     */
    public void singleStart(Class<SupportFragment> cls, SupportFragment fragment, Bundle args) {
        SupportFragment supportFragment = findFragment(cls);
        if (supportFragment == null) {
            easyStart(fragment, args);
        } else {
            fragment.putNewBundle(args);
            start(fragment, SINGLETASK);
        }
    }

    /**
     * 父Fragment单例开启
     */
    public void singParentleStart(Class<SupportFragment> cls, SupportFragment fragment, Bundle args) {
        SupportFragment supportFragment = findFragment(cls);
        if (supportFragment == null) {
            easyParentStart(fragment, args);
        } else {
            if (args != null) {
                fragment.setArguments(args);
            }
            SupportFragment parentFragment = (SupportFragment) this.getParentFragment();
            fragment.putNewBundle(args);
            parentFragment.start(fragment, SINGLETASK);
        }
    }


    /**
     * 重试
     *
     * @param v
     */
    public void reload(View v) {

    }

    public void showErrorLayout() {
        if (loadService != null) {
            loadService.showCallback(ErrorCallback.class);
        }
    }

    public void showLoadingLayout() {
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    public void showSuccessLayout() {
        if (loadService != null) {
            loadService.showSuccess();
        }
    }

    /**
     * 播放音乐并开启播放页面
     *
     * @param songSheet
     * @param position
     */
    public void playMusicWithStartFragment(List<MusicInfo> songSheet, int position) {
        MusicService service = ServiceManager.getInstance().getService();
        service.setSongSheet(songSheet);

        //同一首歌
        if (songSheet.get(position) != service.getMusicInfo()) {
            service.setMusicIndex(position);
            service.realPlay();
        } else {
            service.playPause();
            start(new PlayFragment(), SINGLETASK);
        }
    }

}
