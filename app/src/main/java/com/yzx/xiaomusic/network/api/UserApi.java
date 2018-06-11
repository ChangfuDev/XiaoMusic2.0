package com.yzx.xiaomusic.network.api;

import com.yzx.xiaomusic.model.entity.search.SearchMvResult;
import com.yzx.xiaomusic.model.entity.user.UserSongSheet;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {
    //http://music.163.com/api/user/playlist/?uid=1360597&limit=0&offset=10
    @GET("http://music.163.com/api/user/playlist/")
    Observable<UserSongSheet> getUserInfo(@Query("offset") String offset, @Query("limit") String limit, @Query("uid") String uid);
}
