package com.yzx.xiaomusic.ui.main.discover.recommend;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
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


    public void getSongSheet() {
        mView.showLoadingLayout();
        mModel.getSongSheet(new CommonMvpObserver<SongSheetList>() {

            @Override
            protected void onSuccess(SongSheetList songSheetList) {
                mView.showSuccessLayout();
                mView.setSongSheetData(songSheetList.getPlaylists());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                mView.showErrorLayout();
            }
        });
    }

    public void getLatestAlbums() {
        mModel.getLatestAlbums(new CommonMvpObserver<LatestAlbumList>() {
            @Override
            protected void onSuccess(LatestAlbumList latestAlbumList) {
                mView.showSuccessLayout();
                mView.setAlbumData(latestAlbumList.getAlbums());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                mView.showErrorLayout();
            }
        });
    }
}
