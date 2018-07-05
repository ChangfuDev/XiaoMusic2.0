package com.yzx.xiaomusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.db.entity.LikedMusicInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.notification.PlayNotification;

import static com.yzx.xiaomusic.ui.notification.PlayNotification.dismiss;


/**
 * @author yzx
 * @date 2018/3/5
 * Description
 */

public class RemoteReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.yzx.xiaomusic.remote";
    public static final String KEY = "receiverKey";
    public static final String VALUE_PREVIOUS = "previous";
    public static final String VALUE_PLAY = "play";
    public static final String VALUE_PAUSE = "pause";
    public static final String VALUE_NEXT = "next";
    public static final String VALUE_DELETE = "delete";
    public static final String VALUE_LIKE = "like";
    public static final String VALUE_LYRIC = "lyric";
    private static final String TAG = "yglRemoteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String stringExtra = intent.getStringExtra(KEY);
        Log.i(TAG, "onReceive: " + stringExtra);

        MusicService service = ServiceManager.getInstance().getService();
        if (service != null) {
            if (stringExtra != null) {
                switch (stringExtra) {
                    case VALUE_LIKE:
                        controlLikeMusic(context, service);
                        break;
                    case VALUE_PREVIOUS:
                        service.previous();
                        break;
                    case VALUE_PLAY:
                        service.playPause();
                        break;
                    case VALUE_PAUSE:
                        service.playPause();
                        break;
                    case VALUE_NEXT:
                        service.next();
                        break;
                    case VALUE_LYRIC:
                        ToastUtils.showToast(R.string.commingSoon, ToastUtils.TYPE_NOTICE);
                        break;
                    case VALUE_DELETE:
                        service.pause();
                        dismiss();
                        break;
                }
            }
        } else {
            ToastUtils.showToast(R.string.note_serviceIsNull, ToastUtils.TYPE_NOTICE);
        }
    }

    /**
     * 喜欢音乐操作
     *
     * @param context
     * @param service
     */
    private void controlLikeMusic(Context context, MusicService service) {
        MusicInfo musicInfo = service.getMusicInfo();
        if (musicInfo != null) {
            LikedMusicInfo likedMusicInfo = DBUtils.isLikeMusicExit(musicInfo);
            if (likedMusicInfo != null) {
                DBUtils.cancelLikeMusic(likedMusicInfo);
            } else {
                DBUtils.likeMusic(musicInfo);
            }
            Glide.with(context)
                    .asBitmap()
                    .load(musicInfo.getAlbumCoverPath())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            PlayNotification.showNotification(context, musicInfo, resource);
                        }
                    });
        } else {
            ToastUtils.showToast("无法获取当前音乐");
        }
    }
}
