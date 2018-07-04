package com.yzx.xiaomusic.ui.main.discover.recommend;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.network.api.AlbumApi;
import com.yzx.xiaomusic.network.api.SongSheetApi;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class RecommendModel extends CommonBaseModel {

    public void getSongSheet(CommonMvpObserver<SongSheetList> observer) {
        getApiService(SongSheetApi.class)
                .getSongSheetList("华语", "hot", 0, 6)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }

    public void getLatestAlbums(CommonMvpObserver<LatestAlbumList> observer) {
        getApiService(AlbumApi.class)
                .getLatestAlbum("ZH", 0, 6)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
