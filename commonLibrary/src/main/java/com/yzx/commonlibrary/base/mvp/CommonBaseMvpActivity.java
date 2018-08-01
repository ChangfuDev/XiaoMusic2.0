package com.yzx.commonlibrary.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yzx.commonlibrary.base.CommonBaseActivity;

import static com.yzx.commonlibrary.utils.CommonUtils.checkNotNull;

/**
 * @author yzx
 * @date 2018/4/24
 * Description  MvpActivity
 */
public abstract class CommonBaseMvpActivity<P extends CommonBasePresenter> extends CommonBaseActivity {

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = getPresenter();
        checkNotNull(mPresenter, "mPresenter == null");
        mPresenter.onCreate(this);
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract P getPresenter();


}
