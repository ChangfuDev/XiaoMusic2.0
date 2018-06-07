package com.yzx.xiaomusic.db;

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
}
