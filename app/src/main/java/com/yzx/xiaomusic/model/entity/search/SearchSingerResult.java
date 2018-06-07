package com.yzx.xiaomusic.model.entity.search;

import com.yzx.commonlibrary.base.BaseResposeBody;

import java.util.List;

public class SearchSingerResult extends BaseResposeBody {

    /**
     * result : {"artistCount":12,"artists":[{"id":122455,"name":"群星","picUrl":"https://p1.music.126.net/HnrjpF8WyWqxgsvOEqPaWw==/3261151501061433.jpg","alias":["华语群星"],"albumSize":8271,"picId":3261151501061433,"img1v1Url":"https://p1.music.126.net/ABu20RVf-nzyWRg83ds5rw==/1416170978775000.jpg","img1v1":1416170978775000,"mvSize":923,"followed":false,"alia":["华语群星"],"trans":null,"accountId":433800401},{"id":2849,"name":"古巨基","picUrl":"https://p1.music.126.net/OZh9Xp2iiyu93HOCXtxwqA==/5927467185648928.jpg","alias":["Leo"],"albumSize":72,"picId":5927467185648928,"img1v1Url":"https://p1.music.126.net/dc7JiLEiMpM_MgX0kPPImQ==/5965950092650438.jpg","img1v1":5965950092650438,"mvSize":23,"followed":false,"alia":["Leo"],"trans":null},{"id":10577,"name":"周慧敏","picUrl":"https://p1.music.126.net/fTEEzWpzbLj_8OH8a58pXg==/183618441855812.jpg","alias":["Vivian Chow"],"albumSize":42,"picId":183618441855812,"img1v1Url":"https://p1.music.126.net/YUr4ogprx9Q8ZiVxe2Asxw==/140737488371996.jpg","img1v1":140737488371996,"mvSize":20,"followed":false,"alia":["Vivian Chow"],"trans":null},{"id":4711,"name":"区瑞强","picUrl":"https://p1.music.126.net/0lG7cFj9fmzbgLYr-5SxrA==/18671906464509641.jpg","alias":["Albert Au"],"albumSize":46,"picId":18671906464509641,"img1v1Url":"https://p1.music.126.net/yHMveNdI6HK3aDrL9s1mTg==/18577348463987507.jpg","img1v1":18577348463987507,"mvSize":1,"followed":false,"alia":["Albert Au"],"trans":null},{"id":6692,"name":"钟明秋","picUrl":"https://p1.music.126.net/Xp0MgN-ToNJ_GhwzMy8log==/5990139348631132.jpg","alias":[],"albumSize":18,"picId":5990139348631132,"img1v1Url":"https://p1.music.126.net/QQZEygGfT2pTi2P6Jy06sg==/5994537395142275.jpg","img1v1":5994537395142275,"mvSize":0,"followed":false,"trans":null},{"id":9655,"name":"王珺","picUrl":"https://p1.music.126.net/2frWTRBPCUSJJ7a6bylYSQ==/5972547162577651.jpg","alias":["卡洛儿","Carol"],"albumSize":14,"picId":5972547162577651,"img1v1Url":"https://p1.music.126.net/BvbNOQevnQtaK6zyTUmMDQ==/5994537395135171.jpg","img1v1":5994537395135171,"mvSize":0,"followed":false,"alia":["卡洛儿","Carol"],"trans":null},{"id":12104051,"name":"颜妹","picUrl":"https://p1.music.126.net/8SX5S44IMBUza3Nycktb2A==/18589443092529034.jpg","alias":[],"albumSize":28,"picId":18589443092529034,"img1v1Url":"https://p1.music.126.net/Oc3n7UmjKcz4_J6rxqG4GQ==/109951162837831277.jpg","img1v1":109951162837831277,"mvSize":0,"followed":false,"trans":null},{"id":13698968,"name":"大魔王林憬","picUrl":"https://p1.music.126.net/Hfj-8P5ZuRamL1yP4cr1HA==/109951163322109769.jpg","alias":[],"albumSize":29,"picId":109951163322109769,"img1v1Url":"https://p1.music.126.net/wc3oh4Un0PjVW4iJ19x6Bw==/109951163322104916.jpg","accountId":433800401,"img1v1":109951163322104916,"mvSize":0,"followed":false,"trans":null},{"id":12199897,"name":"时明杰","picUrl":"https://p1.music.126.net/7i_HSs_ZOWEXyuiFdt72nQ==/109951162817598485.jpg","alias":[],"albumSize":5,"picId":109951162817598485,"img1v1Url":"https://p1.music.126.net/8R1LncRIuX0m2v_dWvZmOQ==/109951162817597992.jpg","accountId":289151297,"img1v1":109951162817597992,"mvSize":0,"followed":false,"trans":null},{"id":12264319,"name":"wavmen","picUrl":"https://p1.music.126.net/Mmbd_G1BhXYB8Yu6LNPjxg==/109951162891194597.jpg","alias":[],"albumSize":20,"picId":109951162891194597,"img1v1Url":"https://p1.music.126.net/G7I1rGjceNXgXHck5ujl7A==/109951162891192834.jpg","accountId":265355195,"img1v1":109951162891192834,"mvSize":0,"followed":false,"trans":null}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * artistCount : 12
         * artists : [{"id":122455,"name":"群星","picUrl":"https://p1.music.126.net/HnrjpF8WyWqxgsvOEqPaWw==/3261151501061433.jpg","alias":["华语群星"],"albumSize":8271,"picId":3261151501061433,"img1v1Url":"https://p1.music.126.net/ABu20RVf-nzyWRg83ds5rw==/1416170978775000.jpg","img1v1":1416170978775000,"mvSize":923,"followed":false,"alia":["华语群星"],"trans":null},{"id":2849,"name":"古巨基","picUrl":"https://p1.music.126.net/OZh9Xp2iiyu93HOCXtxwqA==/5927467185648928.jpg","alias":["Leo"],"albumSize":72,"picId":5927467185648928,"img1v1Url":"https://p1.music.126.net/dc7JiLEiMpM_MgX0kPPImQ==/5965950092650438.jpg","img1v1":5965950092650438,"mvSize":23,"followed":false,"alia":["Leo"],"trans":null},{"id":10577,"name":"周慧敏","picUrl":"https://p1.music.126.net/fTEEzWpzbLj_8OH8a58pXg==/183618441855812.jpg","alias":["Vivian Chow"],"albumSize":42,"picId":183618441855812,"img1v1Url":"https://p1.music.126.net/YUr4ogprx9Q8ZiVxe2Asxw==/140737488371996.jpg","img1v1":140737488371996,"mvSize":20,"followed":false,"alia":["Vivian Chow"],"trans":null},{"id":4711,"name":"区瑞强","picUrl":"https://p1.music.126.net/0lG7cFj9fmzbgLYr-5SxrA==/18671906464509641.jpg","alias":["Albert Au"],"albumSize":46,"picId":18671906464509641,"img1v1Url":"https://p1.music.126.net/yHMveNdI6HK3aDrL9s1mTg==/18577348463987507.jpg","img1v1":18577348463987507,"mvSize":1,"followed":false,"alia":["Albert Au"],"trans":null},{"id":6692,"name":"钟明秋","picUrl":"https://p1.music.126.net/Xp0MgN-ToNJ_GhwzMy8log==/5990139348631132.jpg","alias":[],"albumSize":18,"picId":5990139348631132,"img1v1Url":"https://p1.music.126.net/QQZEygGfT2pTi2P6Jy06sg==/5994537395142275.jpg","img1v1":5994537395142275,"mvSize":0,"followed":false,"trans":null},{"id":9655,"name":"王珺","picUrl":"https://p1.music.126.net/2frWTRBPCUSJJ7a6bylYSQ==/5972547162577651.jpg","alias":["卡洛儿","Carol"],"albumSize":14,"picId":5972547162577651,"img1v1Url":"https://p1.music.126.net/BvbNOQevnQtaK6zyTUmMDQ==/5994537395135171.jpg","img1v1":5994537395135171,"mvSize":0,"followed":false,"alia":["卡洛儿","Carol"],"trans":null},{"id":12104051,"name":"颜妹","picUrl":"https://p1.music.126.net/8SX5S44IMBUza3Nycktb2A==/18589443092529034.jpg","alias":[],"albumSize":28,"picId":18589443092529034,"img1v1Url":"https://p1.music.126.net/Oc3n7UmjKcz4_J6rxqG4GQ==/109951162837831277.jpg","img1v1":109951162837831277,"mvSize":0,"followed":false,"trans":null},{"id":13698968,"name":"大魔王林憬","picUrl":"https://p1.music.126.net/Hfj-8P5ZuRamL1yP4cr1HA==/109951163322109769.jpg","alias":[],"albumSize":29,"picId":109951163322109769,"img1v1Url":"https://p1.music.126.net/wc3oh4Un0PjVW4iJ19x6Bw==/109951163322104916.jpg","accountId":433800401,"img1v1":109951163322104916,"mvSize":0,"followed":false,"trans":null},{"id":12199897,"name":"时明杰","picUrl":"https://p1.music.126.net/7i_HSs_ZOWEXyuiFdt72nQ==/109951162817598485.jpg","alias":[],"albumSize":5,"picId":109951162817598485,"img1v1Url":"https://p1.music.126.net/8R1LncRIuX0m2v_dWvZmOQ==/109951162817597992.jpg","accountId":289151297,"img1v1":109951162817597992,"mvSize":0,"followed":false,"trans":null},{"id":12264319,"name":"wavmen","picUrl":"https://p1.music.126.net/Mmbd_G1BhXYB8Yu6LNPjxg==/109951162891194597.jpg","alias":[],"albumSize":20,"picId":109951162891194597,"img1v1Url":"https://p1.music.126.net/G7I1rGjceNXgXHck5ujl7A==/109951162891192834.jpg","accountId":265355195,"img1v1":109951162891192834,"mvSize":0,"followed":false,"trans":null}]
         */

