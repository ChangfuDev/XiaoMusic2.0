package com.yzx.xiaomusic.widget.loadsir;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.yzx.xiaomusic.R;

/**
 * @author yzx
 */
public class LoadingCallback extends Callback {

    private AnimationDrawable animationDrawable;

    @Override
    protected int onCreateView() {
        return R.layout.layout_load_sir_loading;
    }

    @Override
    public void onAttach(Context context, View view) {
        super.onAttach(context, view);
        View loadingView = view.findViewById(R.id.iv_loading);
        loadingView.setBackgroundResource(R.drawable.loading);
        animationDrawable = (AnimationDrawable) loadingView.getBackground();
        animationDrawable.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }
}
