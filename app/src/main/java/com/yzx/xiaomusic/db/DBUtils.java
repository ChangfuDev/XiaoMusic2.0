package com.yzx.xiaomusic.db;

import com.yzx.xiaomusic.db.dao.CollectedSongSheetDao;
import com.yzx.xiaomusic.db.dao.LikedMusicInfoDao;
import com.yzx.xiaomusic.db.dao.SearchHistoryDao;

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
}
