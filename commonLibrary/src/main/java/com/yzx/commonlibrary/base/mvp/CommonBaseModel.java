package com.yzx.commonlibrary.base.mvp;


import com.yzx.commonlibrary.base.CommonBaseApplication;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author yzx
 * @date 2018/4/3
 * Description  BaseModel
 */
public class CommonBaseModel implements ICommonBaseModel {

    public CompositeDisposable mDisposable = new CompositeDisposable();


    public <T> T getApiService(Class<T> t) {
        return CommonBaseApplication.getAppHttpClient().getService(t);
    }

    public void cancleNet() {
        onDestroy();
    }

    @Override
    public void onDestroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable.clear();
        }
    }
}
