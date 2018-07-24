package com.yzx.xiaomusic.network.api;

import com.yzx.xiaomusic.model.entity.Lyric;
import com.yzx.xiaomusic.model.entity.MusicAddress;
import com.yzx.xiaomusic.model.entity.comment.MusicComment;
import com.yzx.xiaomusic.model.entity.mv.MvInfo;

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

    /**
     * 获取评论
     *
     * @param id
     * @return
     */
    @GET("https://api.imjad.cn/cloudmusic")
    Observable<MusicComment> getMusicComment(@Query("type") String type, @Query("limit") int limit, @Query("offset") int offset, @Query("id") String id);

    /**
     * 获取MV地址
     * https://api.imjad.cn/cloudmusic/?type=mv&id=5563801
     *
     * @param type
     * @param id
     * @return
     */
    @GET("https://api.imjad.cn/cloudmusic/")
    Observable<MvInfo> getMvAddress(@Query("type") String type, @Query("id") String id);
}
