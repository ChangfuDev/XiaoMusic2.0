package com.yzx.commonlibrary.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yzx.commonlibrary.utils.LogUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yzx.commonlibrary.utils.CommonUtils.checkNotNull;

/**
 * app网络请求
 *
 * @author yzx
 * @date 2017/6/21
 */

public class AppHttpClient {

    private static final String TAG = "AppHttpClient";
    private static AppHttpClient mAppHttpClient;
    private Context context;
    private String baseUrl;
    private Retrofit mRetrofit;
    public static final int MAX_CACHE_SIZE = 10 * 1024 * 1024;
    public static final int MAX_AGE = 0;

    /**
     * 1天
     */
    public static final int MAX_STALE = 5 * 24 * 60 * 60;
    private Map<String, Object> serviceByType = new HashMap<>();


    private AppHttpClient(Builder builder) {

        context = builder.context;
        baseUrl = builder.baseUrl;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(builder.connectTimeout < 30 ? 30 : builder.connectTimeout, TimeUnit.SECONDS)
                //添加拦截器
                .addInterceptor(getInterceptor(context))
                //设置缓存
                .cache(getCache(context))
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 设置缓存
     *
     * @param context
     * @return
     */
    private Cache getCache(Context context) {

        final File cacheDir = new File(context.getExternalCacheDir(), "musicCache");
        return new Cache(cacheDir, MAX_CACHE_SIZE);
    }

    /**
     * 设置拦截器
     *
     * @param context
     * @return
     */
    private Interceptor getInterceptor(final Context context) {
        return chain -> {
            Request request = chain.request();
            if (!NetWorkUtils.isNetAvailable(context)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            //打印body和url
            RequestBody body = request.body();
            Buffer buffer = new Buffer();
            LogUtils.i(TAG, "intercept: url:" + request.url());
            if (body != null) {
                body.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    charset = contentType.charset(Charset.defaultCharset());
                }
                String paramsStr = null;
                if (charset != null) {
                    paramsStr = buffer.readString(charset);
                }

                LogUtils.i(TAG, "intercept: body:" + paramsStr);
            }

            Response originalResponse = chain.proceed(request);

            if (NetWorkUtils.isNetAvailable(context)) {
                return originalResponse.newBuilder().header("Cache-Control", "public,  max-age=" + MAX_AGE).build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + MAX_STALE).build();
            }
        };
    }

    public synchronized <T> T getService(Class<T> apiInterface) {
        String serviceName = apiInterface.getName();
        if (!Check.isNull(serviceByType.get(serviceName))) {
            return (T) serviceByType.get(serviceName);
        }
        T service = mRetrofit.create(apiInterface);
        serviceByType.put(serviceName, service);
        return service;
    }

    public static class Builder {

        private Context context;
        private String baseUrl;
        private long connectTimeout;


        public Builder() {
        }

        public Builder(AppHttpClient appHttpClient) {

            context = appHttpClient.context;
            baseUrl = appHttpClient.baseUrl;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public AppHttpClient build() {
            checkNotNull(context, "context == null");
            checkNotNull(baseUrl, "baseUrl == null");

            if (mAppHttpClient == null) {
                synchronized (AppHttpClient.class) {
                    if (mAppHttpClient == null) {
                        mAppHttpClient = new AppHttpClient(this);
                    }
                }
            }
            return mAppHttpClient;
        }
    }
}
