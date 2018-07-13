package com.yzx.xiaomusic.ui.comment.music;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.comment.MusicComment;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.MusicApi;

import static com.yzx.xiaomusic.network.ApiConstant.LIMIT;

/**
 * Created by yzx on 2018/7/12.
 * Description
 */
public class MusicCommentModel extends CommonBaseModel {
    public void getComment(String id, int offset, CommonMvpObserver<MusicComment> observer) {

        getApiService(MusicApi.class)
                .getMusicComment(ApiConstant.TYPE_COMMENT, LIMIT, offset, id)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
