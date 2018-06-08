package com.yzx.xiaomusic.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.yzx.commonlibrary.base.CommonBaseFragment;
import com.yzx.xiaomusic.R;

import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

public abstract class BaseFragment extends CommonBaseFragment {

    public LoadService loadService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        loadService = LoadSir.getDefault().register(view, (Callback.OnReloadListener) v -> reload(v));
        loadService.showSuccess();
        return loadService.getLoadLayout();
    }

    public void navigate(@IdRes int resId) {

        navigate(resId, null);
    }

    public void navigate(@IdRes int resId, Bundle bundle) {

        NavOptions navOptions = new NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_bottom)
                .setExitAnim(R.anim.slide_out_top)
                .setPopEnterAnim(R.anim.slide_in_top)
                .setPopExitAnim(R.anim.slide_out_bottom)
                .build();
        navigate(resId, bundle, navOptions);
    }

    public void navigate(@IdRes int resId, Bundle bundle, NavOptions navOptions) {

        NavHostFragment.findNavController(this).navigate(resId, bundle, navOptions);
    }

    /**
     * 重试
     *
     * @param v
     */
    public void reload(View v) {

    }
}
