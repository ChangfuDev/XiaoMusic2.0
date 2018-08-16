package com.yzx.leancloud.utils;

import com.avos.avoscloud.AVUser;

/**
 * Created by yzx on 2018/8/16.
 * Description
 */
public class LeanUtils {
    public static boolean isLogin() {
        return AVUser.getCurrentUser() != null;
    }
}
