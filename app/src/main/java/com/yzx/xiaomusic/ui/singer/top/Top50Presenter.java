package com.yzx.xiaomusic.ui.singer.top;

import android.annotation.SuppressLint;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.SingerTopInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.common.SingerInfo;
import com.yzx.xiaomusic.ui.singer.SingerDetailModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author yzx
 */
public class Top50Presenter extends CommonBasePresenter<Top50Fragment, SingerDetailModel> {
    @Override
    protected SingerDetailModel getModel() {
        return new SingerDetailModel();
    }

    public void getTop50(String id) {
        mView.showLoadingLayout();
        mModel.getTop50(id, new CommonMvpObserver<SingerTopInfo>() {
            @SuppressLint("CheckResult")
            @Override
            protected void onSuccess(SingerTopInfo singerTopInfo) {
                mView.showSuccessLayout();
                List<SingerTopInfo.HotSongsBean> hotSongs = singerTopInfo.getHotSongs();
                SingerTopInfo.ArtistBean artist = singerTopInfo.getArtist();
                if (hotSongs != null && hotSongs.size() > 0) {
                    Observable
                            .fromIterable(hotSongs)
                            .map(hotSongsBean -> {
                                MusicInfo musicInfo = new MusicInfo();

                                musicInfo.setMusicName(hotSongsBean.getName());
                                musicInfo.setMusicId(hotSongsBean.getId());
                                musicInfo.setDuration(hotSongsBean.getDt());

                                SingerTopInfo.HotSongsBean.AlBean al = hotSongsBean.getAl();
                                if (al != null) {
                                    musicInfo.setAlbumId(al.getId());
                                    musicInfo.setAlbumName(al.getName());
                                    musicInfo.setAlbumCoverPath(al.getPicUrl());
                                }
                                ArrayList<SingerInfo> singerInfos = new ArrayList<>();
                                SingerInfo singerInfo = new SingerInfo();
                                singerInfo.setSingerName(artist.getName());
                                singerInfo.setSingerId(artist.getId());
                                singerInfo.setSingerCoverPath(artist.getPicUrl());
                                singerInfos.add(singerInfo);
                                musicInfo.setSingerInfos(singerInfos);

                                musicInfo.setMvId(hotSongsBean.getMv());
                                return musicInfo;
                            })
                            .toList()
                            .subscribe(musicInfos -> mView.setData(musicInfos, artist));
                } else {
                    mView.showEmptyLayout();
                }
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                mView.showErrorLayout();
            }
        });
    }
}
