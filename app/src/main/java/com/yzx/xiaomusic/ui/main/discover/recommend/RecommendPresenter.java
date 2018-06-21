package com.yzx.xiaomusic.ui.main.discover.recommend;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;

/**
 * Created by yzx on 2018/6/21.
 * Description
 */
public class RecommendPresenter extends CommonBasePresenter<RecommendFragment, RecommendModel> {
    @Override
    protected RecommendModel getModel() {
        return new RecommendModel();
    }

    public void getSongSheet(int offset) {
        mView.showLoadingLayout();
        mModel.getSongSheet(offset, new CommonMvpObserver<SongSheetList>() {
            @Override
            protected void onSuccess(SongSheetList songSheetList) {
                mView.showSuccessLayout();
                mView.smartRefreshLayout.finishRefresh();
                mView.setData(songSheetList.getPlaylists());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
            }
        });
    }
}
