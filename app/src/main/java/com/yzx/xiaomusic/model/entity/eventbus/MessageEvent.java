package com.yzx.xiaomusic.model.entity.eventbus;

/**
 * @author yzx
 * @date 2018/5/17
 * Description
 */
public class MessageEvent {
    public static final int TYPE_SEARCH_CONTENT = 1;
    /**
     * 歌曲改变
     */
    public static final int TYPE_MUSIC_CHANGED = 2;
    /**
     * 缓存进度
     */
    public static final int TYPE_MUSIC_UPDATE_BUFFER = 3;
    /**
     * 播放
     */
    public static final int TYPE_MUSIC_PLAYING = 4;
    /**
     * 暂停
     */
    public static final int TYPE_MUSIC_PAUSE = 5;

    /**
     * 更新歌曲播放进度
     */
    public static final int TYPE_MUSIC_UPDATE_PROGRESS = 6;

    /**
     * 缓存进度不足时
     */
    public static final int TYPE_MUSIC_BUFFERRING = 7;

    /**
     * 服务创建完成
     */
    public static final int TYPE_SERVICE_CREATED = 7;

    /**
     * 用户信息改变（登陆登出）
     */
    public static final int TYPE_USER_INFOR_CHANGED = 8;

    /**
     * 加载中
     */
    public static final int TYPE_MUSIC_LOADING = 9;

    public MessageEvent(int type) {
        this.type = type;
    }

    public MessageEvent(int type, Object content) {
        this.type = type;
        this.content = content;
    }

    int type;
    Object content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
