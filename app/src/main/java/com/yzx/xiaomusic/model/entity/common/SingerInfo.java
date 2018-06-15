package com.yzx.xiaomusic.model.entity.common;

public class SingerInfo {

    //歌手
    String singerId;
    String singerName;
    String singerCoverPath;

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerCoverPath() {
        return singerCoverPath;
    }

    public void setSingerCoverPath(String singerCoverPath) {
        this.singerCoverPath = singerCoverPath;
    }

    @Override
    public String toString() {
        return "SingerInfo{" +
                "singerId='" + singerId + '\'' +
                ", singerName='" + singerName + '\'' +
                ", singerCoverPath='" + singerCoverPath + '\'' +
                '}';
    }
}
