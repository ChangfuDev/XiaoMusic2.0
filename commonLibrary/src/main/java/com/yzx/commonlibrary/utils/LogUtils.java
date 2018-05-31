package com.yzx.commonlibrary.utils;

import android.util.Log;

/**
 *
 * @author yzx
 * @date 2018/4/24
 * Description log工具类
 */
public class LogUtils {

    public static boolean DEBUG = true;
    /**
     * 加个前缀
     */
    public static final String extraTag = "ygl";

    public static void d(String TAG, String msg) {
        if (DEBUG) {
            Log.d(extraTag + TAG, msg);
        }
    }

    public static void i(String TAG, String msg) {
        if (DEBUG) {
            Log.i(extraTag + TAG, msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (DEBUG) {
            Log.e(extraTag + TAG, msg);
        }
    }

}
