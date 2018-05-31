package com.yzx.commonlibrary.base.mvp;

/**
 * Created by yzx on 2018/4/3.
 * Description
 */
public interface ICommonBasePresenter<V extends ICommonBaseView> {
    void onCreate(V v);

    void onDestroy();
}
