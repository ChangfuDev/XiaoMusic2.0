package com.yzx.xiaomusic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yzx.xiaomusic.model.entity.common.MusicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yzx
 * @date 2018/6/19
 * Description 音乐服务
 */

public class MusicService extends Service {
    public static final String TAG = MusicService.class.getSimpleName();
    MusicInfo musicInfo;
    /**
     * 歌单
     */
    List<MusicInfo> songSheet = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    public void setSongSheet(List<MusicInfo> songSheet) {
        this.songSheet = songSheet;
    }

    public MusicInfo getMusicInfo() {
        return musicInfo;
    }


    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
