package com.yzx.xiaomusic.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.yzx.xiaomusic.R;

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
    protected synchronized void onDraw(Canvas canvas) {
        @SuppressLint("DrawAllocation")
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aeu);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        super.onDraw(canvas);
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
    }
}
