package com.yzx.xiaomusic.widget.listeningtosongs;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yzx.xiaomusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yzx
 * @date 2018/8/6
 * Description 听歌识曲View
 */
public class ListeningToSongView extends View {

    private static final String TAG = "yglListeningToSongView";
    private Paint mPaint;
    private int waveColor;
    private float innerCircleRadius;
    private int innerDrawable;
    private Bitmap bitmap;
    private int bitmapWidth;
    private int bitmapHeight;
    private int centerX;
    private int centerY;
    private int state;

    private static final int STATE_START = 1;
    private static final int STATE_STOP = 0;
    private AnimatorSet animationSet;
    private int realWidth;
    private int realHeight;
    private int waveRadius;
    private int value;
    private List<Animator> valueAnimators;
    private Animator.AnimatorListener listener;

    public ListeningToSongView(Context context) {
        this(context, null);
    }

    public ListeningToSongView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListeningToSongView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ListeningToSongView);
        waveColor = typedArray.getColor(R.styleable.ListeningToSongView_ltsv_wave_color, Color.RED);
        innerCircleRadius = typedArray.getDimension(R.styleable.ListeningToSongView_ltsv_inner_circle_radius, dp2px(20));
        innerDrawable = typedArray.getResourceId(R.styleable.ListeningToSongView_ltsv_inner_drawable, R.drawable.t_actionbar_music_selected);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        bitmap = BitmapFactory.decodeResource(getResources(), innerDrawable);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();

        animationSet = new AnimatorSet();
        valueAnimators = new ArrayList<>();
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
            heightSize = dp2px(300);
            Log.i("ygl", "onMeasure: AT_MOST");
        }

        setMeasuredDimension(widthSize, heightSize);

        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        realWidth = measuredWidth - getPaddingStart() - getPaddingEnd();
        realHeight = measuredHeight - getPaddingTop() - getPaddingBottom();


        centerX = realWidth / 2;
        centerY = realHeight / 2;

        waveRadius = Math.max(centerX, centerY);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (state == STATE_STOP) {
            drawStopCircle(canvas);
        } else {
            drawWave(canvas);
        }

        drawInnerCircle(canvas);
        drawBitmap(canvas);

    }

    private void drawStopCircle(Canvas canvas) {
        mPaint.setShader(null);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(0.25f));
        mPaint.setColor(waveColor);
        mPaint.setAlpha(50);
        int space = dp2px(50);
        int count = (int) ((waveRadius - innerCircleRadius) / space);
        Log.i(TAG, "drawStopCircle: " + count);
        for (int i = 0; i < count; i++) {
            float radius = innerCircleRadius + (i + 1) * space;
            if (radius < waveRadius) {
                Log.i(TAG, "drawStopCircle: " + radius);
                canvas.drawCircle(centerX, centerY, radius, mPaint);
            }
        }
    }

    private void drawWave(Canvas canvas) {

//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(dp2px(2));
        mPaint.setAlpha((waveRadius - value) * 100 / waveRadius);
        RadialGradient radialGradient = new RadialGradient(centerX, centerY, value <= 0 ? 1 : value, 0x00000000, waveColor, Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);

        canvas.drawCircle(centerX, centerY, value, mPaint);
//        for (int i = 0; i < 3; i++) {
//
//        }

    }

    /**
     * 画图片
     *
     * @param canvas
     */
    private void drawBitmap(Canvas canvas) {

        int top = centerY - bitmapHeight / 2;
        int left = centerX - bitmapWidth / 2;
        canvas.drawBitmap(bitmap, left, top, mPaint);

    }

    /**
     * 内圆
     *
     * @param canvas
     */
    private void drawInnerCircle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(null);
        mPaint.setColor(waveColor);


        canvas.drawCircle(centerX, centerY, innerCircleRadius, mPaint);
    }

    /**
     * 开始
     */
    public void startStop() {

        if (state == STATE_START) {
            if (animationSet != null) {
                animationSet.cancel();
            }
            value = 0;
            invalidate();
            state = STATE_STOP;
        } else if (state == STATE_STOP) {
            valueAnimators.clear();
            for (int i = 0; i < 100; i++) {
                ValueAnimator animation = createAnimation();
                valueAnimators.add(animation);
            }

            listener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    state = STATE_STOP;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };
            animationSet.addListener(listener);
            animationSet.playSequentially(valueAnimators);
            animationSet.start();
            state = STATE_START;
        }

    }

    public ValueAnimator createAnimation() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1000);
        valueAnimator.setIntValues((int) innerCircleRadius, waveRadius);
        valueAnimator.addUpdateListener(animation -> {
            value = (int) animation.getAnimatedValue();
            invalidate();
        });
        return valueAnimator;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow: ");
        animationSet.cancel();
//        listener = null;
//        animationSet = null;
        valueAnimators.clear();
        valueAnimators = null;
    }
}
