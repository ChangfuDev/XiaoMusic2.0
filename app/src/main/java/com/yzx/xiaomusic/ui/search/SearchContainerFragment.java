package com.yzx.xiaomusic.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;

/**
 * Created by yzx on 2018/6/13.
 * Description
 */
public class SearchContainerFragment extends BaseFragment {
    @Override
    protected int initContentViewId() {
        return R.layout.fragment_container;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        loadRootFragment(R.id.fragmentContainer, new SearchFragment());
    }
}
