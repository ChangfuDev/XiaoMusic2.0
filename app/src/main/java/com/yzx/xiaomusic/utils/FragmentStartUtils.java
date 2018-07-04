package com.yzx.xiaomusic.utils;

import android.os.Bundle;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by yzx on 2018/7/4.
 * Description
 */
public class FragmentStartUtils {


    public static void startFragment(SupportFragment parentFragment, SupportFragment targetFragment) {
        startFragment(parentFragment, targetFragment, null);
    }

    /**
     * @param parentFragment
     * @param targetFragment
     * @param args
     */
    public static void startFragment(SupportFragment parentFragment, SupportFragment targetFragment, Bundle args) {
        if (args != null) {
            targetFragment.setArguments(args);
        }
        parentFragment.start(targetFragment);
    }


}
