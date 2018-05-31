package com.yzx.commonlibrary.base.mvp;

import android.support.annotation.StringRes;

/**
 * Created by yzx on 2018/4/24.
 * Description
 */
public interface ICommonBaseView {

    void showLoading();

    void hideLoading();

    void showToast(@StringRes int msg, int type);
    void showToast(@StringRes int msg);
    void showToast(String msg, int type);
    void showToast(String msg);
}
