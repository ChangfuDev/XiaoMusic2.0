package com.yzx.commonlibrary.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yzx.commonlibrary.base.CommonBaseFragment;

import static com.yzx.commonlibrary.utils.CommonUtils.checkNotNull;

/**
 * @author yzx
 * @date 2018/4/24
 * Description  mvpFragment
 */
public abstract class CommonBaseMvpFragment<P extends CommonBasePresenter> extends CommonBaseFragment {
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
