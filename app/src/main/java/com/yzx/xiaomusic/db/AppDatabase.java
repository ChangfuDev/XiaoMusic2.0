package com.yzx.xiaomusic.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.yzx.xiaomusic.app.MusicApplication;
import com.yzx.xiaomusic.db.dao.SearchHistoryDao;
import com.yzx.xiaomusic.db.entity.SearchHistory;

@Database(entities = {SearchHistory.class}, version = 1, exportSchema = false)
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


    public abstract SearchHistoryDao getSearchHistoryDao();
}
