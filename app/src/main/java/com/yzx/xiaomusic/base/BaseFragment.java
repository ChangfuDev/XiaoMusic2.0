package com.yzx.xiaomusic.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.yzx.commonlibrary.base.CommonBaseFragment;
import com.yzx.commonlibrary.utils.LogUtils;
import com.yzx.xiaomusic.ui.main.MainActivity;
import com.yzx.xiaomusic.ui.main.MainFragment;

import androidx.navigation.NavOptions;
import me.yokeyword.fragmentation.SupportFragment;

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


    public void setToolBar(Toolbar toolbar) {
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void easyStart(SupportFragment fragment) {
        easyStart(fragment, null);
    }

    public void easyParentStart(SupportFragment fragment) {
        easyParentStart(fragment, null);
    }

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
     * 重试
     *
     * @param v
     */
    public void reload(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            pop();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
