package com.yzx.xiaomusic.utils;

import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yzx on 2018/5/17.
 * Description
 */
public class EventBusUtils {
    public static void post(MessageEvent event) {
        EventBus.getDefault().post(event);
    }

    public static void postSticky(MessageEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
