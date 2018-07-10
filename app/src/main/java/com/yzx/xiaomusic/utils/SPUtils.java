package com.yzx.xiaomusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yzx.xiaomusic.app.MusicApplication;

/**
 * Created by yzx on 2018/7/10.
 * Description SharedPreferences工具类
 */
public class SPUtils {

    public static SharedPreferences getSP() {
        return MusicApplication.getContext().getSharedPreferences("XiaoMusic", Context.MODE_PRIVATE);
    }

    public static void putString(String key, String value) {
        getSP().edit().putString(key, value).apply();
    }

    public static String getString(String key, String defaultValue) {
        return getSP().getString(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        getSP().edit().putInt(key, value).apply();
    }

    public static int getInt(String key, int defaultValue) {
        return getSP().getInt(key, defaultValue);
    }

}
