package com.yzx.xiaomusic.ui.songsheet.list;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.SongSheetApi;

/**
 * Created by yzx on 2018/6/23.
 * Description
 */
public class SongSheetListModel extends CommonBaseModel {

    public void getSongSheet(int offset, CommonMvpObserver<SongSheetList> observer) {
        getApiService(SongSheetApi.class)
                .getSongSheetList("全部", "hot", offset, ApiConstant.LIMIT)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
