package com.yzx.leancloud.callback;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

import java.util.List;

/**
 * Created by yzx on 2018/8/15.
 * Description
 */
public interface OnFindCallBack<T extends AVObject> {
    void onSuccess(List<T> data);

    void onFail(AVException e);
}
