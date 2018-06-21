package com.yzx.xiaomusic.network.api;

import com.yzx.xiaomusic.model.entity.songsheet.SongSheetDetail;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yzx on 2018/6/12.
 * Description 歌单Api
 */
public interface SongSheetApi {
    /**
     * 获取歌单详情
     *
     * @param id
     * @return
     */
    @GET("http://music.163.com/api/playlist/detail/")
    Observable<SongSheetDetail> getSongSheetDetail(@Query("id") String id);


    /**
     * 获取歌单
     *
     * @param cat
     * @param order
     * @param offset
     * @param limit
     * @return
     */
    @GET("http://music.163.com/api/playlist/list")
    Observable<SongSheetList> getSongSheetList(@Query("cat") String cat, @Query("order") String order, @Query("offset") int offset, @Query("limit") int limit);
}
