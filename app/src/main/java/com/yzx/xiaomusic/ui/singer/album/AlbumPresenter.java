package com.yzx.xiaomusic.ui.singer.album;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.album.SingerAlbum;
import com.yzx.xiaomusic.ui.singer.SingerDetailModel;

import java.util.List;

/**
 * @author yzx
 * @date 2018/7/2
 * Description 歌手专辑
 */
public class AlbumPresenter extends CommonBasePresenter<AlbumFragment, SingerDetailModel> {

    @Override
    protected SingerDetailModel getModel() {
        return new SingerDetailModel();
    }


    public void getAlbums(int offset, String singerId) {

        //第一页
        if (offset == 0) {
            mView.showLoadingLayout();
        }
        mModel.getSingerAlbum(singerId, offset, new CommonMvpObserver<SingerAlbum>() {
            @Override
            protected void onSuccess(SingerAlbum singerAlbum) {
                if (offset == 0) {
                    mView.showSuccessLayout();
                }
                if (singerAlbum.isMore()) {
                    mView.smartRefreshLayout.finishLoadMore();
                } else {
                    mView.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                }

                List<SingerAlbum.HotAlbumsBean> hotAlbums = singerAlbum.getHotAlbums();
                if (hotAlbums != null && hotAlbums.size() > 0) {
                    mView.setData(hotAlbums);
                }
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                if (offset == 0) {
                    mView.showErrorLayout();
                }
            }
        });


    }
}
