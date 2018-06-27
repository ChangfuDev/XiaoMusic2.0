package com.yzx.xiaomusic.network;

public class ApiConstant {

    /**
     * 参数说明
     * 参数名 含义 默认
     * type string 指定请求类型 song
     * id int 分类对应的ID 必需
     * <p>
     * 类型相关
     * type 含义
     * song 单曲
     * lyric 歌词
     * comments 评论
     * detail 歌曲详情
     * artist 歌手
     * album 专辑
     * playlist 歌单
     * record 听歌记录
     * mv MV
     * djradio 主播电台
     * dj 主播电台单曲 id
     * detail_dj 主播电台歌曲详情
     * search 搜索
     */
    public static final String TYPE_SEARCH = "search";

    public static final String TYPE_ALBUM = "album";

    public static final String TYPE_SONG = "song";

    public static final String TYPE_LYRIC = "lyric";
    public static String TYPE_SINGER = "artist";
    public static final String BR_320 = "320000";
    /**
     * 分页时，每页加载数量
     */
    public static final int LIMIT = 10;


}
