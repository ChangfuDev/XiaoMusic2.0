package com.yzx.xiaomusic.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.yzx.xiaomusic.app.MusicApplication;
import com.yzx.xiaomusic.db.dao.LikedMusicInfoDao;
import com.yzx.xiaomusic.db.dao.SearchHistoryDao;
import com.yzx.xiaomusic.db.entity.LikedMusicInfo;
import com.yzx.xiaomusic.db.entity.SearchHistory;

/**
 * @author yzx
 */
@Database(entities = {SearchHistory.class, LikedMusicInfo.class}, version = 1, exportSchema = false)
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
     * 获取喜欢的音乐
     *
     * @return
     */
    public abstract LikedMusicInfoDao getLikedMusicDao();
}
