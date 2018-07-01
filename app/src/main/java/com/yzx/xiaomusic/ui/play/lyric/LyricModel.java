package com.yzx.xiaomusic.ui.play.lyric;

import android.annotation.SuppressLint;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;
import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.Constant;
import com.yzx.xiaomusic.cache.CacheManager;
import com.yzx.xiaomusic.cache.CacheUtils;
import com.yzx.xiaomusic.model.entity.Lyric;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.network.api.MusicApi;

import java.io.IOException;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
                            String lyricString = lyric.getLrc().getLyric();
                            DiskLruCache lyricCache = CacheManager.getCacheManager().getLyricCache();
                            if (lyricCache == null) {
                                return;
                            }
                            Observable.create((ObservableOnSubscribe<String>) e -> {
                                try {
                                    DiskLruCache.Editor editor = lyricCache.edit(id);
                                    if (editor != null) {
                                        OutputStream outputStream = editor.newOutputStream(0);
                                        if (CacheUtils.writeStringToStream(lyricString, outputStream)) {
                                            editor.commit();//提交
                                        } else {
                                            editor.abort();//重复操作
                                        }
                                    }
                                    observer.onNext(Constant.PATH_ABSOLUTE_CACHE_LYRIC + "/" + id + ".0");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    observer.onError(ex);
                                    Log.i(TAG, "cacheLyricFail: ");
                                }
                            })
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(s -> {
                                        observer.onComplete();
                                        Log.i(TAG, "cacheLyricSuccess: ");
                                    });
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
