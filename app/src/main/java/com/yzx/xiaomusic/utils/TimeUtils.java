package com.yzx.xiaomusic.utils;


import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

/**
 * @author yzx
 */
public class TimeUtils {

    public static final String FORMAT_MM_SS = "mm:ss";


    @SuppressLint("SimpleDateFormat")
    public static String getFormatData(long time, String format) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(time);
    }

    public static String getToday() {

        String data = getFormatData(System.currentTimeMillis(), "dd");
        if (data.startsWith("0")) {
            return data.replace("0", "");
        }
        return data;
    }
}
