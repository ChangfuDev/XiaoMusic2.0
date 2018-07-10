package com.yzx.xiaomusic.ui.songsheet.list;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;

/**
 * Created by yzx on 2018/6/23.
 * Description
 */
public class SongSheetListPresenter extends CommonBasePresenter<SongSheetListFragment, SongSheetListModel> {
    @Override
    protected SongSheetListModel getModel() {
        return new SongSheetListModel();
    }

    public void getSongSheet(int offset) {
        if (offset == 0) {
            mView.showLoadingLayout();
        }
        mModel.getSongSheet(offset, new CommonMvpObserver<SongSheetList>() {
            @Override
            protected void onSuccess(SongSheetList songSheetList) {
                if (offset == 0) {
                    mView.showSuccessLayout();
                } else {
                    if (songSheetList.isMore()) {
                        mView.smartRefreshLayout.finishLoadMore();
                    } else {
                        mView.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
                mView.setData(songSheetList.getPlaylists());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                if (offset == 0) {
                    mView.showErrorLayout();
                } else {
                    mView.smartRefreshLayout.finishLoadMore(false);
                }
            }
        });
    }
}
