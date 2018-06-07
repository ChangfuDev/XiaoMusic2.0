package com.yzx.xiaomusic.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yzx.xiaomusic.db.entity.SearchHistory;

import java.util.List;

@Dao
public interface SearchHistoryDao {
    /**
     * 查询所有的搜索历史记录
     *
     * @return
     */
    @Query("select * from searchHistory")
    List<SearchHistory> getAllSearchHistory();

//    (select top 5 * from 表名 order by id desc) t

    /**
     * 查询最近五条搜索记录
     *
     * @return
     */
    @Query("select  * from searchHistory order by id desc limit 5")
    List<SearchHistory> getLeastFiveSearchHistory();

    /**
     * 添加搜索历史记录
     *
     * @param searchHistories
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSearchHistory(SearchHistory... searchHistories);

    /**
     * 删除搜索历史记录
     *
     * @param searchHistory
     */
    @Delete()
    void deleteSearchHistory(SearchHistory searchHistory);

}
