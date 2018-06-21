package com.yzx.xiaomusic.ui.main.discover;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.yzx.commonlibrary.base.adapter.CommonBaseFragmentPagerAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.ui.main.discover.friend.FriendFragment;
import com.yzx.xiaomusic.ui.main.discover.radio.RadioFragment;
import com.yzx.xiaomusic.ui.main.discover.recommend.RecommendFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author yzx
 * 发现页面
 */
public class DiscoverFragment extends BaseFragment {
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private CommonBaseFragmentPagerAdapter adapter;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new FriendFragment());
        fragments.add(new RadioFragment());

        titles = new ArrayList<>();
        titles.add(ResourceUtils.parseString(getContext(), R.string.recommend));
        titles.add(ResourceUtils.parseString(getContext(), R.string.friend));
        titles.add(ResourceUtils.parseString(getContext(), R.string.radio));
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        viewPager.setOffscreenPageLimit(3);
        adapter = new CommonBaseFragmentPagerAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(titles);
        viewPager.setAdapter(adapter);
        tl.setupWithViewPager(viewPager);
    }
}
