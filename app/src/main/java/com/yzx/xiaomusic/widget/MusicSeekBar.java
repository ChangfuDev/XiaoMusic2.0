package com.yzx.xiaomusic.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.yzx.xiaomusic.R;

/**
 * @author yzx
 * @date 2018/7/9
 * Description  带loading状态的进度条
 */

public class MusicSeekBar extends View {

    private static final String TAG = "MusicSeekBar";
    private Paint mPaint;
    private int backColor;
    private int secondProgressColor;
    private int progressColor;
    private int max;
    private int secondProgress;
    private int progress;
    private float seekBarHeight;
    private int thumb;
    private int loadingBg;
    private int loadingThumb;

    private int measuredHeight;
    private int measuredWidth;
    private int startX;
    private int centerY;
    private int seekBarCornerRadius;
    private float realWidth;
    public static final int STATE_PLAYING = 0;
    public static final int STATE_LOADING = 1;
    private int state = STATE_PLAYING;
    private int animatedValue;
    private Matrix matrix;
    private ValueAnimator valueAnimator;

    public MusicSeekBar(Context context) {
        this(context, null);
    }

    public MusicSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MusicSeekBar);

        backColor = typedArray.getColor(R.styleable.MusicSeekBar_msb_backColor, 0x33000000);
        secondProgressColor = typedArray.getColor(R.styleable.MusicSeekBar_msb_secondProgressColor, 0xffe1e2e4);
        progressColor = typedArray.getColor(R.styleable.MusicSeekBar_msb_progressColor, 0xff009688);

        max = typedArray.getInteger(R.styleable.MusicSeekBar_msb_max, 100);
        secondProgress = typedArray.getInteger(R.styleable.MusicSeekBar_msb_secondProgress, 10);
        progress = typedArray.getInteger(R.styleable.MusicSeekBar_msb_progress, 0);

        seekBarHeight = typedArray.getDimension(R.styleable.MusicSeekBar_msb_height, dp2px(6));

        thumb = typedArray.getResourceId(R.styleable.MusicSeekBar_msb_thumb, R.drawable.af3);

        loadingBg = typedArray.getResourceId(R.styleable.MusicSeekBar_msb_loading_bg, R.drawable.aeu);
        loadingThumb = typedArray.getResourceId(R.styleable.MusicSeekBar_msb_loading_thumb, R.drawable.aev);


        typedArray.recycle();
        seekBarCornerRadius = (int) (seekBarHeight / 2);
        matrix = new Matrix();

    }

    /**
     * convert dp to its equivalent px
     * <p>
     * 将dp转换为与之相等的px
     */
    public int dp2px(float dipValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = dp2px(300);
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = dp2px(20);
        }


        setMeasuredDimension(widthSize, heightSize);

        measuredHeight = getMeasuredHeight();
        measuredWidth = getMeasuredWidth();

    }

    /**
     * 高度居中
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (max == 0) {
            return;
        }

        startX = getPaddingStart();
        realWidth = measuredWidth - getPaddingStart() - getPaddingEnd();
        centerY = (measuredHeight - getPaddingTop() - getPaddingBottom()) / 2;


        float left = startX;
        float top = centerY - seekBarHeight / 2;
        float right = left + realWidth;
        float bottom = top + seekBarHeight;
        drawLines(canvas, left, top, right, bottom);

    }

    private void drawLines(Canvas canvas, float left, float top, float right, float bottom) {

        //画底线
        mPaint.setColor(backColor);
        canvas.drawRoundRect(left, top, right, bottom, seekBarCornerRadius, seekBarCornerRadius, mPaint);

        //secondProgress
        mPaint.setColor(secondProgressColor);
        float secondRight = left + realWidth * secondProgress / max;
        canvas.drawRoundRect(left, top, secondRight, bottom, seekBarCornerRadius, seekBarCornerRadius, mPaint);

        //progress
        mPaint.setColor(progressColor);
        float progressRight = left + realWidth * progress / max;
        canvas.drawRoundRect(left, top, progressRight, bottom, seekBarCornerRadius, seekBarCornerRadius, mPaint);

        //画图片
        if (state == STATE_PLAYING) {
            Bitmap thumbBitmap = BitmapFactory.decodeResource(getResources(), thumb);
            int thumbBitmapWidth = thumbBitmap.getWidth();

            //左右显示优化
            if (progressRight < left + thumbBitmapWidth / 2) {
                progressRight = left;
            } else if (progressRight > right - thumbBitmapWidth) {
                progressRight = right - thumbBitmapWidth;
            } else {
                progressRight = progressRight - thumbBitmapWidth / 2;
            }

            canvas.drawBitmap(thumbBitmap, progressRight, centerY - thumbBitmap.getHeight() / 2, mPaint);
            canvas.drawCircle(progressRight + thumbBitmapWidth / 2, centerY, seekBarCornerRadius, mPaint);
        } else {
            Bitmap loadingThumbBg = BitmapFactory.decodeResource(getResources(), loadingBg);
            Bitmap loadingThumbBitmap = BitmapFactory.decodeResource(getResources(), loadingThumb);


            int loadingThumbBgWidth = loadingThumbBg.getWidth();

            if (progressRight < left + loadingThumbBgWidth / 2) {
                progressRight = left;
            } else if (progressRight > right - loadingThumbBgWidth) {
                progressRight = right - loadingThumbBgWidth;
            } else {
                progressRight = progressRight - loadingThumbBgWidth / 2;
            }


            canvas.drawBitmap(loadingThumbBg, progressRight, centerY - loadingThumbBg.getHeight() / 2, mPaint);

            Log.i(TAG, "drawLines: " + animatedValue);
            matrix.reset();
            matrix.postRotate(animatedValue, loadingThumbBitmap.getWidth() / 2, loadingThumbBitmap.getHeight() / 2);
            matrix.postTranslate(progressRight + loadingThumbBgWidth / 2 - loadingThumbBitmap.getWidth() / 2, centerY - loadingThumbBitmap.getHeight() / 2);

            mPaint.setColorFilter(new PorterDuffColorFilter(progressColor, PorterDuff.Mode.SRC_IN));
//            canvas.drawBitmap(loadingThumbBitmap, progressRight + loadingThumbBgWidth / 2 - loadingThumbBitmap.getWidth() / 2, centerY - loadingThumbBitmap.getHeight() / 2, mPaint);
            canvas.drawBitmap(loadingThumbBitmap, matrix, mPaint);
            mPaint.setColorFilter(null);
        }
    }


    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setSecondProgress(int secondProgress) {
        this.secondProgress = secondProgress;
        invalidate();
    }

    private void animat() {

        valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3 * 60 * 1000);
        valueAnimator.setIntValues(3 * 60 * 360);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            animatedValue = (int) animation.getAnimatedValue();
            invalidate();
        });
        valueAnimator.start();
    }

    public void setState(int state) {

        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state == STATE_PLAYING) {
            if (valueAnimator != null && valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            invalidate();
        } else {
            animat();
        }
    }

    public int getState() {
        return state;
    }
}
