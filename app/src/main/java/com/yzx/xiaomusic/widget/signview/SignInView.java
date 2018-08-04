package com.yzx.xiaomusic.widget.signview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yzx.xiaomusic.R;

import java.util.ArrayList;

/**
 * Created by yzx on 2018/8/4.
 * Description 签到
 */
public class SignInView extends View {


    private ArrayList<SignInData> signInDatas;
    private int lineColor;
    private int unSignIconColor;
    private int signedIconColor;
    private int unSignTextColor;
    private int signedTextColor;
    private float unSignTextSize;
    private float signedTextSize;
    private Paint mPaint;
    private int iconRadius;
    private int measuredWidth;
    private int lineWidth;
    private float lineHeight;
    private Rect bounds;
    private Path path;
    private float space;

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


    public SignInView(Context context) {
        this(context, null);
    }

    public SignInView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignInView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SignInView);

        lineColor = typedArray.getColor(R.styleable.SignInView_siv_lineColor, 0xffe1e2e4);
        unSignIconColor = typedArray.getColor(R.styleable.SignInView_siv_unSignIconColor, 0xffe1e2e4);
        signedIconColor = typedArray.getColor(R.styleable.SignInView_siv_signedIconColor, 0xff009688);
        unSignTextColor = typedArray.getColor(R.styleable.SignInView_siv_unSignTextColor, 0xff8c8c8c);
        signedTextColor = typedArray.getColor(R.styleable.SignInView_siv_signedTextColor, 0xff009688);

        unSignTextSize = typedArray.getDimension(R.styleable.SignInView_siv_unSignTextSize, sp2px(12));
        signedTextSize = typedArray.getDimension(R.styleable.SignInView_siv_signedTextSize, sp2px(12));
        lineHeight = typedArray.getDimension(R.styleable.SignInView_siv_lineHeight, dp2px(3));
        space = typedArray.getDimension(R.styleable.SignInView_siv_space, dp2px(5));

        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        iconRadius = dp2px(10);
        //存放测量后文字大小
        bounds = new Rect();
        path = new Path();

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    /**
     * 宽高：300,60
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
            Log.i("ygl", "onMeasure: UNSPECIFIED");
        }


        setMeasuredDimension(widthSize, heightSize);

    }


    /**
     * 根据高度居中显示，不算padding值
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (signInDatas == null || signInDatas.isEmpty()) {
            return;
        }

        measuredWidth = getMeasuredWidth();

        int measuredHeight = getMeasuredHeight();

        //Y起始点
        int startTopY = (int) ((measuredHeight - iconRadius * 2 - space - Math.max(unSignTextSize, signedTextSize)) / 2);
        //线长
        lineWidth = (measuredWidth - iconRadius * 2 * signInDatas.size() - getPaddingStart() - getPaddingEnd()) / (signInDatas.size() - 1);


        int iconCenterX;
        int iconCenterY = iconRadius + getPaddingTop() + startTopY;
        int lineStartY = (int) (iconCenterY - lineHeight / 2);
        int lineStopY = (int) (iconCenterY - lineHeight / 2);


        for (int i = 0; i < signInDatas.size(); i++) {

            SignInData signInData = signInDatas.get(i);


            //
            drawLine(canvas, lineStartY, lineStopY, i);


            //画图标
            iconCenterX = i * (lineWidth + iconRadius * 2) + iconRadius + getPaddingStart();

            drawIcon(canvas, iconCenterX, iconCenterY, signInData);


            String des = signInData.getDes();
            if (!TextUtils.isEmpty(des)) {

                drawText(canvas, iconCenterX, iconCenterY, signInData, des);
            }
        }

    }

    /**
     * 画文字
     *
     * @param canvas
     * @param iconCenterX
     * @param iconCenterY
     * @param signInData
     * @param des
     */
    private void drawText(Canvas canvas, int iconCenterX, int iconCenterY, SignInData signInData, String des) {
        int textCenterX;
        int textCenterY;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        if (signInData.state == SignInData.STATE_NO) {
            mPaint.setColor(unSignTextColor);
            mPaint.setTextSize(unSignTextSize);
        } else if (signInData.state == SignInData.STATE_SIGNED) {
            mPaint.setColor(signedTextColor);
            mPaint.setTextSize(signedTextSize);
        } else {
            mPaint.setColor(signedTextColor);
            mPaint.setTextSize(signedTextSize);
        }
        textCenterX = iconCenterX;
        mPaint.measureText(des);

        mPaint.getTextBounds(des, 0, des.length(), bounds);
        textCenterY = (int) (iconCenterY + iconRadius + bounds.height() + space);

        Log.i("ygl", mPaint.getTextSize() + "drawText: " + bounds.height());
        canvas.drawText(des, textCenterX, textCenterY, mPaint);
    }

    /**
     * 画icon
     *
     * @param canvas
     * @param iconCenterX
     * @param iconCenterY
     * @param signInData
     */
    private void drawIcon(Canvas canvas, int iconCenterX, int iconCenterY, SignInData signInData) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(dp2px(2));
        if (signInData.state == SignInData.STATE_NO) {

            //画内圆
            mPaint.setColor(unSignIconColor);
            canvas.drawCircle(iconCenterX, iconCenterY, dp2px(2), mPaint);
        } else if (signInData.state == SignInData.STATE_SIGNED) {

            //画对勾
            mPaint.setColor(signedIconColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(dp2px(1));
            mPaint.setStrokeCap(Paint.Cap.ROUND);

            canvas.drawPath(getRightShapePath(iconCenterX, iconCenterY), mPaint);
        } else {
            mPaint.setColor(signedIconColor);
        }
        //画外圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(1));
        canvas.drawCircle(iconCenterX, iconCenterY, iconRadius, mPaint);
    }

    /**
     * 划线
     *
     * @param canvas
     * @param lineStartY
     * @param lineStopY
     * @param i
     */
    private void drawLine(Canvas canvas, int lineStartY, int lineStopY, int i) {
        int lineStartX;
        int lineStopX;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(lineHeight);
        mPaint.setColor(lineColor);

        if (i < signInDatas.size() - 1) {

            lineStartX = (i + 1) * iconRadius * 2 + i * lineWidth + getPaddingStart();
            lineStopX = lineStartX + lineWidth;

            canvas.drawLine(lineStartX, lineStartY, lineStopX, lineStopY, mPaint);
        }
    }

    private Path getRightShapePath(int iconCenterX, int iconCenterY) {
        //重置Path
        path.reset();
        path.moveTo(iconCenterX - dp2px(4), iconCenterY);
        path.lineTo(iconCenterX - dp2px(2), iconCenterY + dp2px(3));
        path.lineTo(iconCenterX + dp2px(5), iconCenterY - dp2px(3));
        return path;
    }


    /**
     * 设置数据
     *
     * @param signInDatas
     */
    public void setData(ArrayList<SignInData> signInDatas) {
        this.signInDatas = signInDatas;
        invalidate();
    }
}
