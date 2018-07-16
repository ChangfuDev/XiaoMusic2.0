package com.yzx.xiaomusic.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;
import com.kingja.loadsir.core.LoadSir;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.yzx.commonlibrary.base.CommonBaseApplication;
import com.yzx.xiaomusic.cache.CacheManager;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.notification.PlayNotification;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.widget.loadsir.EmptyCallback;
import com.yzx.xiaomusic.widget.loadsir.ErrorCallback;
import com.yzx.xiaomusic.widget.loadsir.LoadingCallback;

import static com.yzx.xiaomusic.Constant.BASE_URL;
import static com.yzx.xiaomusic.Constant.KEY_BUGLY;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_SERVICE_CREATED;

/**
 * Created by yzx on 2018/5/14.
 * Description
 */
public class MusicApplication extends CommonBaseApplication {

    {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    static {
        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
//            //全局设置主题颜色
//            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
//            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//            return new MaterialHeader(context);
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
//            //指定为经典Footer，默认是 BallPulseFooter
//            return new MusicFooter(context);
//        });
    }

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        MusicApplication application = (MusicApplication) context.getApplicationContext();
        return application.refWatcher;
    }
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(context);
//        Multidex.install(this);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"CghDjwlOTN96qvIL1XFcamD3-gzGzoHsz","sIqdXCfJFgilIp0bor4g32K9");
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);
        //初始化友盟
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //初始化崩溃日志
        CrashReport.initCrashReport(getApplicationContext(), KEY_BUGLY, true);
        //初始化LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
        //初始化状态页
        LoadSir.beginBuilder()
                //添加各种状态页
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                //设置默认状态页
                .setDefaultCallback(LoadingCallback.class)
                .commit();

        CacheManager.getCacheManager().init();
        //绑定服务
        Intent serviceIntent = new Intent(this, MusicService.class);
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //获取服务
                MusicService.MusicBinder musicBinder = (MusicService.MusicBinder) service;
                //设置服务管理类
                ServiceManager.getInstance().setService(musicBinder.getService());
                EventBusUtils.post(new MessageEvent(TYPE_SERVICE_CREATED));
                Log.i("ygl", "onServiceConnected: ");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                PlayNotification.dismiss();
            }
        };
        bindService(serviceIntent, conn, Service.BIND_AUTO_CREATE);
    }

    @Override
    public String initBaseUrl() {
        return BASE_URL;
    }
}
