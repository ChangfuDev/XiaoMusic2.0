package com.yzx.xiaomusic.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.network.AppHttpClient;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.cache.CacheUtils;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.model.entity.MusicAddress;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.MusicApi;
import com.yzx.xiaomusic.ui.notification.PlayNotification;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.JsonUtils;
import com.yzx.xiaomusic.utils.SPUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.yzx.xiaomusic.Constant.BASE_URL;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;
import static com.yzx.xiaomusic.network.ApiConstant.BR_320;

/**
 * @author yzx
 * @date 2018/6/19
 * Description 音乐服务
 */

public class MusicService extends Service implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnErrorListener {
    public static final String TAG = "ygl" + MusicService.class.getSimpleName();

    /**
     * 焦点状态改变监听
     */
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = focusChange -> {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                pause();
                break;
        }
    };


    public static final int PLAY_MODE_LOOP = 1;
    public static final int PLAY_MODE_SINGLE = 2;
    public static final int PLAY_MODE_RANDOM = 3;
    private int playMode = PLAY_MODE_LOOP;

    public static final String KEY_SONG_SHEET = "SongSheetList";
    public static final String KEY_LAST_POSITION = "lastPosition";

    CompositeDisposable mDisposable = new CompositeDisposable();
    MusicInfo musicInfo;
    /**
     * 歌单
     */
    List<MusicInfo> songSheet;
    private MediaPlayer mediaPlayer;
    private int index;
    private boolean prepared;
    private Random random;
    private Disposable disposable;
    private int buffer;
    private int currentPosition;
    private Disposable d;
    private AudioManager mAudioManager;
    private MediaSessionManager mMediaSessionManager;


    @Override
    public void onCreate() {
        super.onCreate();


        setUpMediaPlayer();
        //显示上次播放的歌曲信息
        setUpLastPlayInfo();

        //用来随机播放
        random = new Random();
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        if (Build.VERSION.SDK_INT >= 21) {
            mMediaSessionManager = new MediaSessionManager(this);
        }
    }


    /**
     * 初始化mediaPlayer
     */
    private void setUpMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnErrorListener(this);

        //设置成播放音乐
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        mediaPlayer.setAudioAttributes(attributes);
    }

    /**
     * 初始化上次播放记录
     */
    private void setUpLastPlayInfo() {
        String stringSongSheet = SPUtils.getString(KEY_SONG_SHEET, null);
        if (!TextUtils.isEmpty(stringSongSheet)) {
            setSongSheet(JsonUtils.stringToList(stringSongSheet, MusicInfo.class));
            index = SPUtils.getInt(KEY_LAST_POSITION, 0);
            musicInfo = getSongSheet().get(index);
            EventBusUtils.postMusicChanged();
        }
    }

    public void setSongSheet(List<MusicInfo> songSheet) {
        this.songSheet = songSheet;
    }

    public List<MusicInfo> getSongSheet() {
        return songSheet;
    }

    public MusicInfo getMusicInfo() {
        return musicInfo;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    public int getPlayMode() {
        return playMode;
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void setMusicIndex(int position) {
        this.index = position;
        musicInfo = songSheet.get(position);
        EventBusUtils.postMusicChanged();
    }

    public int getIndex() {
        return index;
    }

    public int getBuffer() {
        return buffer;
    }

    public void seekTo(int progress) {
        mediaPlayer.seekTo(progress);
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
        if (musicInfo != null && musicInfo.getDuration() > 0) {
            if (percent - (mp.getCurrentPosition() * 100 / musicInfo.getDuration()) < 3) {
                sendPauseEvent();
                EventBusUtils.postMusicLoading(mp.getCurrentPosition());
            } else {
                if (!mp.isPlaying()) {
                    start();
                }
            }
        }

        buffer = percent;
        EventBusUtils.postBuffer(percent);
    }

    @SuppressLint("CheckResult")
    private void sendPlayingEvent() {
        showPlayNotification();
        EventBusUtils.post(new MessageEvent(TYPE_MUSIC_PLAYING));
        Observable
                .interval(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {

                        currentPosition = mediaPlayer.getCurrentPosition();
                        EventBusUtils.post(new MessageEvent(TYPE_MUSIC_UPDATE_PROGRESS, currentPosition));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void sendPauseEvent() {
        EventBusUtils.post(new MessageEvent(TYPE_MUSIC_PAUSE));
        showPlayNotification();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    private void showPlayNotification() {
        if (musicInfo != null) {
            Glide.with(getApplicationContext())
                    .asBitmap()
                    .load(musicInfo.getAlbumCoverPath())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            PlayNotification.showNotification(getApplicationContext(), musicInfo, resource);
                        }
                    });
        }
    }

    /**
     * 准备好监听
     *
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        prepared = true;
        DBUtils.listenMusic(musicInfo);
        //如果歌单不一样，保存到本地
        if (songSheet != null && !TextUtils.equals(JsonUtils.objectToString(songSheet), SPUtils.getString(KEY_SONG_SHEET, null))) {
            Log.i(TAG, "onPrepared: 歌单不一致SP更新歌单");
            SPUtils.putString(KEY_SONG_SHEET, JsonUtils.objectToString(songSheet));
        }
        SPUtils.putInt(KEY_LAST_POSITION, index);
        mp.start();
    }

    /**
     * 播放完成监听
     *
     * @param mp
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        //重置播放缓存
        buffer = 0;
        sendPauseEvent();
        next();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.i(TAG, "onError: " + what);
        Log.i(TAG, "onError: " + extra);
        return true;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
    }

    public void playPause() {
        if (mediaPlayer.isPlaying()) {
            pause();
        } else if (prepared) {
            start();
        } else {
            realPlay();
        }
    }

    public void start() {
        mediaPlayer.start();
        if (Build.VERSION.SDK_INT >= 21) {
            mMediaSessionManager.updatePlaybackState();
        }
        mAudioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        sendPlayingEvent();
    }

    public void pause() {
        mediaPlayer.pause();
        if (Build.VERSION.SDK_INT >= 21) {
            mMediaSessionManager.updatePlaybackState();
        }
        mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        sendPauseEvent();
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
                    index = random.nextInt(songSheet.size());
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
                    index = random.nextInt(songSheet.size());
                    setMusicIndex(index);
                    break;
            }
        }
        realPlay();
    }


    /**
     * 真正播放逻辑
     */
    public void realPlay() {
        reset();
        MusicInfo musicInfo = getMusicInfo();
        if (mediaPlayer == null || songSheet == null || songSheet.size() <= 0 || musicInfo == null) {
            ToastUtils.showToast("没有要播放的歌单或歌曲");
            return;
        }
        //播放的时候获取焦点
        mAudioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (musicInfo.isLocal()) {
            playMusic(musicInfo);
        } else {
            playNetMusic(musicInfo);
        }
    }

    /**
     * 如果正在获取网络歌曲地址的时候切歌，取消请求
     * 重置mediaPlayer
     */
    private void reset() {
        if (d != null) {
            d.dispose();
        }
        mediaPlayer.reset();
        prepared = false;
    }

    /**
     * 播放网络音乐
     * 判断是否有缓存
     * 有，利用缓存播放
     * 无，获取地址、缓存、播放
     *
     * @param musicInfo
     */
    private void playNetMusic(MusicInfo musicInfo) {

        String cacheMusic = CacheUtils.getCacheMusic(musicInfo.getMusicId());
        if (!TextUtils.isEmpty(cacheMusic)) {
            musicInfo.setPath(cacheMusic);
            playMusic(musicInfo);
            EventBusUtils.postBuffer(100);
            Log.i(TAG, "playNetMusic: cache");
        } else {
            Log.i(TAG, "playNetMusic: noCache");
            EventBusUtils.postBuffer(0);
            getNetMusicAddressAndPlay(musicInfo);
        }
    }

    private void getNetMusicAddressAndPlay(MusicInfo musicInfo) {

        sendPauseEvent();
        EventBusUtils.postMusicLoading(0);

        getMusicAddress(musicInfo.getMusicId(), new CommonMvpObserver<MusicAddress>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                MusicService.this.d = d;
            }

            @Override
            protected void onSuccess(MusicAddress musicAddress) {
                Log.i(TAG, "onSuccess: 获取地址成功" + musicInfo.getMusicName());
                String url = musicAddress.getData().get(0).getUrl();
                if (!TextUtils.isEmpty(url)) {
                    musicInfo.setPath(url);
                    playMusic(musicInfo);
                    CacheUtils.cacheMusic(musicInfo.getMusicId(), url);
                } else {
                    ToastUtils.showToast("暂时无法获取" + musicInfo.getMusicName() + "自动跳过", ToastUtils.TYPE_NOTICE);
                    Log.e(TAG, "onSuccess: url为空" + musicInfo.getMusicName());
                    next();
                }
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                next();
                ToastUtils.showToast("网络异常，播放失败");
                Log.e(TAG, "onFail: " + code + errorMsg);
            }
        });
    }

    /**
     * 播放音乐
     *
     * @param musicInfo
     */
    private void playMusic(MusicInfo musicInfo) {
        try {
            mediaPlayer.setDataSource(musicInfo.getPath());
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            ToastUtils.showToast("播放失败");
            Log.e(TAG, "onFail: " + e.toString());
        }
    }

    public void getMusicAddress(String id, CommonMvpObserver<MusicAddress> observer) {
        new AppHttpClient.Builder()
                .context(getApplicationContext())
                .baseUrl(BASE_URL)
                .connectTimeout(90)
                .build()
                .getService(MusicApi.class)
                .getMusicAddress(ApiConstant.TYPE_SONG, id, BR_320)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        mediaPlayer.release();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