        private int artistCount;
        private List<ArtistsBean> artists;

        public int getArtistCount() {
            return artistCount;
        }

        public void setArtistCount(int artistCount) {
            this.artistCount = artistCount;
        }

        public List<ArtistsBean> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBean> artists) {
            this.artists = artists;
        }

        public static class ArtistsBean {
            /**
             * id : 122455
             * name : 群星
             * picUrl : https://p1.music.126.net/HnrjpF8WyWqxgsvOEqPaWw==/3261151501061433.jpg
             * alias : ["华语群星"]
             * albumSize : 8271
             * picId : 3261151501061433
             * img1v1Url : https://p1.music.126.net/ABu20RVf-nzyWRg83ds5rw==/1416170978775000.jpg
             * img1v1 : 1416170978775000
             * mvSize : 923
             * followed : false
             * alia : ["华语群星"]
             * trans : null
             * accountId : 433800401
             */

            private int id;
            private String name;
            private String picUrl;
            private int albumSize;
            private long picId;
            private String img1v1Url;
            private long img1v1;
            private int mvSize;
            private boolean followed;
            private Object trans;
            private int accountId;
            private List<String> alias;
            private List<String> alia;

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

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getAlbumSize() {
                return albumSize;
            }

            public void setAlbumSize(int albumSize) {
                this.albumSize = albumSize;
            }

            public long getPicId() {
                return picId;
            }

            public void setPicId(long picId) {
                this.picId = picId;
            }

            public String getImg1v1Url() {
                return img1v1Url;
            }

            public void setImg1v1Url(String img1v1Url) {
                this.img1v1Url = img1v1Url;
            }

            public long getImg1v1() {
                return img1v1;
            }

            public void setImg1v1(long img1v1) {
                this.img1v1 = img1v1;
            }

            public int getMvSize() {
                return mvSize;
            }

            public void setMvSize(int mvSize) {
                this.mvSize = mvSize;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public Object getTrans() {
                return trans;
            }

            public void setTrans(Object trans) {
                this.trans = trans;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public List<String> getAlias() {
                return alias;
            }

            public void setAlias(List<String> alias) {
                this.alias = alias;
            }

            public List<String> getAlia() {
                return alia;
            }

            public void setAlia(List<String> alia) {
                this.alia = alia;
            }
        }
    }
}
