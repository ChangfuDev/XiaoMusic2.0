package com.yzx.xiaomusic.model.entity.common;

public class SongSheet {

    String id;
    String coverUrl;
    String title;
    String des;
    String playCount;

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getMusicCount() {
        return musicCount;
    }

    public void setMusicCount(String musicCount) {
        this.musicCount = musicCount;
    }

    String musicCount;

    String creatorId;
    String creatorCoverUrl;
    String creatorBgUrl;
    String creatorNickName;

    public String getCreatorBgUrl() {
        return creatorBgUrl;
    }

    public void setCreatorBgUrl(String creatorBgUrl) {
        this.creatorBgUrl = creatorBgUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorCoverUrl() {
        return creatorCoverUrl;
    }

    public void setCreatorCoverUrl(String creatorCoverUrl) {
        this.creatorCoverUrl = creatorCoverUrl;
    }

    public String getCreatorNickName() {
        return creatorNickName;
    }

    public void setCreatorNickName(String creatorNickName) {
        this.creatorNickName = creatorNickName;
    }
}
