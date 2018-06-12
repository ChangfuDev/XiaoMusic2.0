package com.yzx.xiaomusic.ui.search.result;

import android.annotation.SuppressLint;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.MusicInfo;
import com.yzx.xiaomusic.model.entity.SingerInfo;
import com.yzx.xiaomusic.model.entity.common.SongSheetInfo;
import com.yzx.xiaomusic.model.entity.search.SearchAlbumResult;
import com.yzx.xiaomusic.model.entity.search.SearchMusicResult;
import com.yzx.xiaomusic.model.entity.search.SearchMvResult;
import com.yzx.xiaomusic.model.entity.search.SearchRadioResult;
import com.yzx.xiaomusic.model.entity.search.SearchSingerResult;
import com.yzx.xiaomusic.model.entity.search.SearchSongSheetResult;
import com.yzx.xiaomusic.model.entity.search.SearchUserResult;
import com.yzx.xiaomusic.widget.loadsir.ErrorCallback;
import com.yzx.xiaomusic.widget.loadsir.LoadingCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_ALBUM;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_MUSIC;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_MV;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_RADIO;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_SINGER;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_SONG_SHEET;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_USER;

@SuppressLint("CheckResult")
public class SearchResultPresenter extends CommonBasePresenter<SearchResultFragment, SearchResultModel> {
    @Override
    protected SearchResultModel getModel() {
        return new SearchResultModel();
    }

    public void getSearchResult(int searchType, int offset, String s) {

        if (offset == 0) {
            mView.loadService.showCallback(LoadingCallback.class);
        }

//        searchType = 100;
//        searchSinger(searchType, offset, s);
        switch (searchType) {
            case TYPE_SEARCH_MUSIC:
                searchMusic(searchType, offset, s);
                break;
            case TYPE_SEARCH_MV:
                searchMv(searchType, offset, s);
                break;
            case TYPE_SEARCH_SINGER:
                searchSinger(searchType, offset, s);
                break;
            case TYPE_SEARCH_ALBUM:
                searchAlbum(searchType, offset, s);
                break;
            case TYPE_SEARCH_SONG_SHEET:
                searchSongSheet(searchType, offset, s);
                break;
            case TYPE_SEARCH_RADIO:
                searchRadio(searchType, offset, s);
                break;
            case TYPE_SEARCH_USER:
                searchUser(searchType, offset, s);
                break;
        }
    }

    private void searchUser(int searchType, int offset, String s) {
        mModel.getSearchUserResult(searchType, offset, s, new CommonMvpObserver<SearchUserResult>() {
            @Override
            protected void onSuccess(SearchUserResult searchUserResult) {
                mView.loadService.showSuccess();
                mView.onLoadMoreSuccess(searchUserResult.getResult().getUserprofiles());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                SearchResultPresenter.this.onFail(errorMsg, offset);
            }
        });
    }

    private void searchRadio(int searchType, int offset, String s) {
        mModel.getSearchRadioResult(searchType, offset, s, new CommonMvpObserver<SearchRadioResult>() {
            @Override
            protected void onSuccess(SearchRadioResult searchRadioResult) {
                mView.loadService.showSuccess();
                mView.onLoadMoreSuccess(searchRadioResult.getResult().getDjRadios());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                SearchResultPresenter.this.onFail(errorMsg, offset);
            }
        });
    }

    private void searchSongSheet(int searchType, int offset, String s) {
        mModel.getSearchSongSheetResult(searchType, offset, s, new CommonMvpObserver<SearchSongSheetResult>() {

            @Override
            protected void onSuccess(SearchSongSheetResult searchSongSheetResult) {

                Observable
                        .fromIterable(searchSongSheetResult.getResult().getPlaylists())
                        .map(playlistsBean -> {
                            SongSheetInfo songSheetInfo = new SongSheetInfo();
                            songSheetInfo.setId(playlistsBean.getId());
                            songSheetInfo.setTitle(playlistsBean.getName());
                            songSheetInfo.setCoverUrl(playlistsBean.getCoverImgUrl());
                            songSheetInfo.setMusicCount(String.valueOf(playlistsBean.getTrackCount()));
                            songSheetInfo.setPlayCount(String.valueOf(playlistsBean.getPlayCount()));

                            SearchSongSheetResult.ResultBean.PlaylistsBean.CreatorBean creator = playlistsBean.getCreator();
                            if (creator != null) {
                                songSheetInfo.setCreatorId(String.valueOf(creator.getUserId()));
                                songSheetInfo.setCreatorNickName(creator.getNickname());
                            }
                            return songSheetInfo;
                        })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toList()
                        .subscribe(songSheetInfos -> {
                            mView.onLoadMoreSuccess(songSheetInfos);
                            mView.loadService.showSuccess();
                        });
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                SearchResultPresenter.this.onFail(errorMsg, offset);
            }
        });
    }

