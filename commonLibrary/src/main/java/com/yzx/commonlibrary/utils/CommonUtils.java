package com.yzx.commonlibrary.utils;

import android.support.annotation.Nullable;

/**
 * Created by yzx on 2018/4/24.
 * Description
 */
public class CommonUtils {

    public static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
