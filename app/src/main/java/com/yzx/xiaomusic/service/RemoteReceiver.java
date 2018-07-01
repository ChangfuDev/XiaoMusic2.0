package com.yzx.xiaomusic.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.yzx.xiaomusic.utils.PlayNotification.*;


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
    private static final String TAG = "yglRemoteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String stringExtra = intent.getStringExtra(KEY);
        Log.i(TAG, "onReceive: " + stringExtra);
        MusicService service = ServiceManager.getInstance().getService();
        if (stringExtra != null) {
            switch (stringExtra) {
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
                case VALUE_DELETE:
                    service.playPause();
                    dismiss();
                    break;
            }
        }
    }
}
