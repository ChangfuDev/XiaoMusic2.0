package com.yzx.xiaomusic.ui.play.lyric;

import android.annotation.SuppressLint;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.Constant;
import com.yzx.xiaomusic.model.entity.Lyric;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.MusicApi;

import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.Observer;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class LyricModel extends CommonBaseModel {
    @SuppressLint("CheckResult")
    public void getLrc(String id, Observer<String> observer) {
        getApiService(MusicApi.class)
                .getMusicLyrics(ApiConstant.TYPE_LYRIC, id)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(new CommonMvpObserver<Lyric>() {
                    @Override
                    protected void onSuccess(Lyric lyric) {
                        if (lyric != null && lyric.getLrc() != null && lyric.getLrc().getLyric() != null) {
//
                            File appDir = new File(Constant.PATH_APP);
                            if (!appDir.exists()) {
                                appDir.mkdirs();
                            }
                            File lyricDir = new File(Constant.PATH_ABSOLUTE_CACHE_LYRIC);
                            if (!lyricDir.exists()) {
                                lyricDir.mkdirs();
                            }
                            File file = new File(Constant.PATH_ABSOLUTE_CACHE_LYRIC + "/" + id);
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                fileOutputStream.write(lyric.getLrc().getLyric().getBytes());
                                fileOutputStream.close();
                                observer.onNext(file.getAbsolutePath());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                observer.onError(ex);
                            }
                        } else {
                            observer.onError(new NullPointerException("have no lyric"));
                        }
                    }

                    @Override
                    protected void onFail(int code, String errorMsg) {
                        super.onFail(code, errorMsg);
                        observer.onError(new Exception(code + errorMsg));
                    }
                });
    }
}
