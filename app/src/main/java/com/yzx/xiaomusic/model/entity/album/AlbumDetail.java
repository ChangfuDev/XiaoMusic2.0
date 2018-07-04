package com.yzx.xiaomusic.model.entity.album;

import com.yzx.commonlibrary.base.BaseResposeBody;

import java.util.List;

/**
 * Created by yzx on 2018/6/14.
 * Description  专辑详情
 */
public class AlbumDetail extends BaseResposeBody {


    /**
     * album : {"songs":[{"starred":false,"popularity":70,"starredNum":0,"playedNum":0,"dayPlays":0,"hearTime":0,"mp3Url":"http://m2.music.126.net/ACh0S2LiCA56FvjLMT7L2Q==/2068181371870561.mp3","rtUrls":null,"crbt":null,"bMusic":{"sr":44100,"dfsId":2068181371870561,"bitrate":96000,"playTime":222903,"volumeDelta":-1.18,"name":"就现在","id":24845046,"size":2699520,"extension":"mp3"},"rtUrl":null,"ringtone":"600902000008015762","alias":[],"position":1,"duration":222903,"status":0,"artists":[{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}],"copyrightId":0,"score":70,"audition":null,"fee":0,"disc":"1","album":{"songs":[],"paid":false,"onSale":false,"blurPicUrl":"http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg","companyId":0,"pic":2307874906756673,"tags":"","alias":[],"status":1,"description":"","subType":"现场版","artists":[{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}],"copyrightId":0,"publishTime":1367856000007,"company":"梦响强音","picId":2307874906756673,"artist":{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"","id":0,"img1v1Id_str":"18686200114669622"},"briefDesc":"","commentThreadId":"R_AL_3_2457012","picUrl":"http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg","name":"就现在","id":2457012,"type":"EP/Single","size":1},"hMusic":{"sr":44100,"dfsId":2002210674204000,"bitrate":320000,"playTime":222903,"volumeDelta":-1.55,"name":"就现在","id":24845044,"size":8936098,"extension":"mp3"},"mMusic":{"sr":44100,"dfsId":2832341953174381,"bitrate":160000,"playTime":222903,"volumeDelta":-1.08,"name":"就现在","id":24845045,"size":4481698,"extension":"mp3"},"lMusic":{"sr":44100,"dfsId":2068181371870561,"bitrate":96000,"playTime":222903,"volumeDelta":-1.18,"name":"就现在","id":24845046,"size":2699520,"extension":"mp3"},"no":1,"mvid":0,"ftype":0,"rtype":0,"rurl":null,"copyFrom":"","commentThreadId":"R_SO_4_26343111","name":"就现在","id":26343111}],"paid":false,"onSale":false,"blurPicUrl":"http://p4.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg","companyId":0,"pic":2307874906756673,"tags":"","alias":[],"status":1,"description":"","subType":"现场版","artists":[{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}],"copyrightId":0,"publishTime":1367856000007,"company":"梦响强音","picId":2307874906756673,"artist":{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":104,"picId":3294136847236898,"albumSize":21,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p4.music.126.net/nAdTO1WFrSRaQimVirCKBg==/3294136847236898.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"},"briefDesc":"","commentThreadId":"R_AL_3_2457012","picUrl":"http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg","name":"就现在","id":2457012,"type":"EP/Single","size":1,"info":{"commentThread":{"id":"R_AL_3_2457012","resourceInfo":{"id":2457012,"userId":-1,"name":"就现在","imgUrl":null,"creator":null},"resourceType":3,"commentCount":14,"likedCount":0,"shareCount":16,"hotCount":0,"latestLikedUsers":null,"resourceOwnerId":-1,"resourceTitle":"就现在","resourceId":2457012},"latestLikedUsers":null,"liked":false,"comments":null,"resourceType":3,"resourceId":2457012,"threadId":"R_AL_3_2457012","likedCount":0,"shareCount":16,"commentCount":14}}
     */

    private AlbumBeanX album;

