package com.yzx.xiaomusic.model.entity.mv;

import com.google.gson.annotations.SerializedName;
import com.yzx.commonlibrary.base.BaseResposeBody;

/**
 * Created by yzx on 2018/7/23.
 * Description
 */
public class MvInfo extends BaseResposeBody {


    /**
     * loadingPic :
     * bufferPic :
     * loadingPicFS :
     * bufferPicFS :
     * subed : false
     * data : {"id":5563801,"name":"追光者","artistId":7409,"artistName":"岑宁儿","briefDesc":"纪念你我已经逝去的青春","desc":"青春偶像剧《夏至未至》由\u201c天籁新声\u201d岑宁儿献唱的插曲《追光者》MV发布，立夏（郑爽饰）暗恋傅小司（陈学冬饰）的情节随之曝光。","cover":"http://p1.music.126.net/5JFlnZbDpBR70BsYJIhLrg==/18535567022997577.jpg","coverId":18535567022997577,"playCount":15551543,"subCount":129433,"shareCount":8990,"likeCount":65103,"commentCount":9225,"duration":232000,"nType":0,"publishTime":"2017-06-16","brs":{"240":"http://v4.music.126.net/20180723175550/82bc139c355e4f423979e54a51f9611f/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/2636600d4978583fb14bbf5fe7b1c56a.mp4","480":"http://v6c.music.126.net/20180723175550/75c7e37d0343d111c34ba73eb76ab034/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/da39af7b9ffcf70381545d264ab82d87.mp4","720":"http://v4.music.126.net/20180723175550/7b2147c59911cb0f06be78fb8b878867/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/8a6387700959055502ce04a3bfaf352e.mp4","1080":"http://v4.music.126.net/20180723175550/32ee08df71197ab0740d76834eae5b5c/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/149336109aff55c9fe6895445f0089c8.mp4"},"artists":[{"id":7409,"name":"岑宁儿"}],"isReward":false,"commentThreadId":"R_MV_5_5563801"}
     */

    private String loadingPic;
    private String bufferPic;
    private String loadingPicFS;
    private String bufferPicFS;
    private boolean subed;
    private DataBean data;

    public String getLoadingPic() {
        return loadingPic;
    }

    public void setLoadingPic(String loadingPic) {
        this.loadingPic = loadingPic;
    }

    public String getBufferPic() {
        return bufferPic;
    }

    public void setBufferPic(String bufferPic) {
        this.bufferPic = bufferPic;
    }

    public String getLoadingPicFS() {
        return loadingPicFS;
    }

    public void setLoadingPicFS(String loadingPicFS) {
        this.loadingPicFS = loadingPicFS;
    }

    public String getBufferPicFS() {
        return bufferPicFS;
    }

    public void setBufferPicFS(String bufferPicFS) {
        this.bufferPicFS = bufferPicFS;
    }

    public boolean isSubed() {
        return subed;
    }

    public void setSubed(boolean subed) {
        this.subed = subed;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5563801
         * name : 追光者
         * artistId : 7409
         * artistName : 岑宁儿
         * briefDesc : 纪念你我已经逝去的青春
         * desc : 青春偶像剧《夏至未至》由“天籁新声”岑宁儿献唱的插曲《追光者》MV发布，立夏（郑爽饰）暗恋傅小司（陈学冬饰）的情节随之曝光。
         * cover : http://p1.music.126.net/5JFlnZbDpBR70BsYJIhLrg==/18535567022997577.jpg
         * coverId : 18535567022997577
         * playCount : 15551543
         * subCount : 129433
         * shareCount : 8990
         * likeCount : 65103
         * commentCount : 9225
         * duration : 232000
         * nType : 0
         * publishTime : 2017-06-16
         * brs : {"240":"http://v4.music.126.net/20180723175550/82bc139c355e4f423979e54a51f9611f/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/2636600d4978583fb14bbf5fe7b1c56a.mp4","480":"http://v6c.music.126.net/20180723175550/75c7e37d0343d111c34ba73eb76ab034/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/da39af7b9ffcf70381545d264ab82d87.mp4","720":"http://v4.music.126.net/20180723175550/7b2147c59911cb0f06be78fb8b878867/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/8a6387700959055502ce04a3bfaf352e.mp4","1080":"http://v4.music.126.net/20180723175550/32ee08df71197ab0740d76834eae5b5c/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/149336109aff55c9fe6895445f0089c8.mp4"}
         * artists : [{"id":7409,"name":"岑宁儿"}]
         * isReward : false
         * commentThreadId : R_MV_5_5563801
         */

        private int id;
        private String name;
        private int artistId;
        private String artistName;
        private String briefDesc;
        private String desc;
        private String cover;
        private long coverId;
        private int playCount;
        private int subCount;
        private int shareCount;
        private int likeCount;
        private int commentCount;
        private int duration;
        private int nType;
        private String publishTime;
        private BrsBean brs;
        private boolean isReward;
        private String commentThreadId;
//        private List<ArtistsBean> artists;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getBriefDesc() {
            return briefDesc;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public long getCoverId() {
            return coverId;
        }

        public void setCoverId(long coverId) {
            this.coverId = coverId;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getSubCount() {
            return subCount;
        }

        public void setSubCount(int subCount) {
            this.subCount = subCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getNType() {
            return nType;
        }

        public void setNType(int nType) {
            this.nType = nType;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public BrsBean getBrs() {
            return brs;
        }

        public void setBrs(BrsBean brs) {
            this.brs = brs;
        }

        public boolean isIsReward() {
            return isReward;
        }

        public void setIsReward(boolean isReward) {
            this.isReward = isReward;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

//        public List<ArtistsBean> getArtists() {
//            return artists;
//        }
//
//        public void setArtists(List<ArtistsBean> artists) {
//            this.artists = artists;
//        }

        public static class BrsBean {
            /**
             * 240 : http://v4.music.126.net/20180723175550/82bc139c355e4f423979e54a51f9611f/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/2636600d4978583fb14bbf5fe7b1c56a.mp4
             * 480 : http://v6c.music.126.net/20180723175550/75c7e37d0343d111c34ba73eb76ab034/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/da39af7b9ffcf70381545d264ab82d87.mp4
             * 720 : http://v4.music.126.net/20180723175550/7b2147c59911cb0f06be78fb8b878867/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/8a6387700959055502ce04a3bfaf352e.mp4
             * 1080 : http://v4.music.126.net/20180723175550/32ee08df71197ab0740d76834eae5b5c/web/cloudmusic/JjYiICIwMCAjMCBiMCAgIA==/mv/5563801/149336109aff55c9fe6895445f0089c8.mp4
             */

            @SerializedName("240")
            private String _$240;
            @SerializedName("480")
            private String _$480;
            @SerializedName("720")
            private String _$720;
            @SerializedName("1080")
            private String _$1080;

            public String get_$240() {
                return _$240;
            }

            public void set_$240(String _$240) {
                this._$240 = _$240;
            }

            public String get_$480() {
                return _$480;
            }

            public void set_$480(String _$480) {
                this._$480 = _$480;
            }

            public String get_$720() {
                return _$720;
            }

            public void set_$720(String _$720) {
                this._$720 = _$720;
            }

            public String get_$1080() {
                return _$1080;
            }

            public void set_$1080(String _$1080) {
                this._$1080 = _$1080;
            }
        }

        public static class ArtistsBean {
            /**
             * id : 7409
             * name : 岑宁儿
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
