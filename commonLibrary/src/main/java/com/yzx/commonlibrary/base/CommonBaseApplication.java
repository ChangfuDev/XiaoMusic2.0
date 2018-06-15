package com.yzx.commonlibrary.base;

import android.app.Application;
import android.content.Context;

import com.yzx.commonlibrary.network.AppHttpClient;

import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;

/**
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
        // 建议在Application里初始化
        Fragmentation.builder()
                // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .debug(true)
//             ... // 更多查看wiki或demo
                .install();
    }

    public static Context getContext() {
        return context;
    }

    public static AppHttpClient getAppHttpClient() {
        return new AppHttpClient.Builder().context(context).baseUrl(baseUrl).build();
    }

    public abstract String initBaseUrl();
}
