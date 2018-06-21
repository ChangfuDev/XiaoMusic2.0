package com.yzx.xiaomusic.ui.play.lyric;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;

/**
 * Created by yzx on 2018/6/21.
 * Description
 */
public class LyricFragment extends BaseMvpFragment<LyricPresenter> {
    @Override
    protected LyricPresenter getPresenter() {
        return new LyricPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_lyric;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

    }
}
