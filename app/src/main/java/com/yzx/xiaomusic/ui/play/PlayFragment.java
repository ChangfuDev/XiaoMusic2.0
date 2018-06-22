package com.yzx.xiaomusic.ui.play;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.play.card.PlayCardFragment;
import com.yzx.xiaomusic.ui.play.lyric.LyricFragment;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_BUFFER;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;

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
    AppCompatSeekBar seekBar;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_play_pause)
    ImageView ivPlayPause;
    Unbinder unbinder;
    private MusicInfo musicInfo;
    private MusicService service;
    public PlayCardFragment playCardFragment;
    public LyricFragment lyricFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
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
        musicInfo = service.getMusicInfo();
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        playCardFragment = new PlayCardFragment();
        lyricFragment = new LyricFragment();
        loadMultipleRootFragment(R.id.fragmentContainer, 0, playCardFragment, lyricFragment);
        initToolBar(tb);
        tb.inflateMenu(R.menu.menu_share);
        tb.setOnMenuItemClickListener(this);

        tvSubTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(musicInfo.getMusicName());
        tvSubTitle.setText(MusicDataUtils.getSingers(musicInfo));
        if (!musicInfo.isLocal()) {
            GlideUtils.loadBlurImg(getContext(), musicInfo.getAlbumCoverPath(), ivBg);
        }
        tvCurrentProgress.setText("00:00");
        tvDuration.setText(TimeUtils.getFormatData(musicInfo.getDuration(), TimeUtils.FORMAT_MM_SS));
    }

    @OnClick({R.id.tv_subTitle, R.id.tb, R.id.iv_play_mode, R.id.iv_previous, R.id.iv_play_pause, R.id.iv_next, R.id.iv_song_sheet})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_subTitle:
//                SingerDetailsFragment singerDetailsFragment = new SingerDetailsFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString();
//                singerDetailsFragment.setArguments(bundle);
//                start(singerDetailsFragment,SINGLETASK);
                break;
            case R.id.tb:
                break;
            case R.id.iv_play_mode:
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
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

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
                seekBar.setMax((int) musicInfo.getDuration());
                seekBar.setSecondaryProgress((int) (musicInfo.getDuration() * ((int) event.getContent()) / 100));
                break;
            case TYPE_MUSIC_PLAYING:
                ivPlayPause.setImageResource(R.drawable.acq);
                break;
            case TYPE_MUSIC_PAUSE:
                ivPlayPause.setImageResource(R.drawable.acs);
                break;
            case TYPE_MUSIC_UPDATE_PROGRESS:
                Integer content = (Integer) event.getContent();
                seekBar.setMax((int) musicInfo.getDuration());
//                Log.i(TAG, "onMessageEvent: " + seekBar.getMax() + content);
                seekBar.setProgress(content);
                tvCurrentProgress.setText(TimeUtils.getFormatData(content, TimeUtils.FORMAT_MM_SS));
                break;

        }
    }
}
