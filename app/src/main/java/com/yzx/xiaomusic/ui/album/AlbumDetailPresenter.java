package com.yzx.xiaomusic.ui.album;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.album.AlbumDetail;
import com.yzx.xiaomusic.widget.loadsir.ErrorCallback;
import com.yzx.xiaomusic.widget.loadsir.LoadingCallback;

/**
 *
 * @author yzx
 * @date 2018/6/12
 * Description
 */
public class AlbumDetailPresenter extends CommonBasePresenter<AlbumDetailFragment, AlbumDetailModel> {

    @Override
    protected AlbumDetailModel getModel() {
        return new AlbumDetailModel();
    }

    public void getAlbumDetail(String id) {
        mView.loadService.showCallback(LoadingCallback.class);
        mModel.getAlbumDetail(id, new CommonMvpObserver<AlbumDetail>() {
            @Override
            protected void onSuccess(AlbumDetail albumDetail) {
                mView.loadService.showSuccess();
                mView.setData(albumDetail);
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                mView.loadService.showCallback(ErrorCallback.class);
                mView.showToast(errorMsg);
            }
        });
    }
}
