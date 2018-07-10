package com.yzx.xiaomusic.utils;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportHelper;

/**
 * @author yzx
 * @date 2018/7/4
 * Description
 */
public class FragmentStartUtils {


    private static final String TAG = "yglFragmentStartUtils";

    public static void startFragment(SupportFragment parentFragment, SupportFragment targetFragment) {
        startFragment(parentFragment, targetFragment, null);
    }

    /**
     * @param parentFragment
     * @param targetFragment
     * @param args
     */
    public static void startFragment(SupportFragment parentFragment, SupportFragment targetFragment, Bundle args) {
        if (parentFragment == null) {
            return;
        }
        if (args != null) {
            targetFragment.setArguments(args);
        }

        //如果开启的是自己
        if (TextUtils.equals(parentFragment.getClass().getSimpleName(), targetFragment.getClass().getSimpleName())) {
            parentFragment.startWithPop(targetFragment);
        } else {
            //否则
            SupportFragment fragment = SupportHelper.findFragment(parentFragment.getFragmentManager(), targetFragment.getClass());
            //如果已经存在，单例开启，走onNewBundle()方法
            if (fragment != null) {
                parentFragment.putNewBundle(args);
                parentFragment.start(targetFragment, ISupportFragment.SINGLETASK);
            } else {
                parentFragment.start(targetFragment);
            }
            Log.i(TAG, "startFragment: " + parentFragment.getClass().getSimpleName());
            Log.i(TAG, "startFragment: " + parentFragment.getClass());
        }
    }


}
