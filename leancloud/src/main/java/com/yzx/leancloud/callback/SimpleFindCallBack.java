package com.yzx.leancloud.callback;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.FindCallback;

import java.util.List;

/**
 * @author yzx
 * @date 2018/8/15
 * Description 查询回调
 */
public abstract class SimpleFindCallBack<T extends AVObject> extends FindCallback<T> implements OnFindCallBack<T> {
    @Override
    public void done(List<T> list, AVException e) {
        if (e == null) {
            onSuccess(list);
        } else {
            onFail(e);
        }
    }

}
