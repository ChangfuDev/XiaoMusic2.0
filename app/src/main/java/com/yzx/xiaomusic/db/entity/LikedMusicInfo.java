package com.yzx.xiaomusic.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by yzx on 2018/6/29.
 * Description 喜欢的歌曲信息
 */
@Entity(indices = {@Index(value = {"musicId"}, unique = true)})
public class LikedMusicInfo {

    @PrimaryKey(autoGenerate = true)
   public int id;

    public LikedMusicInfo(String musicId, String musicInfo) {
        this.musicId = musicId;
        this.musicInfo = musicInfo;
    }

    public String musicId;

    public String musicInfo;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicInfo() {
        return musicInfo;
    }

    public void setMusicInfo(String musicInfo) {
        this.musicInfo = musicInfo;
    }
}
