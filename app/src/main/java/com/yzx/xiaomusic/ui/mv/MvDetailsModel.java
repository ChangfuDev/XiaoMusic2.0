package com.yzx.xiaomusic.ui.mv;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.mv.MvInfo;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.MusicApi;

/**
 * Created by yzx on 2018/7/23.
 * Description
 */
public class MvDetailsModel extends CommonBaseModel {


    public void getMv(String mvId, CommonMvpObserver<MvInfo> observer) {
        getApiService(MusicApi.class)
                .getMvAddress(ApiConstant.TYPE_MV,mvId)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
