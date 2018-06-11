package com.yzx.xiaomusic.ui.usercenter.music;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.model.entity.user.UserSongSheet;
import com.yzx.xiaomusic.network.api.UserApi;
import com.yzx.xiaomusic.widget.loadsir.ErrorCallback;
import com.yzx.xiaomusic.widget.loadsir.LoadingCallback;

public class UserCenterMusicPresenter extends CommonBasePresenter<UserCenterMusicFragment, UserCenterMusicModel> {


    @Override
    protected UserCenterMusicModel getModel() {
        return new UserCenterMusicModel();
    }

    public void getUserInfo(String offset, String limit, String uid) {

        mView.loadService.showCallback(LoadingCallback.class);
        mModel.getUserInfo(offset, limit, uid, new CommonMvpObserver<UserSongSheet>() {
            @Override
            protected void onSuccess(UserSongSheet userSongSheet) {
                mView.loadService.showSuccess();
                mView.setData(userSongSheet);
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                mView.loadService.showCallback(ErrorCallback.class);
                mView.showToast(errorMsg, ToastUtils.TYPE_FAIL);
            }
        });
    }
}
