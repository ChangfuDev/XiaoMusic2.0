package com.yzx.commonlibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by yzx on 2017/12/1.
 */

public class ResourceUtils {

    /**
     * 获取资源管理器
     *
     * @return
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 颜色转换
     *
     * @param color
     * @return
     */
    public static int parseColor(Context context, @ColorRes int color) {

        return getResources(context).getColor(color);
    }

    /**
     * @param string
     * @return
     */
    public static String parseString(Context context, @StringRes int string) {

        return getResources(context).getString(string);
    }

    public static View parseLayout(Context context, @LayoutRes int layoutId) {
        return parseLayout(context, layoutId, null);
    }

    public static View parseLayout(Context context, @LayoutRes int layoutId, ViewGroup container) {
        return LayoutInflater.from(context).inflate(layoutId, container, false);
    }
}
