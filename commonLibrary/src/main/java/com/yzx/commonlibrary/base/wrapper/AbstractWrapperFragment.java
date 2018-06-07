package com.yzx.commonlibrary.base.wrapper;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;

import com.yzx.commonlibrary.base.CommonLazyFragment;
import com.yzx.commonlibrary.base.mvp.ICommonBaseView;
import com.yzx.commonlibrary.utils.ToastUtils;

import static com.yzx.commonlibrary.utils.ResourceUtils.parseString;

/**
 * @author yzx
 * @date 2018/4/24
 * Description  封装了Toast，Loading的Fragment
 */
public abstract class AbstractWrapperFragment extends CommonLazyFragment implements ICommonBaseView {
    private ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        progressDialog = new ProgressDialog(context);
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
        showToast(parseString(getContext(), msg));
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void showToast(@StringRes int msg, int type) {
        showToast(parseString(getContext(), msg), type);
    }

    @Override
    public void showToast(String msg, int type) {
        ToastUtils.showToast(msg, type);
    }
}
