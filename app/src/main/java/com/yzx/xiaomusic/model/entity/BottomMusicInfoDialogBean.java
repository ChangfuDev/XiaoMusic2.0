package com.yzx.xiaomusic.model.entity;

/**
 * @author yzx
 */
public class BottomMusicInfoDialogBean {

    int drawable;
    String title;

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public BottomMusicInfoDialogBean(int drawable, String title) {
        this.drawable = drawable;
        this.title = title;
    }
}