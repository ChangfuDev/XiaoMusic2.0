package com.yzx.xiaomusic.network.api;

import com.yzx.xiaomusic.model.entity.album.AlbumDetail;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.album.SingerAlbum;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yzx on 2018/6/12.
 * Description 歌单Api
 */
public interface AlbumApi {

    /**
     * 获取专辑详情
     * http://music.163.com/api/album/2457012?ext=true&id=2457012&offset=0&total=true&limit=10
     *
     * @return
     */
    @Headers({"Cookie: appver=1.5.0.75771", "Referer: http://music.163.com/"})
    @GET("http://music.163.com/api/album/{id}")
    Observable<AlbumDetail> getAlbumDetail(@Path("id") String id);

    /**
     * http://music.163.com/api/artist/albums/10557?offset=0&limit=3
     * 获取歌手专辑
     *
     * @return
     */
    @Headers({"Cookie: appver=1.5.0.75771", "Referer: http://music.163.com/"})
    @GET("http://music.163.com/api/artist/albums/{id}")
    Observable<SingerAlbum> getSingerAlbum(@Path("id") String id, @Query("offset") int offset, @Query("limit") int limit);


    @Headers({"Cookie: appver=1.5.0.75771", "Referer: http://music.163.com/"})
    @GET("http://music.163.com/api/album/new")
    Observable<LatestAlbumList> getLatestAlbum(@Query("area") String area, @Query("offset") int offset, @Query("limit") int limit);
}
