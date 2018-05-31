package com.yzx.commonlibrary.base.mvp;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.yzx.commonlibrary.R;
import com.yzx.commonlibrary.base.CommonBaseApplication;
import com.yzx.commonlibrary.base.BaseResposeBody;
import com.yzx.commonlibrary.utils.LogUtils;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.commonlibrary.utils.ToastUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yzx on 2017/11/27.
 */

public abstract class CommonMvpObserver<B extends BaseResposeBody> implements Observer<B> {

    public static final String TAG = "yglMvpObserver";

    public CommonMvpObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(B value) {

        if (200 != value.getCode()) {
            onFail(value.getCode(), ResourceUtils.parseString(CommonBaseApplication.getContext(),R.string.error_get_data));
        } else {
            onSuccess(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        onException(e);
    }

    @Override
    public void onComplete() {

    }

    /**
     * 成功回调
     *
     * @param b
     */
    protected abstract void onSuccess(B b);

    /**
     * 失败回调
     *
     * @param code
     * @param errorMsg
     */
    protected void onFail(int code, String errorMsg) {
        ToastUtils.showToast(errorMsg, ToastUtils.TYPE_FAIL);
        LogUtils.e(TAG, "onFail: code : " + code + "msg" + errorMsg);
    }

    private void onException(Throwable e) {
        LogUtils.e(TAG, "onException: " + e.toString());
        //   HTTP错误
        if (e instanceof HttpException) {
            onFail(-1, ResourceUtils.parseString(CommonBaseApplication.getContext(),R.string.error_net));
        }
        //   连接错误
        else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            onFail(-1, ResourceUtils.parseString(CommonBaseApplication.getContext(),R.string.error_connect));
        }
        //  连接超时
        else if (e instanceof InterruptedIOException) {
            onFail(-1, ResourceUtils.parseString(CommonBaseApplication.getContext(),R.string.error_connect_time_out));
        }
        //  解析错误
        else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            onFail(-1, ResourceUtils.parseString(CommonBaseApplication.getContext(),R.string.error_parse));
        }
        //未知错误
        else {
            onFail(-1, ResourceUtils.parseString(CommonBaseApplication.getContext(),R.string.error_else));
        }
    }
}
