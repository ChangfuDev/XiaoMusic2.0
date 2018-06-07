package com.yzx.xiaomusic.utils;

import com.yzx.xiaomusic.model.entity.MusicInfo;
import com.yzx.xiaomusic.model.entity.SingerInfo;
import com.yzx.xiaomusic.model.entity.search.SearchMvResult;

import java.util.List;

public class MusicDataUtils {

    public static String getSingers(MusicInfo musicInfo) {

        String singers = "";
        List<SingerInfo> singerInfos = musicInfo.getSingerInfos();

        if (singerInfos.size() > 0) {

            for (int i = 0; i < singerInfos.size(); i++) {
                singers = singers + singerInfos.get(i).getSingerName() + (i == (singerInfos.size() - 1) ? "" : "/");
            }
            return singers;
        } else {
            return null;
        }
    }


    public static String getSingers(List<SearchMvResult.ResultBean.MvsBean.ArtistsBean> artists) {
        String singers = "";

        if (artists.size() > 0) {

            for (int i = 0; i < artists.size(); i++) {
                singers = singers + artists.get(i).getName() + (i == (artists.size() - 1) ? "" : "/");
            }
            return singers;
        } else {
            return null;
        }
    }
}
