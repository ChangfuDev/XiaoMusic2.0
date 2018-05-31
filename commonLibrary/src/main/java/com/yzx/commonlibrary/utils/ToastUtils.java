package com.yzx.commonlibrary.utils;

import android.annotation.SuppressLint;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yzx.commonlibrary.R;
import com.yzx.commonlibrary.base.CommonBaseApplication;


/**
 * @author yzx
 * @date 2017/12/4
 */

public class ToastUtils {


    private static Toast toast;
    public static final int TYPE_SUCCESS = 1;
    public static final int TYPE_FAIL = 2;
    public static final int TYPE_NOTICE = 3;
    public static final int TYPE_DEFALUT = 4;

    public static void showToast(@StringRes int msg) {
        showToast(msg, TYPE_DEFALUT);
    }

    public static void showToast(String msg) {
        showToast(msg, TYPE_DEFALUT);
    }

    public static void showToast(String msg, int type) {
        showToast(msg, type, Toast.LENGTH_SHORT);
    }

    public static void showToast(@StringRes int msg, int type) {
        showToast(msg, type, Toast.LENGTH_SHORT);
    }

    public static void showToast(@StringRes int msg, int type, int duration) {
        showToast(ResourceUtils.parseString(CommonBaseApplication.getContext(), msg), type, duration);
    }

    @SuppressLint("ShowToast")
    public static void showToast(String msg, int type, int duration) {
        if (toast == null) {
            toast = Toast.makeText(CommonBaseApplication.getContext(), msg, Toast.LENGTH_SHORT);
        }

        View toastView = LayoutInflater.from(CommonBaseApplication.getContext()).inflate(R.layout.layout_toast, null);
        ImageView ivToastType = (ImageView) toastView.findViewById(R.id.iv_toast_type);
        TextView ivToastMsg = (TextView) toastView.findViewById(R.id.iv_toast_msg);
        ivToastMsg.setText(msg);
        switch (type) {
            //成功
            case TYPE_SUCCESS:
                ivToastType.setVisibility(View.VISIBLE);
                ivToastType.setImageResource(R.drawable.ic_successful);
                toast.setView(toastView);
                break;
            //失败
            case TYPE_FAIL:
                ivToastType.setVisibility(View.VISIBLE);
                ivToastType.setImageResource(R.drawable.ic_error);
                toast.setView(toastView);
                break;
            //提示
            case TYPE_NOTICE:
                ivToastType.setVisibility(View.VISIBLE);
                ivToastType.setImageResource(R.drawable.ic_notice);
                toast.setView(toastView);
                break;
            //默认
            case TYPE_DEFALUT:
                ivToastType.setVisibility(View.GONE);
                toast.setView(toastView);
                break;
            default:
                break;
        }

        switch (duration) {
            case Toast.LENGTH_SHORT:
                toast.setDuration(Toast.LENGTH_SHORT);
                break;
            case Toast.LENGTH_LONG:
                toast.setDuration(Toast.LENGTH_LONG);
                break;
            default:
                break;
        }

        toast.show();
    }

}
