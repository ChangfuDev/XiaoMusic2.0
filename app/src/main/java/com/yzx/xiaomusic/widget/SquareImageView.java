package com.yzx.xiaomusic.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.othershe.library.NiceImageView;
import com.yzx.commonlibrary.utils.DensityUtils;

/**
 * Created by yzx on 2018/1/23.
 * Description  正方形ImageView,随宽度自适应高度
 */

public class SquareImageView extends NiceImageView {
    public SquareImageView(Context context) {
        this(context,null);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setCornerRadius(DensityUtils.dip2px(context,2));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth,measuredWidth);
    }
}
