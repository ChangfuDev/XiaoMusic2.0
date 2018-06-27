package com.yzx.xiaomusic.ui.singer;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.ui.singer.top.Top50Presenter;

/**
 * @author yzx
 */
public class AlbumFragment extends BaseMvpFragment {
    @Override
    protected CommonBasePresenter getPresenter() {
        return new Top50Presenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_detail_singer_album;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

    }
}
