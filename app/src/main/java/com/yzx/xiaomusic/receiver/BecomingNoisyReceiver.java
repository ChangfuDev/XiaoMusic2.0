package com.yzx.xiaomusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;

/**
 * Desc : 拔出耳机暂停
 * Author : Lauzy
 * Date : 2018/4/4
 * Blog : http://www.jianshu.com/u/e76853f863a9
 * Email : freedompaladin@gmail.com
 *
 * @author yzx
 */
public class BecomingNoisyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("yglBecomingNoisyReceiver", "onReceive: ");
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        switch (action) {
            case AudioManager.ACTION_AUDIO_BECOMING_NOISY:
                MusicService service = ServiceManager.getInstance().getService();
                if (service != null) {
                    service.pause();
                }
                break;
        }
    }
}
