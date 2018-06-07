package com.yzx.xiaomusic.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.yzx.commonlibrary.base.mvp.CommonBaseMvpFragment;
import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;

public abstract class BaseFragment<T extends CommonBasePresenter> extends CommonBaseMvpFragment<T> {

    public LoadService loadService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        loadService = LoadSir.getDefault().register(view, (Callback.OnReloadListener) v -> reload(v));
        return loadService.getLoadLayout();
    }

    /**
     * 重试
     *
     * @param v
     */
    public void reload(View v) {

    }
}
