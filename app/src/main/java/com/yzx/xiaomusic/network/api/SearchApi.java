package com.yzx.xiaomusic.network.api;

import com.yzx.commonlibrary.base.BaseResposeBody;
import com.yzx.xiaomusic.model.entity.search.SearchAlbumResult;
import com.yzx.xiaomusic.model.entity.search.SearchMusicResult;
import com.yzx.xiaomusic.model.entity.search.SearchMvResult;
import com.yzx.xiaomusic.model.entity.search.SearchRadioResult;
import com.yzx.xiaomusic.model.entity.search.SearchSingerResult;
import com.yzx.xiaomusic.model.entity.search.SearchSongSheetResult;
import com.yzx.xiaomusic.model.entity.search.SearchUserResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 搜索APi
 */
public interface SearchApi<T extends BaseResposeBody> {


    /**
     * https://api.imjad.cn/cloudmusic/?type=search&limit=20&offset=1&s=追光者&search_type=1
     *
     * @param type
     * @param limit
     * @param search_type search_type	含义
     *                    1	单曲
     *                    10	专辑
     *                    100	歌手
     *                    1000	歌单
     *                    1002	用户
     *                    1004	mv
     *                    1006	歌词
     *                    1009	主播电台
     * @param offset
     * @param s
     * @return
     */
    @GET("/cloudmusic/")
    Observable<SearchMusicResult> searchMusic(@Query("type") String type, @Query("limit") int limit, @Query("search_type") int search_type,
                                              @Query("offset") int offset, @Query("s") String s);

//
//@GET("/cloudmusic/")
//    Observable<SearchMvResult> searchMv(@Query("type") String type, @Query("limit") int limit, @Query("search_type") int search_type,
//                                        @Query("offset") int offset, @Query("s") String s);

    /**
     * 搜索Mv
     *
     * @param type
     * @param limit
     * @param offset
     * @param s
     * @return
     */
    @POST("http://music.163.com/api/search/pc/")
    Observable<SearchMvResult> searchMv(@Query("type") int type, @Query("limit") String limit,
                                        @Query("offset") String offset, @Query("s") String s);

    @GET("/cloudmusic/")
    Observable<SearchSingerResult> searchSinger(@Query("type") String type, @Query("limit") int limit, @Query("search_type") int search_type,
                                                @Query("offset") int offset, @Query("s") String s);

    @GET("/cloudmusic/")
    Observable<SearchAlbumResult> searchAlbum(@Query("type") String type, @Query("limit") int limit, @Query("search_type") int search_type,
                                              @Query("offset") int offset, @Query("s") String s);

    @GET("/cloudmusic/")
    Observable<SearchSongSheetResult> searchSongSheet(@Query("type") String type, @Query("limit") int limit, @Query("search_type") int search_type,
                                                      @Query("offset") int offset, @Query("s") String s);

    @GET("/cloudmusic/")
    Observable<SearchRadioResult> searchRadio(@Query("type") String type, @Query("limit") int limit, @Query("search_type") int search_type,
                                              @Query("offset") int offset, @Query("s") String s);

    @GET("/cloudmusic/")
    Observable<SearchUserResult> searchUser(@Query("type") String type, @Query("limit") int limit, @Query("search_type") int search_type,
                                            @Query("offset") int offset, @Query("s") String s);
}
