package com.yzx.commonlibrary.base.wrapper;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;

import com.yzx.commonlibrary.base.mvp.ICommonBaseView;
import com.yzx.commonlibrary.utils.ToastUtils;

import static com.yzx.commonlibrary.utils.ResourceUtils.parseString;

/**
 * @author yzx
 * @date 2018/4/24
 * Description  封装了Toast，Loading的Activity
 */
public abstract class AbstractWrapperActivity extends AppCompatActivity implements ICommonBaseView {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    public void showLoading() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(@StringRes int msg) {
        showToast(parseString(this, msg));
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void showToast(@StringRes int msg, int type) {
        showToast(parseString(this, msg), type);
    }

    @Override
    public void showToast(String msg, int type) {
        ToastUtils.showToast(msg, type);
    }
}
