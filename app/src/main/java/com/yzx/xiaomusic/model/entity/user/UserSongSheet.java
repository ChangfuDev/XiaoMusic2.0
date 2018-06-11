package com.yzx.xiaomusic.model.entity.user;

import com.yzx.commonlibrary.base.BaseResposeBody;

import java.util.List;

public class UserSongSheet extends BaseResposeBody {

    /**
     * more : false
     * playlist : [{"subscribers":[],"subscribed":false,"creator":{"defaultAvatar":false,"province":440000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/HVhnDFZkqddM77Nz_WLEGA==/3100622790379217.jpg","accountStatus":0,"gender":2,"city":440300,"birthday":-2209017600000,"userId":1360597,"userType":0,"nickname":"刘惜君网易云音乐粉丝团","signature":"2009快乐女声刘惜君网易云资讯台","description":"","detailDescription":"","avatarImgId":3100622790379217,"backgroundImgId":3113816929912867,"backgroundUrl":"http://p1.music.126.net/VxgFmP1scJX2Dr6AxeKKBQ==/3113816929912867.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"backgroundImgIdStr":"3113816929912867","avatarImgIdStr":"3100622790379217"},"artists":null,"tracks":null,"status":0,"adType":0,"trackNumberUpdateTime":1370832939110,"ordered":false,"tags":[],"subscribedCount":3,"cloudTrackCount":0,"description":null,"privacy":0,"newImported":false,"coverImgId":528865094323612,"createTime":1370832165585,"updateTime":1370832165585,"specialType":5,"anonimous":false,"trackUpdateTime":1522949480858,"trackCount":14,"coverImgUrl":"http://p1.music.126.net/wV9dIEtPqhh8tBtHWGTttA==/528865094323612.jpg","highQuality":false,"totalDuration":0,"commentThreadId":"A_PL_0_1660513","userId":1360597,"playCount":37,"name":"我喜欢的音乐","id":1660513},{"subscribers":[],"subscribed":false,"creator":{"defaultAvatar":false,"province":440000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/HVhnDFZkqddM77Nz_WLEGA==/3100622790379217.jpg","accountStatus":0,"gender":2,"city":440300,"birthday":-2209017600000,"userId":1360597,"userType":0,"nickname":"刘惜君网易云音乐粉丝团","signature":"2009快乐女声刘惜君网易云资讯台","description":"","detailDescription":"","avatarImgId":3100622790379217,"backgroundImgId":3113816929912867,"backgroundUrl":"http://p1.music.126.net/VxgFmP1scJX2Dr6AxeKKBQ==/3113816929912867.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"backgroundImgIdStr":"3113816929912867","avatarImgIdStr":"3100622790379217"},"artists":null,"tracks":null,"status":0,"adType":0,"trackNumberUpdateTime":1370834489112,"ordered":false,"tags":["流行","清新","治愈"],"subscribedCount":762,"cloudTrackCount":0,"description":"刘惜君","privacy":0,"newImported":false,"coverImgId":3142404232235811,"createTime":1370834424784,"updateTime":1375626363593,"specialType":0,"anonimous":false,"trackUpdateTime":1522891296205,"trackCount":24,"coverImgUrl":"http://p1.music.126.net/Mc9oWZ2msuzVHYIg1P61xQ==/3142404232235811.jpg","highQuality":false,"totalDuration":0,"commentThreadId":"A_PL_0_1661742","userId":1360597,"playCount":49761,"name":"刘惜君","id":1661742},{"subscribers":[],"subscribed":false,"creator":{"defaultAvatar":false,"province":110000,"authStatus":1,"followed":false,"avatarUrl":"http://p1.music.126.net/Nfz9FEtnWQGsCG8gjSpuww==/1374389539221080.jpg","accountStatus":0,"gender":2,"city":110101,"birthday":-2209017600000,"userId":188186,"userType":2,"nickname":"刘惜君","signature":"","description":"新生代实力歌手","detailDescription":"新生代实力歌手","avatarImgId":1374389539221080,"backgroundImgId":2002210674180202,"backgroundUrl":"http://p1.music.126.net/pmHS4fcQtcNEGewNb5HRhg==/2002210674180202.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"backgroundImgIdStr":"2002210674180202","avatarImgIdStr":"1374389539221080"},"artists":null,"tracks":null,"status":0,"adType":0,"trackNumberUpdateTime":1375587475774,"ordered":false,"tags":[],"subscribedCount":1328,"cloudTrackCount":0,"description":null,"privacy":0,"newImported":false,"coverImgId":528865097576300,"createTime":1362650088614,"updateTime":1380252961010,"specialType":5,"anonimous":false,"trackUpdateTime":1521987724569,"trackCount":9,"coverImgUrl":"http://p1.music.126.net/Uf4OYIJgQqnCQw8JURyU0g==/528865097576300.jpg","highQuality":false,"totalDuration":0,"commentThreadId":"A_PL_0_233246","userId":188186,"playCount":152171,"name":"刘惜君喜欢的音乐","id":233246}]
     */

