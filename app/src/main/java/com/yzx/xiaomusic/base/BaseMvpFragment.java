package com.yzx.xiaomusic.base;

import android.os.Bundle;

import android.support.annotation.Nullable;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;


import static com.yzx.commonlibrary.utils.CommonUtils.checkNotNull;

public abstract class BaseMvpFragment<P extends CommonBasePresenter> extends BaseFragment {


    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        checkNotNull(mPresenter, "mPresenter == null");
        mPresenter.onCreate(this);
    }

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract P getPresenter();
}
