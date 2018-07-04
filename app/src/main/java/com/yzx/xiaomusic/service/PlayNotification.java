package com.yzx.xiaomusic.service;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.receiver.RemoteReceiver;
import com.yzx.xiaomusic.ui.main.MainActivity;

import static com.yzx.xiaomusic.receiver.RemoteReceiver.ACTION;
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

    public static void showNotification(Context context, String name, String artist, Bitmap bitmap) {
        systemService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);
//        intent.putExtra(Extras.EXTRA_NOTIFICATION, true);
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteView = getRemoteView(context, name, artist, bitmap);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                //设置跳转页面
                .setContentIntent(pendingIntent)
//                .setCustomBigContentView(getRemoteView(context, name, artist, bitmap))
//                .setContentTitle(name)
//                .setContentText(artist)
//                .setLargeIcon(bitmap)
//                .setContent(remoteView)
//                .setCustomHeadsUpContentView(remoteView)
                //vivo如果只设置该方法会显示小窗口，需要适配
                .setCustomContentView(remoteView)
//                魅族如果只设置大的会不显示通知的内容
                .setCustomBigContentView(remoteView)
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
//                .setStyle(new NotificationCompat.MediaStyle().setShowActionsInCompactView(0, 1, 2, 3))
                .build();

        assert systemService != null;
        systemService.notify(1, builder.build());
    }

    public static void dismiss() {
        systemService.cancelAll();
    }

    private static RemoteViews getRemoteView(final Context context, String name, String artist, final Bitmap bitmap) {

        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_remote_play);

        remoteViews.setTextViewText(R.id.tv_name, name);
        remoteViews.setTextViewText(R.id.tv_artist, artist);
        remoteViews.setImageViewBitmap(R.id.iv_music_poster, bitmap);

        PendingIntent previousIntent = getPreviousPendingIntent(context);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_previous, previousIntent);

        PendingIntent playIntent = getPlayPausePendingIntent(context);
        remoteViews.setImageViewResource(R.id.iv_play_pause,
                isPlaying() ? R.drawable.note_btn_pause_white : R.drawable.note_btn_play_white);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_pause, playIntent);

        PendingIntent nextIntent = getNextPendingIntent(context);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_next, nextIntent);

        PendingIntent deleteIntent = getDeletePendingIntent(context);
        remoteViews.setOnClickPendingIntent(R.id.iv_delete, deleteIntent);

        return remoteViews;
    }

    private static PendingIntent getDeletePendingIntent(Context context) {
        Intent delete = new Intent(ACTION);
        delete.putExtra(RemoteReceiver.KEY, RemoteReceiver.VALUE_DELETE);
        return PendingIntent.getBroadcast(context, 3, delete, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static PendingIntent getNextPendingIntent(Context context) {
        Intent next = new Intent(ACTION);
        next.putExtra(RemoteReceiver.KEY, RemoteReceiver.VALUE_NEXT);
        return PendingIntent.getBroadcast(context, 2, next, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static PendingIntent getPlayPausePendingIntent(Context context) {
        boolean playing = isPlaying();
        Intent play = new Intent(ACTION);
        play.putExtra(RemoteReceiver.KEY, playing ? RemoteReceiver.VALUE_PAUSE : RemoteReceiver.VALUE_PLAY);

        return PendingIntent.getBroadcast(context, 1, play, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static boolean isPlaying() {
        return getInstance().getService().isPlaying();
    }

    private static PendingIntent getPreviousPendingIntent(Context context) {
        Intent previous = new Intent(ACTION);
        previous.putExtra(RemoteReceiver.KEY, RemoteReceiver.VALUE_PREVIOUS);
        return PendingIntent.getBroadcast(context, 0, previous, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