    private boolean more;
    private List<PlaylistBean> playlist;

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public List<PlaylistBean> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<PlaylistBean> playlist) {
        this.playlist = playlist;
    }

    public static class PlaylistBean {
        /**
         * subscribers : []
         * subscribed : false
         * creator : {"defaultAvatar":false,"province":440000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/HVhnDFZkqddM77Nz_WLEGA==/3100622790379217.jpg","accountStatus":0,"gender":2,"city":440300,"birthday":-2209017600000,"userId":1360597,"userType":0,"nickname":"刘惜君网易云音乐粉丝团","signature":"2009快乐女声刘惜君网易云资讯台","description":"","detailDescription":"","avatarImgId":3100622790379217,"backgroundImgId":3113816929912867,"backgroundUrl":"http://p1.music.126.net/VxgFmP1scJX2Dr6AxeKKBQ==/3113816929912867.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"backgroundImgIdStr":"3113816929912867","avatarImgIdStr":"3100622790379217"}
         * artists : null
         * tracks : null
         * status : 0
         * adType : 0
         * trackNumberUpdateTime : 1370832939110
         * ordered : false
         * tags : []
         * subscribedCount : 3
         * cloudTrackCount : 0
         * description : null
         * privacy : 0
         * newImported : false
         * coverImgId : 528865094323612
         * createTime : 1370832165585
         * updateTime : 1370832165585
         * specialType : 5
         * anonimous : false
         * trackUpdateTime : 1522949480858
         * trackCount : 14
         * coverImgUrl : http://p1.music.126.net/wV9dIEtPqhh8tBtHWGTttA==/528865094323612.jpg
         * highQuality : false
         * totalDuration : 0
         * commentThreadId : A_PL_0_1660513
         * userId : 1360597
         * playCount : 37
         * name : 我喜欢的音乐
         * id : 1660513
         */

        private boolean subscribed;
        private CreatorBean creator;
        private Object artists;
        private Object tracks;
        private int status;
        private int adType;
        private long trackNumberUpdateTime;
        private boolean ordered;
        private int subscribedCount;
        private int cloudTrackCount;
        private String description;
        private int privacy;
        private boolean newImported;
        private long coverImgId;
        private long createTime;
        private long updateTime;
        private int specialType;
        private boolean anonimous;
        private long trackUpdateTime;
        private int trackCount;
        private String coverImgUrl;
        private boolean highQuality;
        private int totalDuration;
        private String commentThreadId;
        private int userId;
        private int playCount;
        private String name;
        private String id;
        private List<?> subscribers;
        private List<?> tags;

        public boolean isSubscribed() {
            return subscribed;
        }

        public void setSubscribed(boolean subscribed) {
            this.subscribed = subscribed;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public Object getArtists() {
            return artists;
        }

        public void setArtists(Object artists) {
            this.artists = artists;
        }

        public Object getTracks() {
            return tracks;
        }

        public void setTracks(Object tracks) {
            this.tracks = tracks;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

        public long getTrackNumberUpdateTime() {
            return trackNumberUpdateTime;
        }

        public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
            this.trackNumberUpdateTime = trackNumberUpdateTime;
        }

        public boolean isOrdered() {
            return ordered;
        }

        public void setOrdered(boolean ordered) {
            this.ordered = ordered;
        }

        public int getSubscribedCount() {
            return subscribedCount;
        }

        public void setSubscribedCount(int subscribedCount) {
            this.subscribedCount = subscribedCount;
        }

        public int getCloudTrackCount() {
            return cloudTrackCount;
        }

        public void setCloudTrackCount(int cloudTrackCount) {
            this.cloudTrackCount = cloudTrackCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPrivacy() {
            return privacy;
        }

        public void setPrivacy(int privacy) {
            this.privacy = privacy;
        }

        public boolean isNewImported() {
            return newImported;
        }

        public void setNewImported(boolean newImported) {
            this.newImported = newImported;
        }

        public long getCoverImgId() {
            return coverImgId;
        }

        public void setCoverImgId(long coverImgId) {
            this.coverImgId = coverImgId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getSpecialType() {
            return specialType;
        }

        public void setSpecialType(int specialType) {
            this.specialType = specialType;
        }

        public boolean isAnonimous() {
            return anonimous;
        }

        public void setAnonimous(boolean anonimous) {
            this.anonimous = anonimous;
        }

        public long getTrackUpdateTime() {
            return trackUpdateTime;
        }

        public void setTrackUpdateTime(long trackUpdateTime) {
            this.trackUpdateTime = trackUpdateTime;
        }

        public int getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(int trackCount) {
            this.trackCount = trackCount;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public boolean isHighQuality() {
            return highQuality;
        }

        public void setHighQuality(boolean highQuality) {
            this.highQuality = highQuality;
        }

        public int getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(int totalDuration) {
            this.totalDuration = totalDuration;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<?> getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(List<?> subscribers) {
            this.subscribers = subscribers;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        public static class CreatorBean {
            /**
             * defaultAvatar : false
             * province : 440000
             * authStatus : 0
             * followed : false
             * avatarUrl : http://p1.music.126.net/HVhnDFZkqddM77Nz_WLEGA==/3100622790379217.jpg
             * accountStatus : 0
             * gender : 2
             * city : 440300
             * birthday : -2209017600000
             * userId : 1360597
             * userType : 0
             * nickname : 刘惜君网易云音乐粉丝团
             * signature : 2009快乐女声刘惜君网易云资讯台
             * description :
             * detailDescription :
             * avatarImgId : 3100622790379217
             * backgroundImgId : 3113816929912867
             * backgroundUrl : http://p1.music.126.net/VxgFmP1scJX2Dr6AxeKKBQ==/3113816929912867.jpg
             * authority : 0
             * mutual : false
             * expertTags : null
             * experts : null
             * djStatus : 0
             * vipType : 0
             * remarkName : null
             * backgroundImgIdStr : 3113816929912867
             * avatarImgIdStr : 3100622790379217
             */

            private boolean defaultAvatar;
            private int province;
            private int authStatus;
            private boolean followed;
            private String avatarUrl;
            private int accountStatus;
            private int gender;
            private int city;
            private long birthday;
            private String userId;
            private int userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private long avatarImgId;
            private long backgroundImgId;
            private String backgroundUrl;
            private int authority;
            private boolean mutual;
            private Object expertTags;
            private Object experts;
            private int djStatus;
            private int vipType;
            private Object remarkName;
            private String backgroundImgIdStr;
            private String avatarImgIdStr;

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public long getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(long avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public long getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(long backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public int getAuthority() {
                return authority;
            }

            public void setAuthority(int authority) {
                this.authority = authority;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
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

            public int getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(int djStatus) {
                this.djStatus = djStatus;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }
        }
    }
}
