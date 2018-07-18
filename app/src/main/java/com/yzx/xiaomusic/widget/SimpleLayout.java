package com.yzx.xiaomusic.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;

/**
 * Created by yzx on 2018/7/18.
 * Description  图标  文字   文字   图标这种类型的布局
 */
public class SimpleLayout extends LinearLayout {

    private int leftIcon;
    private int leftIconTint;
    private int rightIcon;
    private int rightIconTint;
    private String title;
    private String subTitle;
    private int titleColor;
    private TypedArray typedArray;
    private float titleSize;
    private int subTitleColor;
    private float subTitleSize;
    private float titleLeftMargin;
    private float subTitleRightMargin;

    public SimpleLayout(Context context) {
        this(context, null);
    }

    public SimpleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
        initView(context);
    }


    private void init(Context context, AttributeSet attrs) {
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleLayout);
        leftIcon = typedArray.getResourceId(R.styleable.SimpleLayout_leftIcon, 0);
        leftIconTint = typedArray.getResourceId(R.styleable.SimpleLayout_leftIconTint, 0x00000000);

        title = typedArray.getString(R.styleable.SimpleLayout_title);
        titleColor = typedArray.getColor(R.styleable.SimpleLayout_titleColor, 0xaa000000);
        titleSize = typedArray.getDimension(R.styleable.SimpleLayout_titleSize, (int) DensityUtils.spToPx(14));
        titleLeftMargin = typedArray.getDimension(R.styleable.SimpleLayout_titleLeftMargin, DensityUtils.dip2px(context, 10));

        subTitle = typedArray.getString(R.styleable.SimpleLayout_subTitle);
        subTitleColor = typedArray.getColor(R.styleable.SimpleLayout_subTitleColor, 0x55000000);
        subTitleSize = typedArray.getDimensionPixelSize(R.styleable.SimpleLayout_titleSize, (int) DensityUtils.spToPx(12));
        subTitleRightMargin = typedArray.getDimension(R.styleable.SimpleLayout_subTitleRightMargin, DensityUtils.dip2px(context, 10));

        rightIcon = typedArray.getResourceId(R.styleable.SimpleLayout_rightIcon, 0);
        rightIconTint = typedArray.getResourceId(R.styleable.SimpleLayout_rightIconTint, 0x00000000);

        typedArray.recycle();
    }

    private void initView(Context context) {
        setOrientation(HORIZONTAL);

        if (leftIcon != 0) {
            ImageView leftIconView = new ImageView(context);
            leftIconView.setImageResource(leftIcon);
            leftIconView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            leftIconView.setImageTintList(context.getResources().getColorStateList(leftIconTint));
            addView(leftIconView);
        }

        if (!TextUtils.isEmpty(title)) {
            TextView titleView = new TextView(context);
            titleView.setText(title);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
            titleView.setTextColor(titleColor);
            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            layoutParams.leftMargin = (int) titleLeftMargin;
            addView(titleView, layoutParams);
        }

        if (!TextUtils.isEmpty(subTitle)) {
            TextView subTitleView = new TextView(context);
            subTitleView.setText(subTitle);
            subTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, subTitleSize);
            subTitleView.setTextColor(subTitleColor);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.rightMargin = (int) subTitleRightMargin;
            addView(subTitleView, layoutParams);
        }

        if (rightIcon != 0) {
            ImageView rightIconView = new ImageView(context);
            rightIconView.setImageResource(rightIcon);
            rightIconView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            rightIconView.setImageTintList(context.getResources().getColorStateList(rightIconTint));
            addView(rightIconView);
        }

    }

}
