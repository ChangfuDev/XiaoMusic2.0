package com.yzx.xiaomusic.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
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
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        // 测试 SDK 是否正常工作的代码
        AVObject testObject = new AVObject("TestObject");
        testObject.put("words", "Hello World!");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Log.d("saved", "success!");
                } else {
                    Log.e("saved", "done: " + e.toString());
                }
            }
        });
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
