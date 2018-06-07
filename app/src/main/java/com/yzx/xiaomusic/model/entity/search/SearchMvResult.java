package com.yzx.xiaomusic.model.entity.search;

import com.yzx.commonlibrary.base.BaseResposeBody;

import java.util.List;

public class SearchMvResult extends BaseResposeBody {


    /**
     * result : {"mvCount":1,"mvs":[{"id":444030,"cover":"http://p4.music.126.net/L_pGeKnST1-wwlM9raFfvQ==/7931876884172166.jpg","name":"爱得太迟","playCount":0,"briefDesc":"","desc":null,"artistName":"古巨基","artistId":2849,"duration":326000,"mark":0,"artists":[{"id":2849,"name":"古巨基","alias":["Ku Kui Kei","Leo","Leo Ku"],"transNames":null},{"id":10577,"name":"周慧敏","alias":["Vivian Chow"],"transNames":null}]}]}
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
         * mvCount : 1
         * mvs : [{"id":444030,"cover":"http://p4.music.126.net/L_pGeKnST1-wwlM9raFfvQ==/7931876884172166.jpg","name":"爱得太迟","playCount":0,"briefDesc":"","desc":null,"artistName":"古巨基","artistId":2849,"duration":326000,"mark":0,"artists":[{"id":2849,"name":"古巨基","alias":["Ku Kui Kei","Leo","Leo Ku"],"transNames":null},{"id":10577,"name":"周慧敏","alias":["Vivian Chow"],"transNames":null}]}]
         */

        private int mvCount;
        private List<MvsBean> mvs;

        public int getMvCount() {
            return mvCount;
        }

        public void setMvCount(int mvCount) {
            this.mvCount = mvCount;
        }

        public List<MvsBean> getMvs() {
            return mvs;
        }

        public void setMvs(List<MvsBean> mvs) {
            this.mvs = mvs;
        }

        public static class MvsBean {
            /**
             * id : 444030
             * cover : http://p4.music.126.net/L_pGeKnST1-wwlM9raFfvQ==/7931876884172166.jpg
             * name : 爱得太迟
             * playCount : 0
             * briefDesc :
             * desc : null
             * artistName : 古巨基
             * artistId : 2849
             * duration : 326000
             * mark : 0
             * artists : [{"id":2849,"name":"古巨基","alias":["Ku Kui Kei","Leo","Leo Ku"],"transNames":null},{"id":10577,"name":"周慧敏","alias":["Vivian Chow"],"transNames":null}]
             */

            private int id;
            private String cover;
            private String name;
            private int playCount;
            private String briefDesc;
            private Object desc;
            private String artistName;
            private int artistId;
            private int duration;
            private int mark;
            private List<ArtistsBean> artists;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPlayCount() {
                return playCount;
            }

            public void setPlayCount(int playCount) {
                this.playCount = playCount;
            }

            public String getBriefDesc() {
                return briefDesc;
            }

            public void setBriefDesc(String briefDesc) {
                this.briefDesc = briefDesc;
            }

            public Object getDesc() {
                return desc;
            }

            public void setDesc(Object desc) {
                this.desc = desc;
            }

            public String getArtistName() {
                return artistName;
            }

            public void setArtistName(String artistName) {
                this.artistName = artistName;
            }

            public int getArtistId() {
                return artistId;
            }

            public void setArtistId(int artistId) {
                this.artistId = artistId;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getMark() {
                return mark;
            }

            public void setMark(int mark) {
                this.mark = mark;
            }

            public List<ArtistsBean> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsBean> artists) {
                this.artists = artists;
            }

            public static class ArtistsBean {
                /**
                 * id : 2849
                 * name : 古巨基
                 * alias : ["Ku Kui Kei","Leo","Leo Ku"]
                 * transNames : null
                 */

                private int id;
                private String name;
                private Object transNames;
                private List<String> alias;

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

                public Object getTransNames() {
                    return transNames;
                }

                public void setTransNames(Object transNames) {
                    this.transNames = transNames;
                }

                public List<String> getAlias() {
                    return alias;
                }

                public void setAlias(List<String> alias) {
                    this.alias = alias;
                }
            }
        }
    }
}
