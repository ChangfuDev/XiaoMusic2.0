package com.yzx.xiaomusic.ui.main.discover.recommend;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.SongSheetApi;

/**
 *
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class RecommendModel extends CommonBaseModel {
    public void getSongSheet(int offset, CommonMvpObserver<SongSheetList> observer) {
        getApiService(SongSheetApi.class)
                .getSongSheetList("全部","hot",offset, ApiConstant.LIMIT)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
