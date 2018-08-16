package com.yzx.xiaomusic.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.yzx.commonlibrary.base.adapter.CommonBaseFragmentPagerAdapter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.widget.tab.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yzx
 * @date 2018/8/15
 * Description
 */
public class LoginAndRegisterFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    CommonTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ArrayList<CustomTabEntity> tabEntities;
    private CommonBaseFragmentPagerAdapter adapter;
    private ArrayList<Fragment> fragments;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_login_and_register;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("登录"));
        tabEntities.add(new TabEntity("注册"));

        fragments = new ArrayList<>();
        fragments.add(new LoginFragment());
        fragments.add(new RegisterFagment());
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        initToolBar(tb);
        tvTitle.setText(R.string.login);
        adapter = new CommonBaseFragmentPagerAdapter(getChildFragmentManager());
        setUpViewPager(viewPager, tabLayout, adapter, fragments, tabEntities);
    }

    @OnClick({R.id.iv_weixin, R.id.iv_qq, R.id.iv_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_weixin:
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weibo:
                break;
        }
    }

}
