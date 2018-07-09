package com.yzx.xiaomusic.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author yzx
 * @date 2018/6/29
 * Description 喜欢的歌曲信息
 */
@Entity(indices = {@Index(value = {"musicId"}, unique = true)})
public class ExtraMusicInfo {

    @PrimaryKey(autoGenerate = true)
    public int id;


    public ExtraMusicInfo(String musicId, String musicInfo,int liked, int listened) {
        this.liked =liked;
        this.listened = listened;
        this.musicId = musicId;
        this.musicInfo = musicInfo;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getListened() {
        return listened;
    }

    public void setListened(int listened) {
        this.listened = listened;
    }

    /**
     * 喜欢记录标识
     */
    public int liked;
    /**
     * 听歌记录标识（最近播放）
     */
    public int listened;
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
