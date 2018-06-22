package com.yzx.xiaomusic.network.api;

import com.yzx.xiaomusic.model.entity.Lyric;
import com.yzx.xiaomusic.model.entity.MusicAddress;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yzx on 2018/6/20.
 * Description
 */
public interface MusicApi {
    /**
     * 获取歌曲地址
     *
     * @return
     */
//    @Header("")
    @POST("http://music.163.com/weapi/song/enhance/player/url?csrf_token=")
    Observable<MusicAddress> getMusicAddress(@Body com.yzx.xiaomusic.model.entity.Body body);

    /**
     * 获取歌曲地址
     *
     * @param type
     * @param id
     * @param br
     * @return
     */
    @GET("https://api.imjad.cn/cloudmusic")
    Observable<MusicAddress> getMusicAddress(@Query("type") String type, @Query("id") String id, @Query("br") String br);

    /**
     * 获取歌词
     *
     * @param id
     * @return
     */
    @GET("https://api.imjad.cn/cloudmusic")
    Observable<Lyric> getMusicLyrics(@Query("type") String type, @Query("id") String id);
}
