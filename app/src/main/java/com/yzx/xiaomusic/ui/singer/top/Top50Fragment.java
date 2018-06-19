package com.yzx.xiaomusic.ui.singer.top;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.ui.adapter.MusicAdapter;

import butterknife.BindView;

public class Top50Fragment extends BaseMvpFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected CommonBasePresenter getPresenter() {
        return new Top50Presenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_detail_singer_top50;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        recyclerView.setAdapter(new MusicAdapter(getFragmentManager()));
    }
}
