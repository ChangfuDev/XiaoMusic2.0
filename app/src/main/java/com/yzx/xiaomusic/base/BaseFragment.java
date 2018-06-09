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
import com.yzx.commonlibrary.base.CommonBaseFragment;
import com.yzx.commonlibrary.utils.LogUtils;
import com.yzx.xiaomusic.ui.main.MainFragment;

import androidx.navigation.NavOptions;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment extends CommonBaseFragment {

    public LoadService loadService;
    private NavOptions navOptions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        loadService = LoadSir.getDefault().register(view, (Callback.OnReloadListener) v -> reload(v));
        loadService.showSuccess();

        LogUtils.d(this.getClass().getSimpleName(), "onCreateView");
        return loadService.getLoadLayout();
    }


    public void easyStart(SupportFragment fragment) {
        easyStart(fragment, null);
    }

    public void easyStart(SupportFragment fragment, Bundle args) {
        if (args != null) {
            fragment.setArguments(args);
        }

        if (this instanceof MainFragment) {
            start(fragment);
        } else {
            SupportFragment parentFragment = (SupportFragment) this.getParentFragment();
            parentFragment.start(fragment);
        }
    }

    /**
     * 重试
     *
     * @param v
     */
    public void reload(View v) {

    }
}
