package com.yzx.xiaomusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.KeyEvent;

public class MediaKeyReceiverextends extends BroadcastReceiver {
    private String Tag = "MediaKeyReceiver001";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Build.VERSION.SDK_INT >= 23) {
            return;
        }
        if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON)) {
            KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE: //播放暂停
//                    if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
//                        L.i(Tag, "KEYCODE_MEDIA_PLAY_PAUSE  ACTION_UP");
//                    } else if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//                        L.i(Tag, "KEYCODE_MEDIA_PLAY_PAUSE  ACTION_DOWN");
//                    }
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT://下一曲
//                    if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
//                        L.i(Tag, "KEYCODE_MEDIA_NEXT  ACTION_UP");
//                    } else if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//                        L.i(Tag, "KEYCODE_MEDIA_NEXT  ACTION_DOWN");
//                    }
                    break;
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS://上一曲
//                    if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
//                        L.i(Tag, "KEYCODE_MEDIA_PREVIOUS  ACTION_UP");
//                    } else if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//                        L.i(Tag, "KEYCODE_MEDIA_PREVIOUS  ACTION_DOWN");
//                    }
                    break;
            }
        }
    }
}