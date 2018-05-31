package com.yzx.xiaomusic.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yzx.xiaomusic.R;

/**
 * Created by yzx on 2018/5/31.
 * Description
 */
public class GlideUtils {

    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_HEAD = 1;

    @SuppressLint("CheckResult")
    public static void loadImg(Context context, Object resource, ImageView imageView) {
        loadImg(context, resource, TYPE_DEFAULT, imageView);
    }

    @SuppressLint("CheckResult")
    public static void loadImg(Context context, Object resource, int type, ImageView imageView) {

        int placeholder;
        RequestOptions options = new RequestOptions();
        switch (type) {
            case TYPE_HEAD:
                placeholder = R.drawable.header_default;
                break;
            default:
                placeholder = R.drawable.zhanweitu;
                break;
        }
        options.placeholder(placeholder).error(placeholder);

        Glide.with(context).load(resource).apply(options).into(imageView);
    }
}
