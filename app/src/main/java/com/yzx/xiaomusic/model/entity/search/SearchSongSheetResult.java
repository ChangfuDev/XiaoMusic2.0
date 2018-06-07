package com.yzx.xiaomusic.model.entity.search;

import com.yzx.commonlibrary.base.BaseResposeBody;

import java.util.List;

public class SearchSongSheetResult extends BaseResposeBody {

    /**
     * result : {"playlists":[{"id":897849660,"name":"【刘惜君歌曲】评论量过999 排行榜（57首）","coverImgUrl":"https://p1.music.126.net/LUgEJeO1ldHSxvXBZ7kT-Q==/18890709277139952.jpg","creator":{"nickname":"意一念之间_获如是真我","userId":453399705,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":57,"userId":453399705,"playCount":23678,"bookCount":482,"highQuality":false},{"id":125330696,"name":"他们都是从选秀里走出来的好声音","coverImgUrl":"https://p1.music.126.net/GZ9aX7iS2g68UMONi8uyJg==/109951163061968765.jpg","creator":{"nickname":"-KooTo-","userId":17711631,"userType":0,"authStatus":0,"expertTags":["电子","另类/独立","欧美"],"experts":null},"subscribed":false,"trackCount":37,"userId":17711631,"playCount":683456,"bookCount":4935,"highQuality":false},{"id":52839260,"name":"那些曾经打动人心的女声！","coverImgUrl":"https://p1.music.126.net/kgutwD3WO2GEbius1zvUIQ==/7757054534256514.jpg","creator":{"nickname":"Yoli_小璐","userId":34094285,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":230,"userId":34094285,"playCount":110072,"bookCount":950,"highQuality":false},{"id":2146514084,"name":"徐佳莹/郁可唯/刘惜君/田馥甄","coverImgUrl":"https://p1.music.126.net/egxJ0te6WKFRcsLwP0T8zw==/109951163188533434.jpg","creator":{"nickname":"胡桃夹子zr","userId":492584302,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":39,"userId":492584302,"playCount":4093,"bookCount":125,"highQuality":false},{"id":312888169,"name":"刘惜君的\u201c惜式情歌\u201d","coverImgUrl":"https://p1.music.126.net/rSHIpSYMCYkH6g4pCsf7pA==/18957779486326544.jpg","creator":{"nickname":"摩天轮198112","userId":108254581,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":99,"userId":108254581,"playCount":13077,"bookCount":310,"highQuality":false},{"id":974321090,"name":"金玟岐/二珂/刘惜君/叶炫清/提莫","coverImgUrl":"https://p1.music.126.net/ZpEecRhnhaAh6851LUiwoA==/109951163051452192.jpg","creator":{"nickname":"希士比","userId":126755554,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":43,"userId":126755554,"playCount":2913,"bookCount":56,"highQuality":false},{"id":2131085578,"name":"刘惜君","coverImgUrl":"https://p1.music.126.net/xn1gjAGoFNHJJXNO0C6f6g==/2458507999732206.jpg","creator":{"nickname":"傷月华","userId":594160529,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":55,"userId":594160529,"playCount":29,"bookCount":0,"highQuality":false},{"id":998411238,"name":"『专辑/刘惜君』","coverImgUrl":"https://p1.music.126.net/Gqja4fAu7Wsumpgowmuj6Q==/109951163090545667.jpg","creator":{"nickname":"青皮桔菓子","userId":2415126,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":52,"userId":2415126,"playCount":47,"bookCount":0,"highQuality":false},{"id":28338243,"name":"刘惜君","coverImgUrl":"https://p1.music.126.net/M7-dRd7NF3g89-dNGwHqYg==/19085322835527598.jpg","creator":{"nickname":"星辰依旧","userId":26441442,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":110,"userId":26441442,"playCount":41490,"bookCount":989,"highQuality":false},{"id":2255475757,"name":"刘惜君丨像夜里的明珠","coverImgUrl":"https://p1.music.126.net/z3Be6qfX_P9wjAC2ZOyvyg==/18999560928570289.jpg","creator":{"nickname":"林里有羚羊","userId":324473044,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":21,"userId":324473044,"playCount":0,"bookCount":0,"highQuality":false}],"playlistCount":540}
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
         * playlists : [{"id":897849660,"name":"【刘惜君歌曲】评论量过999 排行榜（57首）","coverImgUrl":"https://p1.music.126.net/LUgEJeO1ldHSxvXBZ7kT-Q==/18890709277139952.jpg","creator":{"nickname":"意一念之间_获如是真我","userId":453399705,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":57,"userId":453399705,"playCount":23678,"bookCount":482,"highQuality":false},{"id":125330696,"name":"他们都是从选秀里走出来的好声音","coverImgUrl":"https://p1.music.126.net/GZ9aX7iS2g68UMONi8uyJg==/109951163061968765.jpg","creator":{"nickname":"-KooTo-","userId":17711631,"userType":0,"authStatus":0,"expertTags":["电子","另类/独立","欧美"],"experts":null},"subscribed":false,"trackCount":37,"userId":17711631,"playCount":683456,"bookCount":4935,"highQuality":false},{"id":52839260,"name":"那些曾经打动人心的女声！","coverImgUrl":"https://p1.music.126.net/kgutwD3WO2GEbius1zvUIQ==/7757054534256514.jpg","creator":{"nickname":"Yoli_小璐","userId":34094285,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":230,"userId":34094285,"playCount":110072,"bookCount":950,"highQuality":false},{"id":2146514084,"name":"徐佳莹/郁可唯/刘惜君/田馥甄","coverImgUrl":"https://p1.music.126.net/egxJ0te6WKFRcsLwP0T8zw==/109951163188533434.jpg","creator":{"nickname":"胡桃夹子zr","userId":492584302,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":39,"userId":492584302,"playCount":4093,"bookCount":125,"highQuality":false},{"id":312888169,"name":"刘惜君的\u201c惜式情歌\u201d","coverImgUrl":"https://p1.music.126.net/rSHIpSYMCYkH6g4pCsf7pA==/18957779486326544.jpg","creator":{"nickname":"摩天轮198112","userId":108254581,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":99,"userId":108254581,"playCount":13077,"bookCount":310,"highQuality":false},{"id":974321090,"name":"金玟岐/二珂/刘惜君/叶炫清/提莫","coverImgUrl":"https://p1.music.126.net/ZpEecRhnhaAh6851LUiwoA==/109951163051452192.jpg","creator":{"nickname":"希士比","userId":126755554,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":43,"userId":126755554,"playCount":2913,"bookCount":56,"highQuality":false},{"id":2131085578,"name":"刘惜君","coverImgUrl":"https://p1.music.126.net/xn1gjAGoFNHJJXNO0C6f6g==/2458507999732206.jpg","creator":{"nickname":"傷月华","userId":594160529,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":55,"userId":594160529,"playCount":29,"bookCount":0,"highQuality":false},{"id":998411238,"name":"『专辑/刘惜君』","coverImgUrl":"https://p1.music.126.net/Gqja4fAu7Wsumpgowmuj6Q==/109951163090545667.jpg","creator":{"nickname":"青皮桔菓子","userId":2415126,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":52,"userId":2415126,"playCount":47,"bookCount":0,"highQuality":false},{"id":28338243,"name":"刘惜君","coverImgUrl":"https://p1.music.126.net/M7-dRd7NF3g89-dNGwHqYg==/19085322835527598.jpg","creator":{"nickname":"星辰依旧","userId":26441442,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":110,"userId":26441442,"playCount":41490,"bookCount":989,"highQuality":false},{"id":2255475757,"name":"刘惜君丨像夜里的明珠","coverImgUrl":"https://p1.music.126.net/z3Be6qfX_P9wjAC2ZOyvyg==/18999560928570289.jpg","creator":{"nickname":"林里有羚羊","userId":324473044,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":21,"userId":324473044,"playCount":0,"bookCount":0,"highQuality":false}]
         * playlistCount : 540
         */

