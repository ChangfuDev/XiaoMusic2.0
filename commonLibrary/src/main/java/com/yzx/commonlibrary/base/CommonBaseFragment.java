package com.yzx.commonlibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzx.commonlibrary.base.wrapper.AbstractWrapperFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yzx on 2018/4/24.
 * Description fragment的基类
 */
public abstract class CommonBaseFragment extends AbstractWrapperFragment {

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initBeforeSetContentView(savedInstanceState);
        View rootView = inflater.inflate(initContentViewId(), container, false);
        bind = ButterKnife.bind(this, rootView);
        initData(savedInstanceState);
        initView(inflater, savedInstanceState);
        return rootView;
    }

    /**
     * setContentView()之前做操作
     *
     * @param savedInstanceState
     */
    protected void initBeforeSetContentView(Bundle savedInstanceState) {

    }

    /**
     * 设置ContentViewId()
     *
     * @return
     */
    protected abstract int initContentViewId();

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected void initData(Bundle savedInstanceState) {

    }

    /**
     * 初始化View
     *
     * @param inflater
     * @param savedInstanceState
     */
    protected abstract void initView(LayoutInflater inflater, Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        bind.unbind();
        super.onDestroyView();
    }
}
