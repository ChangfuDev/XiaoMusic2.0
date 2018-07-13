package com.yzx.xiaomusic.utils;


import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;

/**
 * @author yzx
 */
public class TimeUtils {

    public static final String FORMAT_MM_SS = "mm:ss";
    public static final String FORMAT_HH_MM = "hh:mm";
    public static final String FORMAT_YYYY_MM_DD = "yyyy年MM月dd日";
    public static final String FORMAT_MM_DD = "MM月dd日";
    public static final String FORMAT_YYYY = "yyyy";


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

    /**
     * 获取友好时间显示
     *
     * @param time
     * @return
     */
    public static String getFriendlyData(long time) {

        //不是今年
        if (!isThisYear(time)) {
            return getFormatData(time, FORMAT_YYYY_MM_DD);
        }

        //是今天
        if (DateUtils.isToday(time)) {
            //距现在的时间
            long l = System.currentTimeMillis() - time;
            //五分钟前
            if (l < 5 * 60 * 1000) {
                return "刚刚";
            } else if (l < 60 * 60 * 1000) {
                return l / 60 / 1000 + "分钟前";
            } else {
                return getFormatData(time, FORMAT_HH_MM);
            }
        }

        return getFormatData(time, FORMAT_MM_DD);
    }

    private static boolean isThisYear(long time) {
        return TextUtils.equals(getFormatData(time, FORMAT_YYYY), getFormatData(System.currentTimeMillis(), FORMAT_YYYY));
    }


}
