package com.yzx.xiaomusic.ui.play;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.cache.CacheUtils;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.dialog.BottomSongSheetDialog;
import com.yzx.xiaomusic.ui.play.card.PlayCardFragment;
import com.yzx.xiaomusic.ui.play.lyric.LyricFragment;
import com.yzx.xiaomusic.ui.singer.SingerDetailsFragment;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.FragmentStartUtils;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.utils.TimeUtils;
import com.yzx.xiaomusic.widget.MusicSeekBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_LOADING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_BUFFER;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;
import static com.yzx.xiaomusic.service.MusicService.PLAY_MODE_LOOP;
import static com.yzx.xiaomusic.service.MusicService.PLAY_MODE_RANDOM;
import static com.yzx.xiaomusic.service.MusicService.PLAY_MODE_SINGLE;
import static com.yzx.xiaomusic.ui.singer.SingerDetailsFragment.KEY_ID_SINGER;

/**
 * @author yzx
 * @date 2018/6/20
 * Description  播放页面
 */
public class PlayFragment extends BaseMvpFragment<PlayPresenter> implements Toolbar.OnMenuItemClickListener {
    public static final String TAG = "ygl" + PlayFragment.class.getSimpleName();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    @BindView(R.id.tv_current_progress)
    TextView tvCurrentProgress;
    @BindView(R.id.seekBar)
    MusicSeekBar seekBar;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_play_pause)
    ImageView ivPlayPause;
    @BindView(R.id.iv_play_mode)
    ImageView ivPlayMode;
    private MusicInfo musicInfo;
    private MusicService service;
    public PlayCardFragment playCardFragment;
    public LyricFragment lyricFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBusUtils.register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected PlayPresenter getPresenter() {
        return new PlayPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        service = ServiceManager.getInstance().getService();
        if (service == null) {
            return;
        }
        musicInfo = service.getMusicInfo();
    }

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
        Log.i(TAG, "onNewBundle: ");
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        initToolBar(tb);
        tb.inflateMenu(R.menu.menu_share);
        tb.setOnMenuItemClickListener(this);
//        seekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (fromUser) {
//                    service.seekTo(progress);
//                }
//            }
//        });
        playCardFragment = new PlayCardFragment();
        lyricFragment = new LyricFragment();
        loadMultipleRootFragment(R.id.fragmentContainer, 0, playCardFragment, lyricFragment);
        if (musicInfo != null) {
            tvSubTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(musicInfo.getMusicName());
            tvSubTitle.setText(MusicDataUtils.getSingers(musicInfo));

            if (!musicInfo.isLocal()) {
                GlideUtils.loadBlurImg(getContext(), musicInfo.getAlbumCoverPath(), ivBg);
            }
            tvCurrentProgress.setText("00:00");
            tvDuration.setText(TimeUtils.getFormatData(musicInfo.getDuration(), TimeUtils.FORMAT_MM_SS));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //更新缓存进度
        if (musicInfo != null) {
            seekBar.setMax((int) musicInfo.getDuration());
            if (musicInfo.isLocal() || CacheUtils.isMusicCache(musicInfo.getMusicId())) {
                seekBar.setSecondProgress((int) musicInfo.getDuration());
            } else {
                seekBar.setSecondProgress((int) (musicInfo.getDuration() * (service.getBuffer()) / 100));
            }
        }
        //更新播放状态
        ivPlayPause.setImageResource(service.isPlaying() ? R.drawable.acq : R.drawable.acs);
        switch (service.getPlayMode()) {
            case PLAY_MODE_LOOP:
                ivPlayMode.setImageResource(R.drawable.ady);
                break;
            case PLAY_MODE_SINGLE:
                ivPlayMode.setImageResource(R.drawable.ae6);
                break;
            case PLAY_MODE_RANDOM:
                ivPlayMode.setImageResource(R.drawable.aej);
                break;
        }
    }

    @OnClick({R.id.tv_subTitle, R.id.tb, R.id.iv_play_mode, R.id.iv_previous, R.id.iv_play_pause, R.id.iv_next, R.id.iv_song_sheet})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_subTitle:
                MusicInfo musicInfo = service.getMusicInfo();
                Bundle bundle = new Bundle();
                bundle.putString(KEY_ID_SINGER, musicInfo.getSingerInfos().get(0).getSingerId());
                FragmentStartUtils.startFragment(this, new SingerDetailsFragment(), bundle);
                break;
            case R.id.tb:
                break;
            case R.id.iv_play_mode:
                changePlayMode();
                break;
            case R.id.iv_previous:
                service.previous();
                break;
            case R.id.iv_play_pause:
                service.playPause();
                break;
            case R.id.iv_next:
                service.next();
                break;
            case R.id.iv_song_sheet:
                BottomSongSheetDialog songSheetDialog = new BottomSongSheetDialog();
                songSheetDialog.show(getChildFragmentManager(), "songSheetDialog");
                break;
        }
    }

    private void changePlayMode() {
        switch (service.getPlayMode()) {
            case PLAY_MODE_LOOP:
                service.setPlayMode(PLAY_MODE_SINGLE);
                ivPlayMode.setImageResource(R.drawable.ae6);
                showToast(R.string.singleLoop);
                break;
            case PLAY_MODE_SINGLE:
                service.setPlayMode(PLAY_MODE_RANDOM);
                ivPlayMode.setImageResource(R.drawable.aej);
                showToast(R.string.randomPlay);
                break;
            case PLAY_MODE_RANDOM:
                service.setPlayMode(PLAY_MODE_LOOP);
                ivPlayMode.setImageResource(R.drawable.ady);
                showToast(R.string.listLoop);
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                showToast(R.string.share);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyView() {
        EventBusUtils.unregister(this);
        super.onDestroyView();
    }

    @SuppressLint("CheckResult")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case TYPE_MUSIC_CHANGED:
                musicInfo = service.getMusicInfo();
                tvTitle.setText(musicInfo.getMusicName());
                tvSubTitle.setText(MusicDataUtils.getSingers(musicInfo));
                seekBar.setMax((int) musicInfo.getDuration());
                if (!musicInfo.isLocal()) {
                    GlideUtils.loadBlurImg(getContext(), musicInfo.getAlbumCoverPath(), ivBg);
                }
                break;
            case TYPE_MUSIC_UPDATE_BUFFER:
//                seekBar.setMax((int) musicInfo.getDuration());
                seekBar.setSecondProgress((int) (musicInfo.getDuration() * ((int) event.getContent()) / 100));
                break;
            case TYPE_MUSIC_PLAYING:
                ivPlayPause.setImageResource(R.drawable.acq);
                break;
            case TYPE_MUSIC_PAUSE:
                ivPlayPause.setImageResource(R.drawable.acs);
                break;
            case TYPE_MUSIC_UPDATE_PROGRESS:
                seekBar.setState(MusicSeekBar.STATE_PLAYING);
                Integer content = (Integer) event.getContent();
//                seekBar.setMax((int) musicInfo.getDuration());
                seekBar.setProgress(content);
                tvCurrentProgress.setText(TimeUtils.getFormatData(content, TimeUtils.FORMAT_MM_SS));
                break;
//            case TYPE_MUSIC_BUFFERRING:
//                //TODO 缓存不足时操作
//                seekBar.setState(MusicSeekBar.STATE_LOADING);
//                break;
            case TYPE_MUSIC_LOADING:
                //TODO 缓存不足时操作
                seekBar.setState(MusicSeekBar.STATE_LOADING);
                Log.i(TAG, "onMessageEvent: " + seekBar.getState());
                break;
        }
    }
}
