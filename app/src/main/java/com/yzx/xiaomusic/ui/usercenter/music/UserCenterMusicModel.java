package com.yzx.xiaomusic.ui.usercenter.music;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.user.UserSongSheet;
import com.yzx.xiaomusic.network.api.UserApi;

public class UserCenterMusicModel extends CommonBaseModel {


    public void getUserInfo(String offset, String limit, String uid, CommonMvpObserver<UserSongSheet> observer) {

        getApiService(UserApi.class)
                .getUserInfo(offset, limit, uid)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
