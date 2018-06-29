package com.yzx.xiaomusic.model.entity.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author yzx
 * 音乐信息
 */
//@Entity
public class MusicInfo implements Serializable {

//    @PrimaryKey(autoGenerate = true)
    long id;
    /**
     * 是否是本地音乐
     **/
    boolean isLocal;

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 本地音乐路径
     */
    String path;

    /**
     * 音乐
     */
    String musicId;
    String musicName;
    long duration;
    long size;
    String poster;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<SingerInfo> getSingerInfos() {
        return singerInfos;
    }

    public void setSingerInfos(List<SingerInfo> singerInfos) {
        this.singerInfos = singerInfos;
    }

    List<SingerInfo> singerInfos;

    /**
     * 专辑
     */
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

    /**
     * mv
     **/

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
