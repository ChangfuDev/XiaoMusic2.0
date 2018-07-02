package com.yzx.xiaomusic.ui.singer;

import android.annotation.SuppressLint;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.SingerTopInfo;
import com.yzx.xiaomusic.model.entity.album.SingerAlbum;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.AlbumApi;
import com.yzx.xiaomusic.network.api.SingerApi;

/**
 * @author yzx
 */
public class SingerDetailModel extends CommonBaseModel {
    public void getTop50(String id, CommonMvpObserver<SingerTopInfo> observer) {
        getApiService(SingerApi.class)
                .getSingerTopInfo(ApiConstant.TYPE_SINGER, id)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }


    @SuppressLint("CheckResult")
    public void getSingerAlbum(String singerId, int offset, CommonMvpObserver<SingerAlbum> observer) {

        getApiService(AlbumApi.class)
                .getSingerAlbum(singerId, offset, ApiConstant.LIMIT)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
