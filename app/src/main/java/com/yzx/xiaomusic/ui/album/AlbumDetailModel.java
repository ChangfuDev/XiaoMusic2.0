package com.yzx.xiaomusic.ui.album;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.album.AlbumDetail;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.AlbumApi;

/**
 *
 * @author yzx
 * @date 2018/6/12
 * Description  专辑详情
 */
public class AlbumDetailModel extends CommonBaseModel {

    public void getAlbumDetail(String id, CommonMvpObserver<AlbumDetail> observer) {

        getApiService(AlbumApi.class)
                .getAlbumDetail(ApiConstant.TYPE_ALBUM,id)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
