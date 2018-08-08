package com.yzx.xiaomusic.widget.grade;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.yzx.xiaomusic.R;

/**
 * Created by yzx on 2018/8/6.
 * Description  等级View
 */
public class GradeView extends View {

    private Paint mPaint;
    private int realWidth;
    private int realHeight;
    private int backLineColor;
    private int foreLineStartColor;
    private int foreLineStopColor;
    private int dividerColor;
    private float gradeLineHeight;
    private float space;
    private float gradeTextSize;
    private int gradeTextColor;
    private float dividerWidth;
    private int measuredWidth;
    private int measuredHeight;
    private int gradeCount;
    private float currentGrade;
    private float value;

    public GradeView(Context context) {
        this(context, null);
    }

    public GradeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradeView);
        backLineColor = typedArray.getColor(R.styleable.GradeView_gv_gradeBackLineColor, 0xffe1e2e4);
        foreLineStartColor = typedArray.getColor(R.styleable.GradeView_gv_gradeForeLineStartColor, 0xffE0FFFF);
        foreLineStopColor = typedArray.getColor(R.styleable.GradeView_gv_gradeForeLineStopColor, 0xFF009688);
        dividerColor = typedArray.getColor(R.styleable.GradeView_gv_gradeDividerColor, 0xffffffff);
        gradeTextColor = typedArray.getColor(R.styleable.GradeView_gv_gradeTextColor, 0xFF009688);

        gradeLineHeight = typedArray.getDimension(R.styleable.GradeView_gv_gradeLineHeight, dp2px(8));
        space = typedArray.getDimension(R.styleable.GradeView_gv_space, dp2px(3));
        gradeTextSize = typedArray.getDimension(R.styleable.GradeView_gv_gradeTextSize, sp2px(8));
        dividerWidth = typedArray.getDimension(R.styleable.GradeView_gv_gradeDividerWidth, dp2px(1.5f));
        gradeCount = typedArray.getInteger(R.styleable.GradeView_gv_count, 0);
        currentGrade = typedArray.getFloat(R.styleable.GradeView_gv_current, 0);

        typedArray.recycle();


    }

    private void doAnimate() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(realWidth * currentGrade / gradeCount);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            value = (float) animation.getAnimatedValue();
            invalidate();
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();
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


    /**
     * convert sp to its equivalent px
     * <p>
     * 将sp转换为px
     */
    public int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 默认宽度和高度分别为300dp和60dp
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
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
            heightSize = dp2px(60);
        }


        setMeasuredDimension(widthSize, heightSize);

        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();

        realWidth = measuredWidth - getPaddingStart() - getPaddingEnd();
        realHeight = measuredHeight - getPaddingTop() - getPaddingBottom();

        doAnimate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (gradeCount == 0) {
            return;
        }

        mPaint.setTextSize(gradeTextSize);

        int startX = getPaddingStart();
        //上下居中显示
        int startY = (int) ((realHeight - gradeTextSize - space - gradeLineHeight) / 2);

        drawLine(canvas, startX, startY);
//        drawForeLine(canvas, startX, startY);
        drawDividerAndText(canvas, startX, startY);
    }

    /**
     * 画分割线和文字
     *
     * @param canvas
     * @param startX
     * @param startY
     */
    private void drawDividerAndText(Canvas canvas, int startX, int startY) {

        mPaint.setColor(dividerColor);
        mPaint.setShader(null);
        int everyGradeWidth = realWidth / gradeCount;

        int top = (int) (startY + gradeTextSize + space);
        int bottom = (int) (top + gradeLineHeight);


        for (int i = 1; i < gradeCount; i++) {
            int dividerStartX = (int) (startX + everyGradeWidth * i - dividerWidth / 2);
            int dividerStopX = (int) (startX + everyGradeWidth * i + dividerWidth / 2);
            canvas.drawRect(dividerStartX, top, dividerStopX, bottom, mPaint);
        }


        mPaint.setTextAlign(Paint.Align.CENTER);

        float textCenterY = startY + gradeTextSize / 2;

        for (int i = 0; i <= gradeCount; i++) {
            int textX = startX + everyGradeWidth * i;

            if (i == 0) {
                textX = (int) (textX + gradeTextSize / 2);
            } else if (i == gradeCount) {
                textX = (int) (textX - gradeTextSize / 2);
            }


            if ((int) currentGrade == i) {
                mPaint.setColor(gradeTextColor);
                canvas.drawCircle(textX, textCenterY - gradeTextSize / 4, gradeTextSize * 0.6f, mPaint);
                mPaint.setColor(Color.WHITE);
            } else {
                mPaint.setColor(gradeTextColor);
            }


            canvas.drawText(String.valueOf(i), textX, textCenterY, mPaint);

        }
    }

    /**
     * 画背景线和进度线
     *
     * @param canvas
     * @param startX
     * @param startY
     */
    private void drawLine(Canvas canvas, int startX, int startY) {

        mPaint.setColor(backLineColor);
        mPaint.setShader(null);
        int left = startX;
        int top = (int) (startY + gradeTextSize + space);
        int bottom = (int) (top + gradeLineHeight);
        int right = left + realWidth;
        int cornerRadius = (int) (gradeLineHeight / 2);

        canvas.drawRoundRect(left, top, right, bottom, cornerRadius, cornerRadius, mPaint);

        int foreRight = (int) (left + value);

        LinearGradient linearGradient = new LinearGradient(0, 0, foreRight - left, 0, foreLineStartColor, foreLineStopColor, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
//        mPaint.setColor(foreLineStartColor);
        canvas.drawRoundRect(left, top, foreRight, bottom, cornerRadius, cornerRadius, mPaint);


    }
}
