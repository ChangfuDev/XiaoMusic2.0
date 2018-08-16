package com.yzx.xiaomusic.leancloud;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

/**
 * Created by yzx on 2018/8/13.
 * Description
 */
public abstract class SimpleSaveCallBack extends SaveCallback implements OnSaveCallBack {
    @Override
    public void done(AVException e) {

        if (e == null) {
            onSuccess();
        } else {
            onFail(e);
        }
    }
}
