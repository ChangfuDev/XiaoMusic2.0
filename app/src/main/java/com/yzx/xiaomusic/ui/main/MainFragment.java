package com.yzx.xiaomusic.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.yzx.commonlibrary.base.CommonBaseFragment;
import com.yzx.commonlibrary.base.adapter.CommonBaseFragmentPagerAdapter;
import com.yzx.xiaomusic.R;

import java.util.ArrayList;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import butterknife.BindView;

public class MainFragment extends CommonBaseFragment implements ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        fragments = new ArrayList<>();
        fragments.add(new MusicFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new VideoFragment());
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        initToolbar();

        CommonBaseFragmentPagerAdapter pagerAdapter = new CommonBaseFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.setFragments(fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
        tl.addOnTabSelectedListener(this);
    }

    private void initToolbar() {

        activity = (MainActivity) getActivity();

        activity.setSupportActionBar(tb);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        tl.addTab(tl.newTab().setIcon(R.drawable.ic_music));
        tl.addTab(tl.newTab().setIcon(R.drawable.ic_discover));
        tl.addTab(tl.newTab().setIcon(R.drawable.ic_video));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
//                showToast(R.string.search);

                NavOptions options = new NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_bottom)
                        .setExitAnim(R.anim.slide_out_top)
                        .setPopEnterAnim(R.anim.slide_in_top)
                        .setPopExitAnim(R.anim.slide_out_bottom)
                        .build();

                Navigation.findNavController(viewPager).navigate(R.id.searchFragment, null, options);
                return true;
            case android.R.id.home:
                activity.drawerLayout.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tl.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
