package com.yzx.xiaomusic.ui.mv;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.mv.MvInfo;

/**
 * Created by yzx on 2018/7/23.
 * Description
 */
public class MvDetailsPresenter extends CommonBasePresenter<MvDetailsActivity, MvDetailsModel> {
    @Override
    protected MvDetailsModel getModel() {
        return new MvDetailsModel();
    }

    public void getMv(String mvId) {
//        mView.loadService.showCallback(LoadingCallback.class);
        mModel.getMv(mvId, new CommonMvpObserver<MvInfo>() {

            @Override
            protected void onSuccess(MvInfo mvInfo) {
//                mView.loadService.showSuccess();
                mView.setData(mvInfo);
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
//                mView.loadService.showCallback(ErrorCallback.class);
            }
        });
    }
}
