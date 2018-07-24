package com.yzx.commonlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yzx.commonlibrary.base.wrapper.AbstractWrapperActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author yzx
 * @date 2018/4/24
 * Description  Activity基类
 */
public abstract class CommonBaseActivity extends AbstractWrapperActivity {

    public String TAG = "ygl" + this.getClass().getSimpleName();
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeSetContentView(savedInstanceState);
        setContentView(initContentViewId());
        bind = ButterKnife.bind(this);
        initData(savedInstanceState);
        initView(savedInstanceState);
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
    public void initData(Bundle savedInstanceState) {

    }

    /**
     * 初始化View
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
