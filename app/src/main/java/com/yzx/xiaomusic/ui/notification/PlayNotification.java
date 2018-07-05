package com.yzx.xiaomusic.ui.notification;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.receiver.RemoteReceiver;
import com.yzx.xiaomusic.ui.main.MainActivity;
import com.yzx.xiaomusic.utils.MusicDataUtils;

import static com.yzx.xiaomusic.receiver.RemoteReceiver.ACTION;
import static com.yzx.xiaomusic.receiver.RemoteReceiver.VALUE_LIKE;
import static com.yzx.xiaomusic.receiver.RemoteReceiver.VALUE_LYRIC;
import static com.yzx.xiaomusic.receiver.RemoteReceiver.VALUE_NEXT;
import static com.yzx.xiaomusic.receiver.RemoteReceiver.VALUE_PREVIOUS;
import static com.yzx.xiaomusic.service.ServiceManager.getInstance;


/**
 * @author yzx
 * @date 2018/3/4
 * Description  播放通知
 */

public class PlayNotification {

    private static final String TAG = "yglPlayNotification";
    private @SuppressLint("ServiceCast")
    static NotificationManager systemService;

    public static void showNotification(Context context, MusicInfo musicInfo, Bitmap bitmap) {
        systemService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);
//        intent.putExtra(Extras.EXTRA_NOTIFICATION, true);
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews customRemoteView = getCustomRemoteView(context, musicInfo, bitmap);
        RemoteViews bigRemoteView = getBigRemoteView(context, musicInfo, bitmap);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                //设置跳转页面
                .setContentIntent(pendingIntent)
//                .setCustomBigContentView(getBigRemoteView(context, name, artist, bitmap))
//                .setContentTitle(name)
//                .setContentText(artist)
//                .setLargeIcon(bitmap)
//                .setContent(bigRemoteView)
//                .setCustomHeadsUpContentView(bigRemoteView)
                //vivo如果只设置该方法会显示小窗口，需要适配
                .setCustomContentView(customRemoteView)
//                魅族如果只设置大的会不显示通知的内容
                .setCustomBigContentView(bigRemoteView)
//                .addAction(R.drawable.acu, "", getPreviousPendingIntent(context))
//                .addAction(isPlaying() ? R.drawable.acq : R.drawable.acs, "", getPlayPausePendingIntent(context))
//                .addAction(R.drawable.aco, "", getNextPendingIntent(context))
//                .addAction(R.drawable.ou, "", getDeletePendingIntent(context))
                //设置优先级优先级越大越靠前
                .setPriority(NotificationCompat.PRIORITY_MAX)
                //禁止侧滑删除
                .setOngoing(true)
                //设置点击取消？可点击取消：不可点击取消
                .setAutoCancel(false)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .build();

        assert systemService != null;
        systemService.notify(1, builder.build());
    }

    public static void dismiss() {
        systemService.cancelAll();
    }


    private static RemoteViews getCustomRemoteView(final Context context, MusicInfo musicInfo, Bitmap bitmap) {

        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_remote_play_custom);

        remoteViews.setTextViewText(R.id.tv_name, musicInfo.getMusicName());
        remoteViews.setTextViewText(R.id.tv_artist, MusicDataUtils.getSingers(musicInfo) + " - " + musicInfo.getAlbumName());
        remoteViews.setImageViewBitmap(R.id.iv_music_poster, bitmap);

        remoteViews.setImageViewResource(R.id.iv_like,
                DBUtils.isLikeMusicExit(musicInfo) != null ? R.drawable.note_btn_loved : R.drawable.note_btn_love_white);
        setPendingIntent(remoteViews, R.id.iv_like, context, 0, VALUE_LIKE);
        setPendingIntent(remoteViews, R.id.iv_play_previous, context, 1, VALUE_PREVIOUS);

        remoteViews.setImageViewResource(R.id.iv_play_pause,
                isPlaying() ? R.drawable.note_btn_pause_white : R.drawable.note_btn_play_white);
        PendingIntent playIntent = getPlayPausePendingIntent(context);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_pause, playIntent);

        setPendingIntent(remoteViews, R.id.iv_next, context, 3, VALUE_NEXT);
        setPendingIntent(remoteViews, R.id.iv_delete, context, 4, VALUE_NEXT);
        setPendingIntent(remoteViews, R.id.iv_lyric, context, 5, VALUE_LYRIC);


        return remoteViews;
    }

    private static RemoteViews getBigRemoteView(final Context context, MusicInfo musicInfo, Bitmap bitmap) {

        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_remote_play_big);

        remoteViews.setTextViewText(R.id.tv_name, musicInfo.getMusicName());
        remoteViews.setTextViewText(R.id.tv_artist, MusicDataUtils.getSingers(musicInfo) + " - " + musicInfo.getAlbumName());
        remoteViews.setImageViewBitmap(R.id.iv_music_poster, bitmap);

        remoteViews.setImageViewResource(R.id.iv_like,
                DBUtils.isLikeMusicExit(musicInfo) != null ? R.drawable.note_btn_loved : R.drawable.note_btn_love_white);
        setPendingIntent(remoteViews, R.id.iv_like, context, 0, VALUE_LIKE);
        setPendingIntent(remoteViews, R.id.iv_play_previous, context, 1, VALUE_PREVIOUS);

        remoteViews.setImageViewResource(R.id.iv_play_pause,
                isPlaying() ? R.drawable.note_btn_pause_white : R.drawable.note_btn_play_white);
        PendingIntent playIntent = getPlayPausePendingIntent(context);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_pause, playIntent);

        setPendingIntent(remoteViews, R.id.iv_next, context, 3, VALUE_NEXT);
        setPendingIntent(remoteViews, R.id.iv_delete, context, 4, VALUE_NEXT);
        setPendingIntent(remoteViews, R.id.iv_lyric, context, 5, VALUE_LYRIC);


        return remoteViews;
    }

    private static PendingIntent getPlayPausePendingIntent(Context context) {
        boolean playing = isPlaying();
        Intent play = new Intent(ACTION);
        play.putExtra(RemoteReceiver.KEY, playing ? RemoteReceiver.VALUE_PAUSE : RemoteReceiver.VALUE_PLAY);

        return PendingIntent.getBroadcast(context, 2, play, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static boolean isPlaying() {
        return getInstance().getService().isPlaying();
    }

    private static void setPendingIntent(RemoteViews remoteView, int id, Context context, int requestCode, String extraValue) {
        Intent intent = new Intent(ACTION);
        intent.putExtra(RemoteReceiver.KEY, extraValue);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(id, pendingIntent);
    }

}
