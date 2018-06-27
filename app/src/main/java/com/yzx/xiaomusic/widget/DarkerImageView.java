package com.yzx.xiaomusic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 *
 * @author yzx
 * @date 2018/2/28
 * Description  往图片上蒙层灰色防止图片太白
 */

public class DarkerImageView extends android.support.v7.widget.AppCompatImageView {
    public DarkerImageView(Context context) {
        super(context);
    }

    public DarkerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DarkerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(0x88000000);
    }
}
