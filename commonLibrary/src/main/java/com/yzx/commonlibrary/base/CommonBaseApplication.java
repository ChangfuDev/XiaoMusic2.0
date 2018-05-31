package com.yzx.commonlibrary.base;

import android.app.Application;
import android.content.Context;

import com.yzx.commonlibrary.network.AppHttpClient;

/**
 *
 * @author yzx
 * @date 2018/4/24
 * Description
 */
public abstract class CommonBaseApplication extends Application {

    public static Context context;
    private static String baseUrl;

    @Override
    public void onCreate() {
        super.onCreate();
        baseUrl = initBaseUrl();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public static AppHttpClient getAppHttpClient() {
        return new AppHttpClient.Builder().context(context).baseUrl(baseUrl).build();
    }

    public abstract String initBaseUrl();
}
