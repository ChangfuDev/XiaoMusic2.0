package com.yzx.xiaomusic.network.api;

import com.yzx.xiaomusic.model.entity.SingerTopInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yzx on 2018/6/27.
 * Description
 */
public interface SingerApi {
    /**
     * 获取歌手top50和歌手基本信息
     *
     * @param type
     * @param id
     * @return
     */
    @GET("/cloudmusic/")
    Observable<SingerTopInfo> getSingerTopInfo(@Query("type") String type, @Query("id") String id);
}
