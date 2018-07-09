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
            getExtraMusicInfoDao().updateExtraMusicInfo(extraMusicInfo);
        } else {
            getExtraMusicInfoDao().addExtraMusicInfo(new ExtraMusicInfo(musicInfo.getMusicId(), JsonUtils.objectToString(musicInfo), 1, 0));
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
            getExtraMusicInfoDao().updateExtraMusicInfo(extraMusicInfo);
        } else {
            getExtraMusicInfoDao().addExtraMusicInfo(new ExtraMusicInfo(musicInfo.getMusicId(), JsonUtils.objectToString(musicInfo), 0, 1));
        }
    }

    /**
     * 删除喜欢记录
     * 如果同时收听过，则只需要更新喜欢信息即可，否则则直接删除记录
     *
     * @param likedMusicInfo
     */
    public static void cancelLikeMusic(ExtraMusicInfo likedMusicInfo) {
        if (likedMusicInfo.listened == 1 && likedMusicInfo.liked == 1) {
            likedMusicInfo.setLiked(0);
            getExtraMusicInfoDao().updateExtraMusicInfo(likedMusicInfo);
        } else {
            getExtraMusicInfoDao().deleteExtraMusicInfo(likedMusicInfo);
        }
    }


}
