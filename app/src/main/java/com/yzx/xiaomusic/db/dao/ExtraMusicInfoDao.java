package com.yzx.xiaomusic.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yzx.xiaomusic.db.entity.ExtraMusicInfo;

import java.util.List;

/**
 * @author yzx
 * @date 2018/6/29
 * Description 喜欢、最近播放音乐Dao
 */
@Dao
public interface ExtraMusicInfoDao {

    /**
     * 查询所有的喜欢的音乐
     *
     * @return
     */
    @Query("select * from ExtraMusicInfo where liked = 1 order by latestTime desc")
    List<ExtraMusicInfo> getAllLikedMusicInfos();


    /**
     * 查询所有的听过的音乐
     *
     * @return
     */
    @Query("select * from ExtraMusicInfo where listened = 1 order by latestTime desc limit 100")
    List<ExtraMusicInfo> getAllRecentMusicInfos();

    /**
     * 添加音乐
     *
     * @param extraMusicInfos
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addExtraMusicInfo(ExtraMusicInfo... extraMusicInfos);

    /**
     * 根据Id查询记录
     *
     * @param id
     * @return
     */
    @Query("select  * from ExtraMusicInfo where musicId = :id")
    ExtraMusicInfo getExtraMusicInfoById(String id);

    /**
     * 根据Id查询喜欢记录
     *
     * @param id
     * @return
     */
    @Query("select  * from ExtraMusicInfo where musicId = :id and liked = 1")
    ExtraMusicInfo getLikedMusicInfoById(String id);

    /**
     * 根据Id查询最近播放记录
     *
     * @param id
     * @return
     */
    @Query("select  * from ExtraMusicInfo where musicId = :id and listened = 1")
    ExtraMusicInfo getListenedMusicInfoById(String id);

    /**
     * 删除
     *
     * @param likedMusicInfo
     */
    @Delete()
    void deleteExtraMusicInfo(ExtraMusicInfo likedMusicInfo);

    /**
     * 更新音乐信息
     * 如：歌曲被同时喜欢和收听过，取消喜欢就更新下信息而不是删除
     *
     * @param extraMusicInfo
     */
    @Update
    void updateExtraMusicInfo(ExtraMusicInfo extraMusicInfo);

}