    private void searchAlbum(int searchType, int offset, String s) {
        mModel.getSearchAlbumResult(searchType, offset, s, new CommonMvpObserver<SearchAlbumResult>() {
            @Override
            protected void onSuccess(SearchAlbumResult searchAlbumResult) {
                mView.loadService.showSuccess();
                mView.onLoadMoreSuccess(searchAlbumResult.getResult().getAlbums());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                SearchResultPresenter.this.onFail(errorMsg, offset);
            }
        });
    }

    private void searchSinger(int searchType, int offset, String s) {
        mModel.getSearchSingerResult(searchType, offset, s, new CommonMvpObserver<SearchSingerResult>() {
            @Override
            protected void onSuccess(SearchSingerResult searchSingerResult) {
                mView.loadService.showSuccess();
                mView.onLoadMoreSuccess(searchSingerResult.getResult().getArtists());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                SearchResultPresenter.this.onFail(errorMsg, offset);
            }
        });
    }

    private void searchMv(int searchType, int offset, String s) {
        mModel.getSearchMvResult(searchType, offset, s, new CommonMvpObserver<SearchMvResult>() {
            @Override
            protected void onSuccess(SearchMvResult searchMvResult) {
                mView.loadService.showSuccess();
                mView.onLoadMoreSuccess(searchMvResult.getResult().getMvs());
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                SearchResultPresenter.this.onFail(errorMsg, offset);
            }
        });


    }

    /**
     * 失败统一操作
     *
     * @param errorMsg
     * @param offset
     */
    private void onFail(String errorMsg, int offset) {
        if (offset == 0) {
            mView.loadService.showCallback(ErrorCallback.class);
        } else {
            mView.loadService.showSuccess();
        }
        mView.onLoadMoreFail(errorMsg);
    }

    private void searchMusic(int searchType, int offset, String s) {
        mModel.getSearchMusicResult(searchType, offset, s, new CommonMvpObserver<SearchMusicResult>() {
            @Override
            protected void onSuccess(SearchMusicResult searchMusicResult) {
                mView.loadService.showSuccess();

                //处理数据音乐
                Observable
                        .fromIterable(searchMusicResult.getResult().getSongs())
                        .map(songsBean -> {

                            MusicInfo musicInfo = new MusicInfo();
                            musicInfo.setMusicId(songsBean.getId());
                            musicInfo.setMusicName(songsBean.getName());

                            SearchMusicResult.ResultBean.SongsBean.AlBean al = songsBean.getAl();
                            musicInfo.setAlbumId(al.getId());
                            musicInfo.setAlbumName(al.getName());
                            musicInfo.setAlbumCoverPath(al.getPicUrl());


                            List<SearchMusicResult.ResultBean.SongsBean.ArBean> ar = songsBean.getAr();
                            List<SingerInfo> singerInfos = new ArrayList<>();
                            for (int i = 0; i < ar.size(); i++) {
                                SearchMusicResult.ResultBean.SongsBean.ArBean arBean = ar.get(i);
                                SingerInfo singerInfo = new SingerInfo();
                                singerInfo.setSingerId(arBean.getId());
                                singerInfo.setSingerName(arBean.getName());
                                singerInfos.add(singerInfo);
                            }
                            musicInfo.setSingerInfos(singerInfos);

                            musicInfo.setRingTone(songsBean.getRt());
                            musicInfo.setMvId(songsBean.getMv());

                            return musicInfo;
                        })
                        .toList()
                        .subscribe(musicInfos -> {
                            mView.onLoadMoreSuccess(musicInfos);
                        })
                ;
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                SearchResultPresenter.this.onFail(errorMsg, offset);
            }
        });
    }
}
