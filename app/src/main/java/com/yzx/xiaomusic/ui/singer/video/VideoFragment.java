package com.yzx.xiaomusic.ui.singer.video;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.ui.singer.top.Top50Presenter;

public class VideoFragment extends BaseMvpFragment {
    @Override
    protected CommonBasePresenter getPresenter() {
        return new Top50Presenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_detail_singer_video;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

    }
}
