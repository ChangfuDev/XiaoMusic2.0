package com.yzx.xiaomusic.ui.main.discover.recommend;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;

import java.util.List;

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

    public void getBanner() {
        AVQuery<AVObject> avQuery = new AVQuery<>("Banner");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mView.setBannerData(list);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}