        private int playlistCount;
        private List<PlaylistsBean> playlists;

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public List<PlaylistsBean> getPlaylists() {
            return playlists;
        }

        public void setPlaylists(List<PlaylistsBean> playlists) {
            this.playlists = playlists;
        }

        public static class PlaylistsBean {
            /**
             * id : 897849660
             * name : 【刘惜君歌曲】评论量过999 排行榜（57首）
             * coverImgUrl : https://p1.music.126.net/LUgEJeO1ldHSxvXBZ7kT-Q==/18890709277139952.jpg
             * creator : {"nickname":"意一念之间_获如是真我","userId":453399705,"userType":0,"authStatus":0,"expertTags":null,"experts":null}
             * subscribed : false
             * trackCount : 57
             * userId : 453399705
             * playCount : 23678
             * bookCount : 482
             * highQuality : false
             */

            private String id;
            private String name;
            private String coverImgUrl;
            private CreatorBean creator;
            private boolean subscribed;
            private int trackCount;
            private int userId;
            private int playCount;
            private int bookCount;
            private boolean highQuality;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCoverImgUrl() {
                return coverImgUrl;
            }

            public void setCoverImgUrl(String coverImgUrl) {
                this.coverImgUrl = coverImgUrl;
            }

            public CreatorBean getCreator() {
                return creator;
            }

            public void setCreator(CreatorBean creator) {
                this.creator = creator;
            }

            public boolean isSubscribed() {
                return subscribed;
            }

            public void setSubscribed(boolean subscribed) {
                this.subscribed = subscribed;
            }

            public int getTrackCount() {
                return trackCount;
            }

            public void setTrackCount(int trackCount) {
                this.trackCount = trackCount;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getPlayCount() {
                return playCount;
            }

            public void setPlayCount(int playCount) {
                this.playCount = playCount;
            }

            public int getBookCount() {
                return bookCount;
            }

            public void setBookCount(int bookCount) {
                this.bookCount = bookCount;
            }

            public boolean isHighQuality() {
                return highQuality;
            }

            public void setHighQuality(boolean highQuality) {
                this.highQuality = highQuality;
            }

            public static class CreatorBean {
                /**
                 * nickname : 意一念之间_获如是真我
                 * userId : 453399705
                 * userType : 0
                 * authStatus : 0
                 * expertTags : null
                 * experts : null
                 */

                private String nickname;
                private int userId;
                private int userType;
                private int authStatus;
                private Object expertTags;
                private Object experts;

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public int getUserType() {
                    return userType;
                }

                public void setUserType(int userType) {
                    this.userType = userType;
                }

                public int getAuthStatus() {
                    return authStatus;
                }

                public void setAuthStatus(int authStatus) {
                    this.authStatus = authStatus;
                }

                public Object getExpertTags() {
                    return expertTags;
                }

                public void setExpertTags(Object expertTags) {
                    this.expertTags = expertTags;
                }

                public Object getExperts() {
                    return experts;
                }

                public void setExperts(Object experts) {
                    this.experts = experts;
                }
            }
        }
    }
}
