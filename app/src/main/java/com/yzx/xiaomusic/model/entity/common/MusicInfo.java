package com.yzx.xiaomusic.model.entity.common;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity
public class MusicInfo {


    @PrimaryKey(autoGenerate = true)
    long id;

    //音乐
    String musicId;
    String musicName;


    public List<SingerInfo> getSingerInfos() {
        return singerInfos;
    }

    public void setSingerInfos(List<SingerInfo> singerInfos) {
        this.singerInfos = singerInfos;
    }

    List<SingerInfo> singerInfos;

    //专辑
    String albumId;
    String albumName;
    String albumCoverPath;


    public String getRingTone() {
        return ringTone;
    }

    public void setRingTone(String ringTone) {
        this.ringTone = ringTone;
    }

    //SQ音质
    String ringTone;

    //mv
    String mvId;
    String mvName;
    String mvCoverPath;
    int mvPlayCount;

    public String getMvId() {
        return mvId;
    }

    public void setMvId(String mvId) {
        this.mvId = mvId;
    }

    public String getMvName() {
        return mvName;
    }

    public void setMvName(String mvName) {
        this.mvName = mvName;
    }

    public String getMvCoverPath() {
        return mvCoverPath;
    }

    public void setMvCoverPath(String mvCoverPath) {
        this.mvCoverPath = mvCoverPath;
    }

    public int getMvPlayCount() {
        return mvPlayCount;
    }

    public void setMvPlayCount(int mvPlayCount) {
        this.mvPlayCount = mvPlayCount;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumCoverPath() {
        return albumCoverPath;
    }

    public void setAlbumCoverPath(String albumCoverPath) {
        this.albumCoverPath = albumCoverPath;
    }

    @Override
    public String toString() {
        return "MusicInfo{" +
                "id=" + id +
                ", musicId='" + musicId + '\'' +
                ", musicName='" + musicName + '\'' +
                ", singerInfos=" + singerInfos +
                ", albumId='" + albumId + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumCoverPath='" + albumCoverPath + '\'' +
                ", mvId='" + mvId + '\'' +
                ", mvName='" + mvName + '\'' +
                ", mvCoverPath='" + mvCoverPath + '\'' +
                ", mvPlayCount=" + mvPlayCount +
                '}';
    }
}
