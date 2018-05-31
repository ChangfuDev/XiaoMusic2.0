package com.yzx.xiaomusic.base;

import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.yzx.commonlibrary.base.CommonBaseActivity;

/**
 * Created by yzx on 2018/5/15.
 * Description 做一些三方操作，例如友盟统计、内存泄漏检测等
 */
public abstract class ThirdPartActivity extends CommonBaseActivity {

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
