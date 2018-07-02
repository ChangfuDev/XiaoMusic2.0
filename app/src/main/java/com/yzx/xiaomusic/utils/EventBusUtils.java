package com.yzx.xiaomusic.utils;

import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yzx on 2018/5/17.
 * Description
 */
public class EventBusUtils {
    public static void post(MessageEvent event) {
        getDefault().post(event);
    }

    public static void postSticky(MessageEvent event) {
        getDefault().postSticky(event);
    }

    /**
     * 注册EventBus
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        if (getDefault().isRegistered(subscriber)) {
            return;
        }
        getDefault().register(subscriber);
    }

    /**
     * 注销EventBus
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        getDefault().unregister(subscriber);
    }


    private static EventBus getDefault() {
        return EventBus.getDefault();
    }


}
