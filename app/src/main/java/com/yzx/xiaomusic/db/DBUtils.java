package com.yzx.xiaomusic.db;

import com.yzx.xiaomusic.db.dao.CollectedSongSheetDao;
import com.yzx.xiaomusic.db.dao.ExtraMusicInfoDao;
import com.yzx.xiaomusic.db.dao.SearchHistoryDao;
import com.yzx.xiaomusic.db.entity.ExtraMusicInfo;
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

    public static ExtraMusicInfoDao getExtraMusicInfoDao() {
        return AppDatabase.getInstance().getExtraMusicDao();
    }

    public static CollectedSongSheetDao getCollectedSongSheetDao() {
        return AppDatabase.getInstance().getCollectedSongSheetDao();
    }

    /**
     * 判断是否音乐是否被喜欢
     *
     * @param musicInfo
     * @return
     */
    public static ExtraMusicInfo isLikeMusicExit(MusicInfo musicInfo) {
        return getExtraMusicInfoDao().getLikedMusicInfoById(musicInfo.getMusicId());
    }

    /**
     * 喜欢音乐
     * 如果音乐信息已存在，更新音乐信息
     *
     * @param musicInfo
     */
    public static void likeMusic(MusicInfo musicInfo) {
        ExtraMusicInfo extraMusicInfo = getExtraMusicInfoDao().getExtraMusicInfoById(musicInfo.getMusicId());
        if (extraMusicInfo != null) {
            extraMusicInfo.setLiked(1);
            extraMusicInfo.setLatestTime(System.currentTimeMillis());
            getExtraMusicInfoDao().updateExtraMusicInfo(extraMusicInfo);
        } else {
            ExtraMusicInfo newExtraMusicInfo = new ExtraMusicInfo(musicInfo.getMusicId(), JsonUtils.objectToString(musicInfo), 1, 0);
            newExtraMusicInfo.setPlayCount(1);
            newExtraMusicInfo.setLatestTime(System.currentTimeMillis());
            getExtraMusicInfoDao().addExtraMusicInfo(newExtraMusicInfo);
        }
    }

    /**
     * 加入听过的音乐列表
     * 如果音乐信息已存在，更新音乐信息
     *
     * @param musicInfo
     */
    public static void listenMusic(MusicInfo musicInfo) {
        ExtraMusicInfo extraMusicInfo = getExtraMusicInfoDao().getExtraMusicInfoById(musicInfo.getMusicId());
        if (extraMusicInfo != null) {
            extraMusicInfo.setListened(1);
            extraMusicInfo.setPlayCount(extraMusicInfo.getPlayCount() + 1);
            extraMusicInfo.setLatestTime(System.currentTimeMillis());
            getExtraMusicInfoDao().updateExtraMusicInfo(extraMusicInfo);
        } else {
            ExtraMusicInfo newExtraMusicInfo = new ExtraMusicInfo(musicInfo.getMusicId(), JsonUtils.objectToString(musicInfo), 0, 1);
            newExtraMusicInfo.setPlayCount(1);
            newExtraMusicInfo.setLatestTime(System.currentTimeMillis());
            getExtraMusicInfoDao().addExtraMusicInfo(newExtraMusicInfo);
        }
    }

    /**
     * 删除喜欢记录
     * 实质是更新喜欢状态
     *
     * @param likedMusicInfo
     */
    public static void cancelLikeMusic(ExtraMusicInfo likedMusicInfo) {
        likedMusicInfo.setLiked(0);
        getExtraMusicInfoDao().updateExtraMusicInfo(likedMusicInfo);
    }


}
