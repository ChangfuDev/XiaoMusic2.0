package com.yzx.xiaomusic.db;

import com.yzx.xiaomusic.db.dao.CollectedSongSheetDao;
import com.yzx.xiaomusic.db.dao.LikedMusicInfoDao;
import com.yzx.xiaomusic.db.dao.SearchHistoryDao;
import com.yzx.xiaomusic.db.entity.LikedMusicInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.utils.JsonUtils;

public class DBUtils {

    /**
     * 获取SearchHistoryDao
     *
     * @return
     */
    public static SearchHistoryDao getSearchHistoryDao() {
        return AppDatabase.getInstance().getSearchHistoryDao();
    }

    public static LikedMusicInfoDao getLikedMusicInfoDao() {
        return AppDatabase.getInstance().getLikedMusicDao();
    }

    public static CollectedSongSheetDao getCollectedSongSheetDao() {
        return AppDatabase.getInstance().getCollectedSongSheetDao();
    }

    public static LikedMusicInfo isLikeMusicExit(MusicInfo musicInfo) {
        return getLikedMusicInfoDao().getLikedMusicInfoById(musicInfo.getMusicId());
    }

    public static void likeMusic(MusicInfo musicInfo) {
        getLikedMusicInfoDao().addLikedMusicInfo(new LikedMusicInfo(musicInfo.getMusicId(), JsonUtils.objectToString(musicInfo)));
    }

    public static void cancelLikeMusic(LikedMusicInfo likedMusicInfo) {
        getLikedMusicInfoDao().deleteLikedMusicInfo(likedMusicInfo);
    }
}
