package com.yzx.xiaomusic.ui.play.card;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class PlayCardFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_play_card;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

    }

    @OnClick({R.id.iv_like, R.id.iv_download, R.id.iv_evaluate, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                break;
            case R.id.iv_download:
                break;
            case R.id.iv_evaluate:
                break;
            case R.id.iv_more:
                break;
        }
    }
}
