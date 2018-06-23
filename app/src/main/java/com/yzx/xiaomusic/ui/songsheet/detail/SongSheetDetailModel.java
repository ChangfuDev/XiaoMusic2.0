package com.yzx.xiaomusic.ui.songsheet.detail;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetDetail;
import com.yzx.xiaomusic.network.api.SongSheetApi;

/**
 * Created by yzx on 2018/6/12.
 * Description
 */
public class SongSheetDetailModel extends CommonBaseModel {

    public void getSongSheetDetails(String id, CommonMvpObserver<SongSheetDetail> observer) {
        getApiService(SongSheetApi.class)
                .getSongSheetDetail(id)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
