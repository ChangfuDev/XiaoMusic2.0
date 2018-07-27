package com.yzx.xiaomusic.ui.main.discover.recommend;

import android.annotation.SuppressLint;

import com.yzx.commonlibrary.base.BaseResposeBody;
import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.network.api.AlbumApi;
import com.yzx.xiaomusic.network.api.SongSheetApi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;

import static io.reactivex.Observable.mergeArray;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class RecommendModel extends CommonBaseModel {


    @SuppressLint("CheckResult")
    public void getRecommend(SingleObserver<List<BaseResposeBody>> observer) {
        Observable<SongSheetList> sheetListObservable = getApiService(SongSheetApi.class)
                .getSongSheetList("华语", "hot", 0, 6);

        Observable<LatestAlbumList> latestAlbumListObservable = getApiService(AlbumApi.class)
                .getLatestAlbum("ZH", 0, 6);

        mergeArray(2, 2, sheetListObservable, latestAlbumListObservable)
                .compose(RxTransformer.switchSchedulers(this))
                .toList()
                .subscribe(observer);
    }
}
