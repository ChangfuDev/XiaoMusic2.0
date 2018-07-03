package com.yzx.xiaomusic.ui.play.lyric;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.cache.CacheUtils;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.play.PlayFragment;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.widget.lyric.LrcHelper;
import com.yzx.xiaomusic.widget.lyric.LrcView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class LyricFragment extends BaseMvpFragment<LyricPresenter> {
    private static final String TAG = "yglLyricFragment";
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.lrcView)
    LrcView lrcView;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    private AudioManager am;
    private MyVolumeReceiver myVolumeReceiver;


    @Override
    protected LyricPresenter getPresenter() {
        return new LyricPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_lyric;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBar.setMax(maxVolume);
        seekBar.setSecondaryProgress(maxVolume);
    }

    @Override
    public void onResume() {
        super.onResume();
        int volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(volume);

    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        loadLyric();
    }

    @OnClick(R.id.lrcView)
    public void onViewClicked() {
        PlayFragment playFragment = (PlayFragment) getParentFragment();
        playFragment.showHideFragment(playFragment.playCardFragment, this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        EventBusUtils.register(this);
    }

    private void loadLyric() {
        service = ServiceManager.getInstance().getService();
        MusicInfo musicInfo = service.getMusicInfo();
        if (musicInfo != null) {
            String musicId = musicInfo.getMusicId();
            String cacheLyric = CacheUtils.getCacheLyric(musicId);
            if (TextUtils.isEmpty(cacheLyric)) {
                mPresenter.getLrc(musicId);
            } else {
                lrcView.setLrcData(LrcHelper.parseLrcFromFile(new File(cacheLyric)));
//                lrcView.updateTime(service.getCurrentPosition());
            }
        }
    }

    /**
     * 注册当音量发生变化时接收的广播
     */
    private void registerReceiver() {
        myVolumeReceiver = new MyVolumeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        getContext().registerReceiver(myVolumeReceiver, filter);
    }

    /**
     * 处理音量变化时的界面显示
     *
     * @author long
     */
    private class MyVolumeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果音量发生变化则更改seekbar的位置
            if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
                // 当前的媒体音量
                int currVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                if (seekBar != null) {
                    seekBar.setProgress(currVolume);
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        registerReceiver();
    }

    @Override
    public void onStop() {
        super.onStop();
        getContext().unregisterReceiver(myVolumeReceiver);
    }

    @Override
    public void onDestroyView() {
        EventBusUtils.unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case TYPE_MUSIC_UPDATE_PROGRESS:
                Integer content = (Integer) event.getContent();
                lrcView.updateTime(content);
                break;
            case TYPE_MUSIC_CHANGED:
                lrcView.setLrcData(null);
                loadLyric();
                break;
        }
    }
}
