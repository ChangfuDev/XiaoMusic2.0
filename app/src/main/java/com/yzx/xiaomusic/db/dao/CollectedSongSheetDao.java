package com.yzx.xiaomusic.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yzx.xiaomusic.model.entity.common.SongSheetInfo;

import java.util.List;

/**
 * Created by yzx on 2018/6/29.
 * Description
 */
@Dao
public interface CollectedSongSheetDao {

    /**
     * 查询所有的收藏的歌单
     *
     * @return
     */
    @Query("select * from songsheetinfo")
    List<SongSheetInfo> getAllCollectedSongSheet();

    /**
     * 添加歌单
     *
     * @param songSheetInfos
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSongSheet(SongSheetInfo... songSheetInfos);

    /**
     * 根据Id查询记录
     *
     * @param id
     * @return
     */
    @Query("select  * from songsheetinfo where id = :id")
    SongSheetInfo getCollectedSongSheetById(String id);
}
