package com.yzx.xiaomusic.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by yzx on 2018/7/4.
 * Description 无法滑动的LinearLayoutManager
 */
public class UnScrollLinearLayoutManager extends LinearLayoutManager {
    public UnScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public UnScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public UnScrollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
