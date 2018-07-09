package com.yzx.xiaomusic.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.yzx.xiaomusic.app.MusicApplication;
import com.yzx.xiaomusic.db.dao.CollectedSongSheetDao;
import com.yzx.xiaomusic.db.dao.ExtraMusicInfoDao;
import com.yzx.xiaomusic.db.dao.SearchHistoryDao;
import com.yzx.xiaomusic.db.entity.ExtraMusicInfo;
import com.yzx.xiaomusic.db.entity.SearchHistory;
import com.yzx.xiaomusic.model.entity.common.SongSheetInfo;

/**
 * @author yzx
 */
@Database(entities = {SearchHistory.class, ExtraMusicInfo.class, SongSheetInfo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDataBase;

    public static AppDatabase getInstance() {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(MusicApplication.getContext(), AppDatabase.class, "xiaoMusic")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDataBase;
    }


    /**
     * 获取搜索历史
     *
     * @return
     */
    public abstract SearchHistoryDao getSearchHistoryDao();

    /**
     * 获取喜欢、听过的音乐
     *
     * @return
     */
    public abstract ExtraMusicInfoDao getExtraMusicDao();

    /**
     * 获取收藏的歌单
     *
     * @return
     */
    public abstract CollectedSongSheetDao getCollectedSongSheetDao();
}
