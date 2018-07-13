package com.yzx.xiaomusic.utils;

/**
 * @author yzx
 * @date 2018/7/13
 * Description
 */
public class CommonUtils {

    public static String getFriendlyCount(int count) {

        if (count <= 0) {
            return "";
        }
        if (count > 10000) {
            return count / 10000 + "万";
        }
        return String.valueOf(count);
    }
}
