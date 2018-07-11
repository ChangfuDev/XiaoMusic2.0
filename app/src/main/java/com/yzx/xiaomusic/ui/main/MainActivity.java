package com.yzx.xiaomusic.ui.main;

import android.os.Bundle;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseActivity;
import com.yzx.xiaomusic.ui.notification.PlayNotification;

/**
 * @author yzx
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int initContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        loadRootFragment(R.id.fragmentContainer, new MainFragment());
    }

    @Override
    protected void onDestroy() {
        PlayNotification.dismiss();
        super.onDestroy();
    }
}
