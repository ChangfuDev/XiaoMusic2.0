package com.yzx.xiaomusic.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.yzx.commonlibrary.base.adapter.CommonBaseFragmentPagerAdapter;

public class MainPagerAdapter extends CommonBaseFragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
