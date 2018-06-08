package com.yzx.xiaomusic.ui.singer.top;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.ui.adapter.MusicAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Top50Fragment extends BaseMvpFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

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

        recyclerView.setAdapter(new MusicAdapter());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
