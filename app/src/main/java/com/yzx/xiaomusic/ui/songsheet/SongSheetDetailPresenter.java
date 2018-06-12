package com.yzx.xiaomusic.ui.songsheet;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetDetail;
import com.yzx.xiaomusic.widget.loadsir.ErrorCallback;
import com.yzx.xiaomusic.widget.loadsir.LoadingCallback;

/**
 * Created by yzx on 2018/6/12.
 * Description
 */
public class SongSheetDetailPresenter extends CommonBasePresenter<SongSheetDetailFragment, SongSheetDetailModel> {
    @Override
    protected SongSheetDetailModel getModel() {
        return new SongSheetDetailModel();
    }

    public void getSongSheetDetail(String id) {
        mView.loadService.showCallback(LoadingCallback.class);
        mModel.getSongSheetDetails(id, new CommonMvpObserver<SongSheetDetail>() {
            @Override
            protected void onSuccess(SongSheetDetail songSheetDetail) {
                mView.loadService.showSuccess();
                mView.setData(songSheetDetail);
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
