package com.yzx.xiaomusic.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by yzx on 2018/7/4.
 * Description 无法滑动的LinearLayoutManager
 */
public class UnScrollGridLayoutManager extends GridLayoutManager {


    public UnScrollGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public UnScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public UnScrollGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
