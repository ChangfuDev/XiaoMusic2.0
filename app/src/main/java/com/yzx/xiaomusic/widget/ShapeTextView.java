package com.yzx.xiaomusic.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.commonlibrary.utils.LogUtils;
import com.yzx.xiaomusic.R;

/**
 * Created by yzx on 2018/5/29.
 * Description
 */
public class ShapeTextView extends AppCompatTextView {

    private Paint paint;
    private int solidColor;
    private int strokeColor;
    private float strokeWidth;

    public static final String TAG = ShapeTextView.class.getSimpleName();

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        init(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(strokeColor);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
//        paint.setTextSize(strokeWidth);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
        solidColor = attributes.getColor(R.styleable.ShapeTextView_solidColor, 0x00000000);
        strokeColor = attributes.getColor(R.styleable.ShapeTextView_strokeColor, 0x00000000);
        strokeWidth = attributes.getDimension(R.styleable.ShapeTextView_strokeWidth, DensityUtils.dip2px(0.5f));
        attributes.recycle();
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        int radius = Math.min(getMeasuredHeight(), getMeasuredWidth()) / 2;
        RectF strokeRect = new RectF(3, 3, getMeasuredWidth() - 3, getMeasuredHeight() - 3);
        paint.setColor(solidColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(strokeRect, radius, radius, paint);
        paint.setColor(strokeColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(strokeRect, radius, radius, paint);
        super.onDraw(canvas);
    }

    public void setSolidColor(int solidColor) {
        this.solidColor = solidColor;
        invalidate();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidate();
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        invalidate();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                LogUtils.d(TAG, "MotionEvent.ACTION_DOWN");
//                setSolidColor(getResources().getColor(R.color.colorAccent));
//                break;
//            case MotionEvent.ACTION_UP:
//                setSolidColor(solidColor);
//                LogUtils.d(TAG, "MotionEvent.ACTION_UP");
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}
