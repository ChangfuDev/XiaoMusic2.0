package com.yzx.xiaomusic.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.yzx.commonlibrary.base.CommonBaseFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author yzx
 */
public abstract class BaseFragment extends CommonBaseFragment {

    public LoadService loadService;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        loadService = LoadSir.getDefault().register(view, (Callback.OnReloadListener) v -> reload(v));
        loadService.showSuccess();

        return loadService.getLoadLayout();
    }

    public void initToolBar(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(v -> pop());
    }

    /**
     * 普通开启
     *
     * @param fragment
     */
    public void easyStart(SupportFragment fragment) {
        easyStart(fragment, null);
    }

    public void easyParentStart(SupportFragment fragment) {
        easyParentStart(fragment, null);
    }

    /**
     * 父Fragment开启，viewPager情况下
     *
     * @param fragment
     * @param args
     */
    public void easyParentStart(SupportFragment fragment, Bundle args) {
        if (args != null) {
            fragment.setArguments(args);
        }
        SupportFragment parentFragment = (SupportFragment) this.getParentFragment();
        parentFragment.start(fragment);
    }

    public void easyStart(SupportFragment fragment, Bundle args) {
        if (args != null) {
            fragment.setArguments(args);
        }
        start(fragment);
    }

    /**
     * 单例开启
     */
    public void singleStart(Class<SupportFragment> cls, SupportFragment fragment, Bundle args) {
        SupportFragment supportFragment = findFragment(cls);
        if (supportFragment == null) {
            easyStart(fragment, args);
        } else {
            fragment.putNewBundle(args);
            start(fragment, SINGLETASK);
        }
    }

    /**
     * 父Fragment单例开启
     */
    public void singParentleStart(Class<SupportFragment> cls, SupportFragment fragment, Bundle args) {
        SupportFragment supportFragment = findFragment(cls);
        if (supportFragment == null) {
            easyParentStart(fragment, args);
        } else {
            if (args != null) {
                fragment.setArguments(args);
            }
            SupportFragment parentFragment = (SupportFragment) this.getParentFragment();
            fragment.putNewBundle(args);
            parentFragment.start(fragment, SINGLETASK);
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
