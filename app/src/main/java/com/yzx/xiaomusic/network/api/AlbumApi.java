package com.yzx.xiaomusic.network.api;

import com.yzx.xiaomusic.model.entity.album.AlbumDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yzx on 2018/6/12.
 * Description 歌单Api
 */
public interface AlbumApi {

    /**
     * 获取专辑详情
     *
     * @param type
     * @param id
     * @return
     */
    @GET("/cloudmusic/")
    Observable<AlbumDetail> getAlbumDetail(@Query("type") String type, @Query("id") String id);
}
