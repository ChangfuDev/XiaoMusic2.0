package com.yzx.xiaomusic.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by yzx on 2018/7/9.
 * Description
 */

public class MusicSeekBar extends android.support.v7.widget.AppCompatSeekBar {

    private Paint paint;

    public MusicSeekBar(Context context) {
        this(context, null);
    }

    public MusicSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void setThumb(Drawable thumb) {
        super.setThumb(thumb);
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
    }
}
