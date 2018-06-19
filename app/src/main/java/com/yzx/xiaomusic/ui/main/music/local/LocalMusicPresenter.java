package com.yzx.xiaomusic.ui.main.music.local;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yzx on 2018/6/19.
 * Description
 */
public class LocalMusicPresenter extends CommonBasePresenter<LocalMusicFragment, LocalMusicModel> {
    @Override
    protected LocalMusicModel getModel() {
        return new LocalMusicModel();
    }

    public void getLocalMusics() {
        mView.showLoadingLayout();
        List<MusicInfo> musicInfos = new ArrayList<>();
        mModel.getLocalMusics(new Observer<MusicInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                mView.tvDes.animate().setDuration(300).translationY(0).start();
            }

            @Override
            public void onNext(MusicInfo musicInfo) {
                musicInfos.add(musicInfo);
                mView.tvDes.setText(String.format("%s - %s", musicInfo.getMusicName(), musicInfo.getSingerInfos().get(0).getSingerName()));
            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorLayout();
            }

            @Override
            public void onComplete() {
                mView.showSuccessLayout();
                mView.tvDes.animate().setDuration(300).translationY(-DensityUtils.dip2px(50)).start();
                mView.setData(musicInfos);
            }
        });
    }
}
