package com.yzx.xiaomusic.ui.main.discover.recommend;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.yzx.commonlibrary.base.BaseResposeBody;
import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by yzx on 2018/6/21.
 * Description
 */
public class RecommendPresenter extends CommonBasePresenter<RecommendFragment, RecommendModel> {
    @Override
    protected RecommendModel getModel() {
        return new RecommendModel();
    }


    public void getRecommend() {
        mModel.getRecommend(new SingleObserver<List<BaseResposeBody>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<BaseResposeBody> baseResposeBodies) {

                AVQuery<AVObject> avQuery = new AVQuery<>("Banner");
                avQuery.orderByDescending("createdAt");
                avQuery.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> banners, AVException e) {
                        if (e == null) {

                            BaseResposeBody baseResposeBody = baseResposeBodies.get(0);
                            BaseResposeBody baseResposeBody1 = baseResposeBodies.get(1);

                            if (baseResposeBody instanceof SongSheetList) {
                                mView.setData((SongSheetList) baseResposeBody, (LatestAlbumList) baseResposeBody1, banners);
                            } else {
                                mView.setData((SongSheetList) baseResposeBody1, (LatestAlbumList) baseResposeBody, banners);
                            }
                        } else {
                            e.printStackTrace();
                            onError(e);
                        }
                    }
                });

                mView.showSuccessLayout();
            }


            @Override
            public void onError(Throwable e) {
                mView.showErrorLayout();
            }
        });
    }
}
