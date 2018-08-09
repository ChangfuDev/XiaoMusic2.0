package com.yzx.xiaomusic.ui.usercenter.music;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.model.entity.user.UserSongSheet;
import com.yzx.xiaomusic.widget.loadsir.ErrorCallback;
import com.yzx.xiaomusic.widget.loadsir.LoadingCallback;

public class UserCenterMusicPresenter extends CommonBasePresenter<UserCenterMusicFragment, UserCenterMusicModel> {


    @Override
    protected UserCenterMusicModel getModel() {
        return new UserCenterMusicModel();
    }

    public void getUserInfo(int offset, String limit, String uid) {

        if (offset == 0) {
            mView.loadService.showCallback(LoadingCallback.class);
        }
        mModel.getUserInfo(String.valueOf(offset), limit, uid, new CommonMvpObserver<UserSongSheet>() {
            @Override
            protected void onSuccess(UserSongSheet userSongSheet) {
                if (offset == 0) {
                    mView.loadService.showSuccess();
                } else {
                    if (userSongSheet.isMore()){
                        mView.smartRefreshLayout.finishLoadMore(0);
                    }else {
                        mView.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
                mView.setData(userSongSheet);
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                if (offset == 0) {
                    mView.loadService.showCallback(ErrorCallback.class);
                } else {
                    mView.smartRefreshLayout.finishLoadMore(false);
                }
                mView.showToast(errorMsg, ToastUtils.TYPE_FAIL);
            }
        });
    }
}
