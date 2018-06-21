package com.yzx.xiaomusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.app.MusicApplication;
import com.yzx.xiaomusic.model.entity.MusicAddress;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.MusicApi;

import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.yzx.xiaomusic.network.ApiConstant.BR_320;

/**
 * @author yzx
 * @date 2018/6/19
 * Description 音乐服务
 */

public class MusicService extends Service implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener {
    public static final String TAG = "ygl" + MusicService.class.getSimpleName();
    public static final int PLAY_MODE_LOOP = 1;
    public static final int PLAY_MODE_SINGLE = 2;
    public static final int PLAY_MODE_RANDOM = 3;
    private int playMode = PLAY_MODE_LOOP;
    MusicInfo musicInfo;
    /**
     * 歌单
     */
    List<MusicInfo> songSheet;
    private MediaPlayer mediaPlayer;
    private int index;
    private boolean prepared;
    private Random random;


    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        random = new Random();
        Log.d(TAG, "onCreate: ");
    }

    public void setSongSheet(List<MusicInfo> songSheet) {
        this.songSheet = songSheet;
    }

    public MusicInfo getMusicInfo() {
        return musicInfo;
    }

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    /**
     * 进度更新
     *
     * @param mp
     * @param percent
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
//        缓存不足时，先暂停
        if (percent - (mp.getCurrentPosition() * 100 / musicInfo.getDuration()) < 1) {
            mp.pause();
        } else {
            if (!mp.isPlaying()) {
                mp.start();
            }
        }
        Log.i(TAG, musicInfo.getMusicName() + "onBufferingUpdate: " + percent + "---" + (percent - (mp.getCurrentPosition() * 100 / musicInfo.getDuration())));
    }

    /**
     * 准备好监听
     *
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        prepared = true;
        if (!mediaPlayer.isPlaying()) {
            mp.start();
        }
    }

    /**
     * 播放完成监听
     *
     * @param mp
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i(TAG, musicInfo.getMusicName() + "onCompletion: 播放完成");
        index++;
        setMusicIndex(index);
        next();
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    public void playPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else if (prepared) {
            mediaPlayer.start();
        } else {
            realPlay();
        }
    }

    public void next() {
        if (songSheet.size() == 1) {
            setMusicIndex(index);
        } else if (songSheet.size() > 1) {
            switch (playMode) {
                case PLAY_MODE_LOOP:
                case PLAY_MODE_SINGLE:
                    //最后一首
                    if (index == songSheet.size() - 1) {
                        setMusicIndex(0);
                    } else {
                        index++;
                        setMusicIndex(index);
                    }
                    break;
                case PLAY_MODE_RANDOM:
                    random.setSeed(songSheet.size());
                    index = random.nextInt();
                    setMusicIndex(index);
                    break;
            }
        }
        realPlay();
    }

    public void previous() {
        if (songSheet.size() == 1) {
            setMusicIndex(index);
        } else if (songSheet.size() > 1) {
            switch (playMode) {
                case PLAY_MODE_LOOP:
                case PLAY_MODE_SINGLE:
                    //第一首
                    if (index == 0) {
                        setMusicIndex(songSheet.size() - 1);
                    } else {
                        index--;
                        setMusicIndex(index);
                    }
                    break;
                case PLAY_MODE_RANDOM:
                    random.setSeed(songSheet.size());
                    index = random.nextInt();
                    setMusicIndex(index);
                    break;
            }
        }
        realPlay();
    }


    public void realPlay() {
        mediaPlayer.reset();
        prepared = false;
        MusicInfo musicInfo = getMusicInfo();
        if (mediaPlayer == null || songSheet == null || songSheet.size() <= 0 || musicInfo == null) {
            ToastUtils.showToast("没有要播放的歌单或歌曲");
            return;
        }
        if (musicInfo.isLocal()) {
            playLocalMusic(musicInfo);
        } else {
            playNetMusic(musicInfo);
        }
    }

    private void playNetMusic(MusicInfo musicInfo) {
        getMusicAddress(musicInfo.getMusicId(), new CommonMvpObserver<MusicAddress>() {
            @Override
            protected void onSuccess(MusicAddress musicAddress) {
                Log.d(TAG, "onSuccess: 获取地址成功" + musicInfo.getMusicName());
                String url = musicAddress.getData().get(0).getUrl();
                if (url != null) {
                    try {
                        mediaPlayer.setDataSource(getBaseContext(), Uri.parse(url));
                        mediaPlayer.prepareAsync();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, "onSuccess: " + e.toString());
                    }
                } else {
                    ToastUtils.showToast("暂时无法获取" + musicInfo.getMusicName() + "自动跳过", ToastUtils.TYPE_NOTICE);
                    Log.e(TAG, "onSuccess: url为空" + musicInfo.getMusicName());
                    next();
                }
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                ToastUtils.showToast("播放失败");
                Log.e(TAG, "onFail: " + code + errorMsg);
            }
        });
    }

    /**
     * 播放本地音乐
     *
     * @param musicInfo
     */
    private void playLocalMusic(MusicInfo musicInfo) {
        try {
            mediaPlayer.setDataSource(musicInfo.getPath());
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            ToastUtils.showToast("播放失败");
            Log.e(TAG, "onFail: " + e.toString());
        }

    }

    public void getMusicAddress(String id, CommonMvpObserver<MusicAddress> observer) {
        MusicApplication
                .getAppHttpClient()
                .getService(MusicApi.class)
                .getMusicAddress(ApiConstant.TYPE_SONG, id, BR_320)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    public void setMusicIndex(int position) {
        this.index = position;
        musicInfo = songSheet.get(position);
        Log.i(TAG, "setMusicIndex: " + musicInfo.getMusicName() + position);
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
