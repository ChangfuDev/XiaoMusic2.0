package com.yzx.xiaomusic.ui.search.result;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.model.entity.search.SearchAlbumResult;
import com.yzx.xiaomusic.model.entity.search.SearchMusicResult;
import com.yzx.xiaomusic.model.entity.search.SearchMvResult;
import com.yzx.xiaomusic.model.entity.search.SearchRadioResult;
import com.yzx.xiaomusic.model.entity.search.SearchSingerResult;
import com.yzx.xiaomusic.model.entity.search.SearchSongSheetResult;
import com.yzx.xiaomusic.model.entity.search.SearchUserResult;
import com.yzx.xiaomusic.network.api.SearchApi;

import static com.yzx.xiaomusic.network.ApiConstant.LIMIT;
import static com.yzx.xiaomusic.network.ApiConstant.TYPE_SEARCH;


public class SearchResultModel extends CommonBaseModel {


    /**
     * @param searchType
     * @param offset
     * @param s
     */
    public void getSearchMusicResult(int searchType, int offset, String s, CommonMvpObserver<SearchMusicResult> observer) {

        getApiService(SearchApi.class)
                .searchMusic(TYPE_SEARCH, LIMIT, searchType, offset, s)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }

    /**
     * @param offset
     * @param s
     */
    public void getSearchMvResult(int searchType, int offset, String s, CommonMvpObserver<SearchMvResult> observer) {

        getApiService(SearchApi.class)
                .searchMv(TYPE_SEARCH, LIMIT, searchType, offset, s)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }

    /**
     * @param offset
     * @param s
     */
    public void getSearchSingerResult(int searchType, int offset, String s, CommonMvpObserver<SearchSingerResult> observer) {

        getApiService(SearchApi.class)
                .searchSinger(TYPE_SEARCH, LIMIT, searchType, offset, s)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }


    /**
     * @param offset
     * @param s
     */
    public void getSearchAlbumResult(int searchType, int offset, String s, CommonMvpObserver<SearchAlbumResult> observer) {

        getApiService(SearchApi.class)
                .searchAlbum(TYPE_SEARCH, LIMIT, searchType, offset, s)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }

    /**
     * @param offset
     * @param s
     */
    public void getSearchSongSheetResult(int searchType, int offset, String s, CommonMvpObserver<SearchSongSheetResult> observer) {

        getApiService(SearchApi.class)
                .searchSongSheet(TYPE_SEARCH, LIMIT, searchType, offset, s)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }

    /**
     * @param offset
     * @param s
     */
    public void getSearchRadioResult(int searchType, int offset, String s, CommonMvpObserver<SearchRadioResult> observer) {

        getApiService(SearchApi.class)
                .searchRadio(TYPE_SEARCH, LIMIT, searchType, offset, s)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }

    /**
     * @param offset
     * @param s
     */
    public void getSearchUserResult(int searchType, int offset, String s, CommonMvpObserver<SearchUserResult> observer) {

        getApiService(SearchApi.class)
                .searchUser(TYPE_SEARCH, LIMIT, searchType, offset, s)
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(observer);
    }
}
