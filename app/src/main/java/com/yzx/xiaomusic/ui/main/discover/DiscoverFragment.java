package com.yzx.xiaomusic.ui.main.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.yzx.commonlibrary.base.adapter.CommonBaseFragmentPagerAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.ui.main.discover.friend.FriendFragment;
import com.yzx.xiaomusic.ui.main.discover.radio.RadioFragment;
import com.yzx.xiaomusic.ui.main.discover.recommend.RecommendFragment;
import com.yzx.xiaomusic.widget.tab.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author yzx
 * 发现页面
 */
public class DiscoverFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    CommonTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private CommonBaseFragmentPagerAdapter adapter;
    private ArrayList<CustomTabEntity> tabEntities;

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

        tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity(ResourceUtils.parseString(getContext(), R.string.recommend)));
        tabEntities.add(new TabEntity(ResourceUtils.parseString(getContext(), R.string.friend)));
        tabEntities.add(new TabEntity(ResourceUtils.parseString(getContext(), R.string.radio)));
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        adapter = new CommonBaseFragmentPagerAdapter(getChildFragmentManager());
        setUpViewPager(viewPager, tabLayout, adapter, fragments, tabEntities);
    }


}
