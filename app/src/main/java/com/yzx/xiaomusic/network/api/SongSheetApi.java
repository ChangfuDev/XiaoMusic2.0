package com.yzx.xiaomusic.network.api;

import com.yzx.xiaomusic.model.entity.songsheet.SongSheetDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yzx on 2018/6/12.
 * Description 歌单Api
 */
public interface SongSheetApi {
    @GET("http://music.163.com/api/playlist/detail/")
    Observable<SongSheetDetail> getSongSheetDetail(@Query("id") String id);
}