    public AlbumBeanX getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBeanX album) {
        this.album = album;
    }

    public static class AlbumBeanX {
        /**
         * songs : [{"starred":false,"popularity":70,"starredNum":0,"playedNum":0,"dayPlays":0,"hearTime":0,"mp3Url":"http://m2.music.126.net/ACh0S2LiCA56FvjLMT7L2Q==/2068181371870561.mp3","rtUrls":null,"crbt":null,"bMusic":{"sr":44100,"dfsId":2068181371870561,"bitrate":96000,"playTime":222903,"volumeDelta":-1.18,"name":"就现在","id":24845046,"size":2699520,"extension":"mp3"},"rtUrl":null,"ringtone":"600902000008015762","alias":[],"position":1,"duration":222903,"status":0,"artists":[{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}],"copyrightId":0,"score":70,"audition":null,"fee":0,"disc":"1","album":{"songs":[],"paid":false,"onSale":false,"blurPicUrl":"http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg","companyId":0,"pic":2307874906756673,"tags":"","alias":[],"status":1,"description":"","subType":"现场版","artists":[{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}],"copyrightId":0,"publishTime":1367856000007,"company":"梦响强音","picId":2307874906756673,"artist":{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"","id":0,"img1v1Id_str":"18686200114669622"},"briefDesc":"","commentThreadId":"R_AL_3_2457012","picUrl":"http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg","name":"就现在","id":2457012,"type":"EP/Single","size":1},"hMusic":{"sr":44100,"dfsId":2002210674204000,"bitrate":320000,"playTime":222903,"volumeDelta":-1.55,"name":"就现在","id":24845044,"size":8936098,"extension":"mp3"},"mMusic":{"sr":44100,"dfsId":2832341953174381,"bitrate":160000,"playTime":222903,"volumeDelta":-1.08,"name":"就现在","id":24845045,"size":4481698,"extension":"mp3"},"lMusic":{"sr":44100,"dfsId":2068181371870561,"bitrate":96000,"playTime":222903,"volumeDelta":-1.18,"name":"就现在","id":24845046,"size":2699520,"extension":"mp3"},"no":1,"mvid":0,"ftype":0,"rtype":0,"rurl":null,"copyFrom":"","commentThreadId":"R_SO_4_26343111","name":"就现在","id":26343111}]
         * paid : false
         * onSale : false
         * blurPicUrl : http://p4.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg
         * companyId : 0
         * pic : 2307874906756673
         * tags :
         * alias : []
         * status : 1
         * description :
         * subType : 现场版
         * artists : [{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}]
         * copyrightId : 0
         * publishTime : 1367856000007
         * company : 梦响强音
         * picId : 2307874906756673
         * artist : {"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":104,"picId":3294136847236898,"albumSize":21,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p4.music.126.net/nAdTO1WFrSRaQimVirCKBg==/3294136847236898.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}
         * briefDesc :
         * commentThreadId : R_AL_3_2457012
         * picUrl : http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg
         * name : 就现在
         * id : 2457012
         * type : EP/Single
         * size : 1
         * info : {"commentThread":{"id":"R_AL_3_2457012","resourceInfo":{"id":2457012,"userId":-1,"name":"就现在","imgUrl":null,"creator":null},"resourceType":3,"commentCount":14,"likedCount":0,"shareCount":16,"hotCount":0,"latestLikedUsers":null,"resourceOwnerId":-1,"resourceTitle":"就现在","resourceId":2457012},"latestLikedUsers":null,"liked":false,"comments":null,"resourceType":3,"resourceId":2457012,"threadId":"R_AL_3_2457012","likedCount":0,"shareCount":16,"commentCount":14}
         */

        private boolean paid;
        private boolean onSale;
        private String blurPicUrl;
        private int companyId;
        private long pic;
        private String tags;
        private int status;
        private String description;
        private String subType;
        private int copyrightId;
        private long publishTime;
        private String company;
        private long picId;
        private ArtistBean artist;
        private String briefDesc;
        private String commentThreadId;
        private String picUrl;
        private String name;
        private int id;
        private String type;
        private int size;
        private InfoBean info;
        private List<SongsBean> songs;
        private List<?> alias;
        private List<ArtistsBeanXX> artists;

        public boolean isPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }

        public boolean isOnSale() {
            return onSale;
        }

        public void setOnSale(boolean onSale) {
            this.onSale = onSale;
        }

        public String getBlurPicUrl() {
            return blurPicUrl;
        }

        public void setBlurPicUrl(String blurPicUrl) {
            this.blurPicUrl = blurPicUrl;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public long getPic() {
            return pic;
        }

        public void setPic(long pic) {
            this.pic = pic;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSubType() {
            return subType;
        }

        public void setSubType(String subType) {
            this.subType = subType;
        }

        public int getCopyrightId() {
            return copyrightId;
        }

        public void setCopyrightId(int copyrightId) {
            this.copyrightId = copyrightId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public long getPicId() {
            return picId;
        }

        public void setPicId(long picId) {
            this.picId = picId;
        }

        public ArtistBean getArtist() {
            return artist;
        }

        public void setArtist(ArtistBean artist) {
            this.artist = artist;
        }

        public String getBriefDesc() {
            return briefDesc;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<SongsBean> getSongs() {
            return songs;
        }

        public void setSongs(List<SongsBean> songs) {
            this.songs = songs;
        }

        public List<?> getAlias() {
            return alias;
        }

        public void setAlias(List<?> alias) {
            this.alias = alias;
        }

        public List<ArtistsBeanXX> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBeanXX> artists) {
            this.artists = artists;
        }

        public static class ArtistBean {
            /**
             * img1v1Id : 18686200114669622
             * topicPerson : 0
             * alias : []
             * musicSize : 104
             * picId : 3294136847236898
             * albumSize : 21
             * img1v1Url : http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
             * trans :
             * briefDesc :
             * picUrl : http://p4.music.126.net/nAdTO1WFrSRaQimVirCKBg==/3294136847236898.jpg
             * name : 吴莫愁
             * id : 166009
             * img1v1Id_str : 18686200114669622
             */

            private long img1v1Id;
            private int topicPerson;
            private int musicSize;
            private long picId;
            private int albumSize;
            private String img1v1Url;
            private String trans;
            private String briefDesc;
            private String picUrl;
            private String name;
            private int id;
            private String img1v1Id_str;
            private List<?> alias;

            public long getImg1v1Id() {
                return img1v1Id;
            }

            public void setImg1v1Id(long img1v1Id) {
                this.img1v1Id = img1v1Id;
            }

            public int getTopicPerson() {
                return topicPerson;
            }

            public void setTopicPerson(int topicPerson) {
                this.topicPerson = topicPerson;
            }

            public int getMusicSize() {
                return musicSize;
            }

            public void setMusicSize(int musicSize) {
                this.musicSize = musicSize;
            }

            public long getPicId() {
                return picId;
            }

            public void setPicId(long picId) {
                this.picId = picId;
            }

            public int getAlbumSize() {
                return albumSize;
            }

            public void setAlbumSize(int albumSize) {
                this.albumSize = albumSize;
            }

            public String getImg1v1Url() {
                return img1v1Url;
            }

            public void setImg1v1Url(String img1v1Url) {
                this.img1v1Url = img1v1Url;
            }

            public String getTrans() {
                return trans;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }

            public String getBriefDesc() {
                return briefDesc;
            }

            public void setBriefDesc(String briefDesc) {
                this.briefDesc = briefDesc;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg1v1Id_str() {
                return img1v1Id_str;
            }

            public void setImg1v1Id_str(String img1v1Id_str) {
                this.img1v1Id_str = img1v1Id_str;
            }

            public List<?> getAlias() {
                return alias;
            }

            public void setAlias(List<?> alias) {
                this.alias = alias;
            }
        }

        public static class InfoBean {
            /**
             * commentThread : {"id":"R_AL_3_2457012","resourceInfo":{"id":2457012,"userId":-1,"name":"就现在","imgUrl":null,"creator":null},"resourceType":3,"commentCount":14,"likedCount":0,"shareCount":16,"hotCount":0,"latestLikedUsers":null,"resourceOwnerId":-1,"resourceTitle":"就现在","resourceId":2457012}
             * latestLikedUsers : null
             * liked : false
             * comments : null
             * resourceType : 3
             * resourceId : 2457012
             * threadId : R_AL_3_2457012
             * likedCount : 0
             * shareCount : 16
             * commentCount : 14
             */

            private CommentThreadBean commentThread;
            private Object latestLikedUsers;
            private boolean liked;
            private Object comments;
            private int resourceType;
            private int resourceId;
            private String threadId;
            private int likedCount;
            private int shareCount;
            private int commentCount;

            public CommentThreadBean getCommentThread() {
                return commentThread;
            }

            public void setCommentThread(CommentThreadBean commentThread) {
                this.commentThread = commentThread;
            }

            public Object getLatestLikedUsers() {
                return latestLikedUsers;
            }

            public void setLatestLikedUsers(Object latestLikedUsers) {
                this.latestLikedUsers = latestLikedUsers;
            }

            public boolean isLiked() {
                return liked;
            }

            public void setLiked(boolean liked) {
                this.liked = liked;
            }

            public Object getComments() {
                return comments;
            }

            public void setComments(Object comments) {
                this.comments = comments;
            }

            public int getResourceType() {
                return resourceType;
            }

            public void setResourceType(int resourceType) {
                this.resourceType = resourceType;
            }

            public int getResourceId() {
                return resourceId;
            }

            public void setResourceId(int resourceId) {
                this.resourceId = resourceId;
            }

            public String getThreadId() {
                return threadId;
            }

            public void setThreadId(String threadId) {
                this.threadId = threadId;
            }

            public int getLikedCount() {
                return likedCount;
            }

            public void setLikedCount(int likedCount) {
                this.likedCount = likedCount;
            }

            public int getShareCount() {
                return shareCount;
            }

            public void setShareCount(int shareCount) {
                this.shareCount = shareCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public static class CommentThreadBean {
                /**
                 * id : R_AL_3_2457012
                 * resourceInfo : {"id":2457012,"userId":-1,"name":"就现在","imgUrl":null,"creator":null}
                 * resourceType : 3
                 * commentCount : 14
                 * likedCount : 0
                 * shareCount : 16
                 * hotCount : 0
                 * latestLikedUsers : null
                 * resourceOwnerId : -1
                 * resourceTitle : 就现在
                 * resourceId : 2457012
                 */

                private String id;
                private ResourceInfoBean resourceInfo;
                private int resourceType;
                private int commentCount;
                private int likedCount;
                private int shareCount;
                private int hotCount;
                private Object latestLikedUsers;
                private int resourceOwnerId;
                private String resourceTitle;
                private int resourceId;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public ResourceInfoBean getResourceInfo() {
                    return resourceInfo;
                }

                public void setResourceInfo(ResourceInfoBean resourceInfo) {
                    this.resourceInfo = resourceInfo;
                }

                public int getResourceType() {
                    return resourceType;
                }

                public void setResourceType(int resourceType) {
                    this.resourceType = resourceType;
                }

                public int getCommentCount() {
                    return commentCount;
                }

                public void setCommentCount(int commentCount) {
                    this.commentCount = commentCount;
                }

                public int getLikedCount() {
                    return likedCount;
                }

                public void setLikedCount(int likedCount) {
                    this.likedCount = likedCount;
                }

                public int getShareCount() {
                    return shareCount;
                }

                public void setShareCount(int shareCount) {
                    this.shareCount = shareCount;
                }

                public int getHotCount() {
                    return hotCount;
                }

                public void setHotCount(int hotCount) {
                    this.hotCount = hotCount;
                }

                public Object getLatestLikedUsers() {
                    return latestLikedUsers;
                }

                public void setLatestLikedUsers(Object latestLikedUsers) {
                    this.latestLikedUsers = latestLikedUsers;
                }

                public int getResourceOwnerId() {
                    return resourceOwnerId;
                }

                public void setResourceOwnerId(int resourceOwnerId) {
                    this.resourceOwnerId = resourceOwnerId;
                }

                public String getResourceTitle() {
                    return resourceTitle;
                }

                public void setResourceTitle(String resourceTitle) {
                    this.resourceTitle = resourceTitle;
                }

                public int getResourceId() {
                    return resourceId;
                }

                public void setResourceId(int resourceId) {
                    this.resourceId = resourceId;
                }

                public static class ResourceInfoBean {
                    /**
                     * id : 2457012
                     * userId : -1
                     * name : 就现在
                     * imgUrl : null
                     * creator : null
                     */

                    private int id;
                    private int userId;
                    private String name;
                    private Object imgUrl;
                    private Object creator;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getUserId() {
                        return userId;
                    }

                    public void setUserId(int userId) {
                        this.userId = userId;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getImgUrl() {
                        return imgUrl;
                    }

                    public void setImgUrl(Object imgUrl) {
                        this.imgUrl = imgUrl;
                    }

                    public Object getCreator() {
                        return creator;
                    }

                    public void setCreator(Object creator) {
                        this.creator = creator;
                    }
                }
            }
        }

        public static class SongsBean {
            /**
             * starred : false
             * popularity : 70
             * starredNum : 0
             * playedNum : 0
             * dayPlays : 0
             * hearTime : 0
             * mp3Url : http://m2.music.126.net/ACh0S2LiCA56FvjLMT7L2Q==/2068181371870561.mp3
             * rtUrls : null
             * crbt : null
             * bMusic : {"sr":44100,"dfsId":2068181371870561,"bitrate":96000,"playTime":222903,"volumeDelta":-1.18,"name":"就现在","id":24845046,"size":2699520,"extension":"mp3"}
             * rtUrl : null
             * ringtone : 600902000008015762
             * alias : []
             * position : 1
             * duration : 222903
             * status : 0
             * artists : [{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}]
             * copyrightId : 0
             * score : 70
             * audition : null
             * fee : 0
             * disc : 1
             * album : {"songs":[],"paid":false,"onSale":false,"blurPicUrl":"http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg","companyId":0,"pic":2307874906756673,"tags":"","alias":[],"status":1,"description":"","subType":"现场版","artists":[{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}],"copyrightId":0,"publishTime":1367856000007,"company":"梦响强音","picId":2307874906756673,"artist":{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"","id":0,"img1v1Id_str":"18686200114669622"},"briefDesc":"","commentThreadId":"R_AL_3_2457012","picUrl":"http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg","name":"就现在","id":2457012,"type":"EP/Single","size":1}
             * hMusic : {"sr":44100,"dfsId":2002210674204000,"bitrate":320000,"playTime":222903,"volumeDelta":-1.55,"name":"就现在","id":24845044,"size":8936098,"extension":"mp3"}
             * mMusic : {"sr":44100,"dfsId":2832341953174381,"bitrate":160000,"playTime":222903,"volumeDelta":-1.08,"name":"就现在","id":24845045,"size":4481698,"extension":"mp3"}
             * lMusic : {"sr":44100,"dfsId":2068181371870561,"bitrate":96000,"playTime":222903,"volumeDelta":-1.18,"name":"就现在","id":24845046,"size":2699520,"extension":"mp3"}
             * no : 1
             * mvid : 0
             * ftype : 0
             * rtype : 0
             * rurl : null
             * copyFrom :
             * commentThreadId : R_SO_4_26343111
             * name : 就现在
             * id : 26343111
             */

            private boolean starred;
            private int popularity;
            private int starredNum;
            private int playedNum;
            private int dayPlays;
            private int hearTime;
            private String mp3Url;
            private Object rtUrls;
            private Object crbt;
            private Object rtUrl;
            private String ringtone;
            private int position;
            private int duration;
            private int status;
            private int copyrightId;
            private int score;
            private Object audition;
            private int fee;
            private String disc;
            private AlbumBean album;
            private int no;
            private int mvid;
            private int ftype;
            private int rtype;
            private Object rurl;
            private String copyFrom;
            private String commentThreadId;
            private String name;
            private int id;
            private List<?> alias;
            private List<ArtistsBeanX> artists;

            public boolean isStarred() {
                return starred;
            }

            public void setStarred(boolean starred) {
                this.starred = starred;
            }

            public int getPopularity() {
                return popularity;
            }

            public void setPopularity(int popularity) {
                this.popularity = popularity;
            }

            public int getStarredNum() {
                return starredNum;
            }

            public void setStarredNum(int starredNum) {
                this.starredNum = starredNum;
            }

            public int getPlayedNum() {
                return playedNum;
            }

            public void setPlayedNum(int playedNum) {
                this.playedNum = playedNum;
            }

            public int getDayPlays() {
                return dayPlays;
            }

            public void setDayPlays(int dayPlays) {
                this.dayPlays = dayPlays;
            }

            public int getHearTime() {
                return hearTime;
            }

            public void setHearTime(int hearTime) {
                this.hearTime = hearTime;
            }

            public String getMp3Url() {
                return mp3Url;
            }

            public void setMp3Url(String mp3Url) {
                this.mp3Url = mp3Url;
            }

            public Object getRtUrls() {
                return rtUrls;
            }

            public void setRtUrls(Object rtUrls) {
                this.rtUrls = rtUrls;
            }

            public Object getCrbt() {
                return crbt;
            }

            public void setCrbt(Object crbt) {
                this.crbt = crbt;
            }


            public Object getRtUrl() {
                return rtUrl;
            }

            public void setRtUrl(Object rtUrl) {
                this.rtUrl = rtUrl;
            }

            public String getRingtone() {
                return ringtone;
            }

            public void setRingtone(String ringtone) {
                this.ringtone = ringtone;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCopyrightId() {
                return copyrightId;
            }

            public void setCopyrightId(int copyrightId) {
                this.copyrightId = copyrightId;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public Object getAudition() {
                return audition;
            }

            public void setAudition(Object audition) {
                this.audition = audition;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public String getDisc() {
                return disc;
            }

            public void setDisc(String disc) {
                this.disc = disc;
            }

            public AlbumBean getAlbum() {
                return album;
            }

            public void setAlbum(AlbumBean album) {
                this.album = album;
            }


            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public int getMvid() {
                return mvid;
            }

            public void setMvid(int mvid) {
                this.mvid = mvid;
            }

            public int getFtype() {
                return ftype;
            }

            public void setFtype(int ftype) {
                this.ftype = ftype;
            }

            public int getRtype() {
                return rtype;
            }

            public void setRtype(int rtype) {
                this.rtype = rtype;
            }

            public Object getRurl() {
                return rurl;
            }

            public void setRurl(Object rurl) {
                this.rurl = rurl;
            }

            public String getCopyFrom() {
                return copyFrom;
            }

            public void setCopyFrom(String copyFrom) {
                this.copyFrom = copyFrom;
            }

            public String getCommentThreadId() {
                return commentThreadId;
            }

            public void setCommentThreadId(String commentThreadId) {
                this.commentThreadId = commentThreadId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<?> getAlias() {
                return alias;
            }

            public void setAlias(List<?> alias) {
                this.alias = alias;
            }

            public List<ArtistsBeanX> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsBeanX> artists) {
                this.artists = artists;
            }
            public static class AlbumBean {
                /**
                 * songs : []
                 * paid : false
                 * onSale : false
                 * blurPicUrl : http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg
                 * companyId : 0
                 * pic : 2307874906756673
                 * tags :
                 * alias : []
                 * status : 1
                 * description :
                 * subType : 现场版
                 * artists : [{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"吴莫愁","id":166009,"img1v1Id_str":"18686200114669622"}]
                 * copyrightId : 0
                 * publishTime : 1367856000007
                 * company : 梦响强音
                 * picId : 2307874906756673
                 * artist : {"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"musicSize":0,"picId":0,"albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","briefDesc":"","picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"","id":0,"img1v1Id_str":"18686200114669622"}
                 * briefDesc :
                 * commentThreadId : R_AL_3_2457012
                 * picUrl : http://p3.music.126.net/3Mvh9qJjiRTGHEun_FQ8Ig==/2307874906756673.jpg
                 * name : 就现在
                 * id : 2457012
                 * type : EP/Single
                 * size : 1
                 */

                private boolean paid;
                private boolean onSale;
                private String blurPicUrl;
                private int companyId;
                private long pic;
                private String tags;
                private int status;
                private String description;
                private String subType;
                private int copyrightId;
                private long publishTime;
                private String company;
                private long picId;
                private ArtistBeanX artist;
                private String briefDesc;
                private String commentThreadId;
                private String picUrl;
                private String name;
                private int id;
                private String type;
                private int size;
                private List<?> songs;
                private List<?> alias;
                private List<ArtistsBean> artists;

                public boolean isPaid() {
                    return paid;
                }

                public void setPaid(boolean paid) {
                    this.paid = paid;
                }

                public boolean isOnSale() {
                    return onSale;
                }

                public void setOnSale(boolean onSale) {
                    this.onSale = onSale;
                }

                public String getBlurPicUrl() {
                    return blurPicUrl;
                }

                public void setBlurPicUrl(String blurPicUrl) {
                    this.blurPicUrl = blurPicUrl;
                }

                public int getCompanyId() {
                    return companyId;
                }

                public void setCompanyId(int companyId) {
                    this.companyId = companyId;
                }

                public long getPic() {
                    return pic;
                }

                public void setPic(long pic) {
                    this.pic = pic;
                }

                public String getTags() {
                    return tags;
                }

                public void setTags(String tags) {
                    this.tags = tags;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getSubType() {
                    return subType;
                }

                public void setSubType(String subType) {
                    this.subType = subType;
                }

                public int getCopyrightId() {
                    return copyrightId;
                }

                public void setCopyrightId(int copyrightId) {
                    this.copyrightId = copyrightId;
                }

                public long getPublishTime() {
                    return publishTime;
                }

                public void setPublishTime(long publishTime) {
                    this.publishTime = publishTime;
                }

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public long getPicId() {
                    return picId;
                }

                public void setPicId(long picId) {
                    this.picId = picId;
                }

                public ArtistBeanX getArtist() {
                    return artist;
                }

                public void setArtist(ArtistBeanX artist) {
                    this.artist = artist;
                }

                public String getBriefDesc() {
                    return briefDesc;
                }

                public void setBriefDesc(String briefDesc) {
                    this.briefDesc = briefDesc;
                }

                public String getCommentThreadId() {
                    return commentThreadId;
                }

                public void setCommentThreadId(String commentThreadId) {
                    this.commentThreadId = commentThreadId;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public List<?> getSongs() {
                    return songs;
                }

                public void setSongs(List<?> songs) {
                    this.songs = songs;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }

                public List<ArtistsBean> getArtists() {
                    return artists;
                }

                public void setArtists(List<ArtistsBean> artists) {
                    this.artists = artists;
                }

                public static class ArtistBeanX {
                    /**
                     * img1v1Id : 18686200114669622
                     * topicPerson : 0
                     * alias : []
                     * musicSize : 0
                     * picId : 0
                     * albumSize : 0
                     * img1v1Url : http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
                     * trans :
                     * briefDesc :
                     * picUrl : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                     * name :
                     * id : 0
                     * img1v1Id_str : 18686200114669622
                     */

                    private long img1v1Id;
                    private int topicPerson;
                    private int musicSize;
                    private int picId;
                    private int albumSize;
                    private String img1v1Url;
                    private String trans;
                    private String briefDesc;
                    private String picUrl;
                    private String name;
                    private int id;
                    private String img1v1Id_str;
                    private List<?> alias;

                    public long getImg1v1Id() {
                        return img1v1Id;
                    }

                    public void setImg1v1Id(long img1v1Id) {
                        this.img1v1Id = img1v1Id;
                    }

                    public int getTopicPerson() {
                        return topicPerson;
                    }

                    public void setTopicPerson(int topicPerson) {
                        this.topicPerson = topicPerson;
                    }

                    public int getMusicSize() {
                        return musicSize;
                    }

                    public void setMusicSize(int musicSize) {
                        this.musicSize = musicSize;
                    }

                    public int getPicId() {
                        return picId;
                    }

                    public void setPicId(int picId) {
                        this.picId = picId;
                    }

                    public int getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(int albumSize) {
                        this.albumSize = albumSize;
                    }

                    public String getImg1v1Url() {
                        return img1v1Url;
                    }

                    public void setImg1v1Url(String img1v1Url) {
                        this.img1v1Url = img1v1Url;
                    }

                    public String getTrans() {
                        return trans;
                    }

                    public void setTrans(String trans) {
                        this.trans = trans;
                    }

                    public String getBriefDesc() {
                        return briefDesc;
                    }

                    public void setBriefDesc(String briefDesc) {
                        this.briefDesc = briefDesc;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getImg1v1Id_str() {
                        return img1v1Id_str;
                    }

                    public void setImg1v1Id_str(String img1v1Id_str) {
                        this.img1v1Id_str = img1v1Id_str;
                    }

                    public List<?> getAlias() {
                        return alias;
                    }

                    public void setAlias(List<?> alias) {
                        this.alias = alias;
                    }
                }

                public static class ArtistsBean {
                    /**
                     * img1v1Id : 18686200114669622
                     * topicPerson : 0
                     * alias : []
                     * musicSize : 0
                     * picId : 0
                     * albumSize : 0
                     * img1v1Url : http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
                     * trans :
                     * briefDesc :
                     * picUrl : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                     * name : 吴莫愁
                     * id : 166009
                     * img1v1Id_str : 18686200114669622
                     */

                    private long img1v1Id;
                    private int topicPerson;
                    private int musicSize;
                    private int picId;
                    private int albumSize;
                    private String img1v1Url;
                    private String trans;
                    private String briefDesc;
                    private String picUrl;
                    private String name;
                    private int id;
                    private String img1v1Id_str;
                    private List<?> alias;

                    public long getImg1v1Id() {
                        return img1v1Id;
                    }

                    public void setImg1v1Id(long img1v1Id) {
                        this.img1v1Id = img1v1Id;
                    }

                    public int getTopicPerson() {
                        return topicPerson;
                    }

                    public void setTopicPerson(int topicPerson) {
                        this.topicPerson = topicPerson;
                    }

                    public int getMusicSize() {
                        return musicSize;
                    }

                    public void setMusicSize(int musicSize) {
                        this.musicSize = musicSize;
                    }

                    public int getPicId() {
                        return picId;
                    }

                    public void setPicId(int picId) {
                        this.picId = picId;
                    }

                    public int getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(int albumSize) {
                        this.albumSize = albumSize;
                    }

                    public String getImg1v1Url() {
                        return img1v1Url;
                    }

                    public void setImg1v1Url(String img1v1Url) {
                        this.img1v1Url = img1v1Url;
                    }

                    public String getTrans() {
                        return trans;
                    }

                    public void setTrans(String trans) {
                        this.trans = trans;
                    }

                    public String getBriefDesc() {
                        return briefDesc;
                    }

                    public void setBriefDesc(String briefDesc) {
                        this.briefDesc = briefDesc;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getImg1v1Id_str() {
                        return img1v1Id_str;
                    }

                    public void setImg1v1Id_str(String img1v1Id_str) {
                        this.img1v1Id_str = img1v1Id_str;
                    }

                    public List<?> getAlias() {
                        return alias;
                    }

                    public void setAlias(List<?> alias) {
                        this.alias = alias;
                    }
                }
            }


            public static class ArtistsBeanX {
                /**
                 * img1v1Id : 18686200114669622
                 * topicPerson : 0
                 * alias : []
                 * musicSize : 0
                 * picId : 0
                 * albumSize : 0
                 * img1v1Url : http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
                 * trans :
                 * briefDesc :
                 * picUrl : http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                 * name : 吴莫愁
                 * id : 166009
                 * img1v1Id_str : 18686200114669622
                 */

                private long img1v1Id;
                private int topicPerson;
                private int musicSize;
                private int picId;
                private int albumSize;
                private String img1v1Url;
                private String trans;
                private String briefDesc;
                private String picUrl;
                private String name;
                private int id;
                private String img1v1Id_str;
                private List<?> alias;

                public long getImg1v1Id() {
                    return img1v1Id;
                }

                public void setImg1v1Id(long img1v1Id) {
                    this.img1v1Id = img1v1Id;
                }

                public int getTopicPerson() {
                    return topicPerson;
                }

                public void setTopicPerson(int topicPerson) {
                    this.topicPerson = topicPerson;
                }

                public int getMusicSize() {
                    return musicSize;
                }

                public void setMusicSize(int musicSize) {
                    this.musicSize = musicSize;
                }

                public int getPicId() {
                    return picId;
                }

                public void setPicId(int picId) {
                    this.picId = picId;
                }

                public int getAlbumSize() {
                    return albumSize;
                }

                public void setAlbumSize(int albumSize) {
                    this.albumSize = albumSize;
                }

                public String getImg1v1Url() {
                    return img1v1Url;
                }

                public void setImg1v1Url(String img1v1Url) {
                    this.img1v1Url = img1v1Url;
                }

                public String getTrans() {
                    return trans;
                }

                public void setTrans(String trans) {
                    this.trans = trans;
                }

                public String getBriefDesc() {
                    return briefDesc;
                }

                public void setBriefDesc(String briefDesc) {
                    this.briefDesc = briefDesc;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImg1v1Id_str() {
                    return img1v1Id_str;
                }

                public void setImg1v1Id_str(String img1v1Id_str) {
                    this.img1v1Id_str = img1v1Id_str;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }

        public static class ArtistsBeanXX {
            /**
             * img1v1Id : 18686200114669622
             * topicPerson : 0
             * alias : []
             * musicSize : 0
             * picId : 0
             * albumSize : 0
             * img1v1Url : http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
             * trans :
             * briefDesc :
             * picUrl : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
             * name : 吴莫愁
             * id : 166009
             * img1v1Id_str : 18686200114669622
             */

            private long img1v1Id;
            private int topicPerson;
            private int musicSize;
            private int picId;
            private int albumSize;
            private String img1v1Url;
            private String trans;
            private String briefDesc;
            private String picUrl;
            private String name;
            private int id;
            private String img1v1Id_str;
            private List<?> alias;

            public long getImg1v1Id() {
                return img1v1Id;
            }

            public void setImg1v1Id(long img1v1Id) {
                this.img1v1Id = img1v1Id;
            }

            public int getTopicPerson() {
                return topicPerson;
            }

            public void setTopicPerson(int topicPerson) {
                this.topicPerson = topicPerson;
            }

            public int getMusicSize() {
                return musicSize;
            }

            public void setMusicSize(int musicSize) {
                this.musicSize = musicSize;
            }

            public int getPicId() {
                return picId;
            }

            public void setPicId(int picId) {
                this.picId = picId;
            }

            public int getAlbumSize() {
                return albumSize;
            }

            public void setAlbumSize(int albumSize) {
                this.albumSize = albumSize;
            }

            public String getImg1v1Url() {
                return img1v1Url;
            }

            public void setImg1v1Url(String img1v1Url) {
                this.img1v1Url = img1v1Url;
            }

            public String getTrans() {
                return trans;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }

            public String getBriefDesc() {
                return briefDesc;
            }

            public void setBriefDesc(String briefDesc) {
                this.briefDesc = briefDesc;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg1v1Id_str() {
                return img1v1Id_str;
            }

            public void setImg1v1Id_str(String img1v1Id_str) {
                this.img1v1Id_str = img1v1Id_str;
            }

            public List<?> getAlias() {
                return alias;
            }

            public void setAlias(List<?> alias) {
                this.alias = alias;
            }
        }
    }
}
