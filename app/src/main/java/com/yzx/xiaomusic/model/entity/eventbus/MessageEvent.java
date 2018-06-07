package com.yzx.xiaomusic.model.entity.eventbus;

/**
 * Created by yzx on 2018/5/17.
 * Description
 */
public class MessageEvent {

    public MessageEvent(int type, Object content) {
        this.type = type;
        this.content = content;
    }

    public static final int TYPE_SEARCH_CONTENT = 1;
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
