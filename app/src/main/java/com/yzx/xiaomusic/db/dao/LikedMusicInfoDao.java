package com.yzx.xiaomusic.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yzx.xiaomusic.db.entity.LikedMusicInfo;

import java.util.List;

/**
 * Created by yzx on 2018/6/29.
 * Description
 */
@Dao
public interface LikedMusicInfoDao {

    /**
     * 查询所有的喜欢的音乐
     *
     * @return
     */
    @Query("select * from likedmusicinfo")
    List<LikedMusicInfo> getAllLikedMusicInfos();

    /**
     * 添加喜欢的音乐
     *
     * @param likedMusicInfos
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLikedMusicInfo(LikedMusicInfo... likedMusicInfos);

    /**
     * 根据Id查询记录
     *
     * @param id
     * @return
     */
    @Query("select  * from likedmusicinfo where musicId = :id")
    LikedMusicInfo getLikedMusicInfoById(String id);

    /**
     * 删除
     *
     * @param likedMusicInfo
     */
    @Delete()
    void deleteLikedMusicInfo(LikedMusicInfo likedMusicInfo);
}
