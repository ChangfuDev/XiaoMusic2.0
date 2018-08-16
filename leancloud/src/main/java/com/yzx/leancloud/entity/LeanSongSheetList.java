package com.yzx.leancloud.entity;

/**
 * Created by yzx on 2018/8/15.
 * Description
 */
public class LeanSongSheetList {

    /**
     * objectId : 5b73c8c89f54540035373235
     * updatedAt : 2018-08-15T06:31:36.135Z
     * createdAt : 2018-08-15T06:31:36.135Z
     * className : SongSheet
     * serverData : {"@type":"java.util.concurrent.ConcurrentHashMap","creator":{"objectId":"5b73c8c7d50eee00316fb1e7","updatedAt":"2018-08-15T06:31:35.962Z","createdAt":"2018-08-15T06:31:35.962Z","className":"_User","serverData":{"mobilePhoneNumber":"18339941400","emailVerified":false,"headUrl":"http://lc-cghdjwlo.cn-n1.lcfile.com/b93519e2fd3bbb760ae4.jpg","className":"_User","mobilePhoneVerified":false,"username":"18339941400","bgUrl":"http://lc-cghdjwlo.cn-n1.lcfile.com/369a69f819900e6bccfe.png"}},"name":"我喜欢的音乐","deletable":false,"bgUrl":"http://lc-cghdjwlo.cn-n1.lcfile.com/4bd8a31b902407ffc7f1.jpg"}
     */

    private String objectId;
    private String updatedAt;
    private String createdAt;
    private String className;
    private ServerDataBeanX serverData;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ServerDataBeanX getServerData() {
        return serverData;
    }

    public void setServerData(ServerDataBeanX serverData) {
        this.serverData = serverData;
    }

    public static class ServerDataBeanX {
        private CreatorBean creator;
        private String name;
        private boolean deletable;
        private String bgUrl;


        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isDeletable() {
            return deletable;
        }

        public void setDeletable(boolean deletable) {
            this.deletable = deletable;
        }

        public String getBgUrl() {
            return bgUrl;
        }

        public void setBgUrl(String bgUrl) {
            this.bgUrl = bgUrl;
        }

        public static class CreatorBean {
            /**
             * objectId : 5b73c8c7d50eee00316fb1e7
             * updatedAt : 2018-08-15T06:31:35.962Z
             * createdAt : 2018-08-15T06:31:35.962Z
             * className : _User
             * serverData : {"mobilePhoneNumber":"18339941400","emailVerified":false,"headUrl":"http://lc-cghdjwlo.cn-n1.lcfile.com/b93519e2fd3bbb760ae4.jpg","className":"_User","mobilePhoneVerified":false,"username":"18339941400","bgUrl":"http://lc-cghdjwlo.cn-n1.lcfile.com/369a69f819900e6bccfe.png"}
             */

            private String objectId;
            private String updatedAt;
            private String createdAt;
            private String className;
            private ServerDataBean serverData;

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public ServerDataBean getServerData() {
                return serverData;
            }

            public void setServerData(ServerDataBean serverData) {
                this.serverData = serverData;
            }

            public static class ServerDataBean {
                /**
                 * mobilePhoneNumber : 18339941400
                 * emailVerified : false
                 * headUrl : http://lc-cghdjwlo.cn-n1.lcfile.com/b93519e2fd3bbb760ae4.jpg
                 * className : _User
                 * mobilePhoneVerified : false
                 * username : 18339941400
                 * bgUrl : http://lc-cghdjwlo.cn-n1.lcfile.com/369a69f819900e6bccfe.png
                 */

                private String mobilePhoneNumber;
                private boolean emailVerified;
                private String headUrl;
                private String className;
                private boolean mobilePhoneVerified;
                private String username;
                private String bgUrl;

                public String getMobilePhoneNumber() {
                    return mobilePhoneNumber;
                }

                public void setMobilePhoneNumber(String mobilePhoneNumber) {
                    this.mobilePhoneNumber = mobilePhoneNumber;
                }

                public boolean isEmailVerified() {
                    return emailVerified;
                }

                public void setEmailVerified(boolean emailVerified) {
                    this.emailVerified = emailVerified;
                }

                public String getHeadUrl() {
                    return headUrl;
                }

                public void setHeadUrl(String headUrl) {
                    this.headUrl = headUrl;
                }

                public String getClassName() {
                    return className;
                }

                public void setClassName(String className) {
                    this.className = className;
                }

                public boolean isMobilePhoneVerified() {
                    return mobilePhoneVerified;
                }

                public void setMobilePhoneVerified(boolean mobilePhoneVerified) {
                    this.mobilePhoneVerified = mobilePhoneVerified;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getBgUrl() {
                    return bgUrl;
                }

                public void setBgUrl(String bgUrl) {
                    this.bgUrl = bgUrl;
                }
            }
        }
    }
}
