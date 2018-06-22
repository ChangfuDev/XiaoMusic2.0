package com.yzx.xiaomusic.ui.play.lyric;


import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.xiaomusic.widget.lyric.LrcHelper;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class LyricPresenter extends CommonBasePresenter<LyricFragment, LyricModel> {
    @Override
    protected LyricModel getModel() {
        return new LyricModel();
    }

    public void getLrc(String musicId) {
        mModel.getLrc(musicId, new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mView.lrcView.setLrcData(LrcHelper.parseLrcFromFile(new File(s)));
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("获取歌词失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
