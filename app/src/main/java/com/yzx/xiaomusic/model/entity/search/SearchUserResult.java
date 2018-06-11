package com.yzx.xiaomusic.model.entity.search;

import com.yzx.commonlibrary.base.BaseResposeBody;

import java.util.List;

public class SearchUserResult extends BaseResposeBody {

    /**
     * result : {"userprofiles":[{"defaultAvatar":false,"province":320000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/648_dRc4srlSR_6X-7G6ew==/109951163060885481.jpg","accountStatus":0,"gender":1,"city":320100,"birthday":753033600000,"userId":308544838,"userType":4,"nickname":"刘莱斯","signature":"刘莱斯，南京独立音乐人，特小特小的那种 --------------------------------------------音乐节、演出请私信","description":"","detailDescription":"","avatarImgId":109951163060885481,"backgroundImgId":109951163021229773,"backgroundUrl":"https://p1.music.126.net/NIrlmJmqx0kbpnoL5TqtAQ==/109951163021229773.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":0,"remarkName":null,"avatarImgIdStr":"109951163060885481","backgroundImgIdStr":"109951163021229773","avatarImgId_str":"109951163060885481","followeds":36041,"follows":11,"alg":"alg_user_basic","playlistCount":4,"playlistBeSubscribedCount":172},{"defaultAvatar":false,"province":500000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/9PLEpejAOkFhtQ3PZA7-PA==/7775746232717985.jpg","accountStatus":0,"gender":1,"city":500101,"birthday":-2209017600000,"userId":82226370,"userType":0,"nickname":"刘杀鸡","signature":"吾所成之事，不可逆也！","description":"知名电竞达人","detailDescription":"知名电竞达人","avatarImgId":7775746232717985,"backgroundImgId":2002210674180200,"backgroundUrl":"https://p1.music.126.net/45Nu4EqvFqK_kQj6BkPwcw==/2002210674180200.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"avatarImgIdStr":"7775746232717985","backgroundImgIdStr":"2002210674180200","followeds":24968,"follows":0,"alg":"alg_user_basic","playlistCount":5,"playlistBeSubscribedCount":10273},{"defaultAvatar":false,"province":330000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/8FdM2KnFd5a8nlbx_wi9pA==/1391981734511326.jpg","accountStatus":0,"gender":2,"city":330100,"birthday":732038400000,"userId":311587064,"userType":1,"nickname":"刘飞儿faye","signature":"斗鱼主播，房号265438或搜索刘飞儿。","description":"人气女主播","detailDescription":"人气女主播","avatarImgId":1391981734511326,"backgroundImgId":3265549573067532,"backgroundUrl":"https://p1.music.126.net/pF3fboUl4OOCO_OgRSQOtg==/3265549573067532.jpg","authority":3,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":0,"remarkName":null,"avatarImgIdStr":"1391981734511326","backgroundImgIdStr":"3265549573067532","followeds":9232,"follows":0,"alg":"alg_user_basic","playlistCount":2,"playlistBeSubscribedCount":245},{"defaultAvatar":false,"province":120000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/x6OGBrPhf_k_BUfkmlQ6yQ==/109951163320728448.jpg","accountStatus":0,"gender":2,"city":120101,"birthday":960357695161,"userId":635877177,"userType":4,"nickname":"刘增瞳","signature":"代表作《多想留在你身边》《后来的我们》","description":"","detailDescription":"","avatarImgId":109951163320728448,"backgroundImgId":109951163270939694,"backgroundUrl":"https://p1.music.126.net/__hrykXktZOOcbsyV8DtWg==/109951163270939694.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951163320728448","backgroundImgIdStr":"109951163270939694","avatarImgId_str":"109951163320728448","followeds":8698,"follows":3,"alg":"alg_user_basic","playlistCount":1,"playlistBeSubscribedCount":92},{"defaultAvatar":false,"province":440000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/7RJgM-_dBL2PKkz_wuGSqw==/109951163093631992.jpg","accountStatus":0,"gender":1,"city":440300,"birthday":529603200000,"userId":126953459,"userType":4,"nickname":"刘江叶","signature":"来！了解一下！","description":"","detailDescription":"","avatarImgId":109951163093631992,"backgroundImgId":109951163322436041,"backgroundUrl":"https://p1.music.126.net/eLtEyRU5kMaRR-rC_JJmRw==/109951163322436041.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951163093631992","backgroundImgIdStr":"109951163322436041","avatarImgId_str":"109951163093631992","followeds":5652,"follows":29,"alg":"alg_user_basic","playlistCount":17,"playlistBeSubscribedCount":19},{"defaultAvatar":false,"province":440000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/djks4vQorDDZz1GNTPnvlg==/109951163307374170.jpg","accountStatus":0,"gender":2,"city":440100,"birthday":883324800000,"userId":1396896232,"userType":4,"nickname":"刘初寻","signature":"不玩\u2026","description":"","detailDescription":"","avatarImgId":109951163307374170,"backgroundImgId":109951162868128395,"backgroundUrl":"https://p1.music.126.net/2zSNIqTcpHL2jIvU6hG0EA==/109951162868128395.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"avatarImgIdStr":"109951163307374170","backgroundImgIdStr":"109951162868128395","avatarImgId_str":"109951163307374170","followeds":4402,"follows":1,"alg":"alg_user_basic","playlistCount":1,"playlistBeSubscribedCount":0},{"defaultAvatar":false,"province":130000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/EnLCFOMThUxUSqOGY7Sjjw==/109951163288818319.jpg","accountStatus":0,"gender":1,"city":130100,"birthday":515303284530,"userId":37006612,"userType":4,"nickname":"劉伍壹","signature":"独立唱作人 刘键 新浪微博：@庄上青年","description":"独立音乐人","detailDescription":"独立音乐人","avatarImgId":109951163288818319,"backgroundImgId":109951162953625073,"backgroundUrl":"https://p1.music.126.net/5MePcz_vH7wLFyyY-7o8LQ==/109951162953625073.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"avatarImgIdStr":"109951163288818319","backgroundImgIdStr":"109951162953625073","avatarImgId_str":"109951163288818319","followeds":3226,"follows":25,"alg":"alg_user_basic","playlistCount":12,"playlistBeSubscribedCount":2639},{"defaultAvatar":false,"province":110000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/pVxOeDvBvN-rsee_NtWM8A==/3440371886650901.jpg","accountStatus":0,"gender":1,"city":110101,"birthday":612892800000,"userId":88647699,"userType":4,"nickname":"刘陆伟释魂乐队冥燚","signature":"唱作人、音乐制作人、吉他手、录音师、混音师，释魂乐队创建者。中国传媒大学录音系硕士。合作联系491183149","description":"","detailDescription":"","avatarImgId":3440371886650901,"backgroundImgId":2002210674180204,"backgroundUrl":"https://p1.music.126.net/5L9yqWa_UnlHtlp7li5PAg==/2002210674180204.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"avatarImgIdStr":"3440371886650901","backgroundImgIdStr":"2002210674180204","followeds":2893,"follows":4,"alg":"alg_user_basic","playlistCount":3,"playlistBeSubscribedCount":112},{"defaultAvatar":false,"province":640000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/PhJ4noK-tQ52C3A1-_cRow==/109951162897344180.jpg","accountStatus":0,"gender":1,"city":640100,"birthday":1493827200000,"userId":131459295,"userType":4,"nickname":"刘沄","signature":"合作私信。","description":"","detailDescription":"","avatarImgId":109951162897344180,"backgroundImgId":109951163207786627,"backgroundUrl":"https://p1.music.126.net/gMzVzPDClNbX-iWRFv2h0w==/109951163207786627.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951162897344180","backgroundImgIdStr":"109951163207786627","avatarImgId_str":"109951162897344180","followeds":2240,"follows":38,"alg":"alg_user_basic","playlistCount":3,"playlistBeSubscribedCount":93},{"defaultAvatar":false,"province":1000000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/FpXK2YMcoRkk9Co5bi6Iow==/109951162949167233.jpg","accountStatus":0,"gender":2,"city":1007700,"birthday":949852800000,"userId":377143217,"userType":4,"nickname":"刘晓晨Beryl","signature":"做你们的小晨喵","description":"","detailDescription":"","avatarImgId":109951162949167233,"backgroundImgId":109951163287448933,"backgroundUrl":"https://p1.music.126.net/ofaFVj9lionYWlCWIJjmew==/109951163287448933.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951162949167233","backgroundImgIdStr":"109951163287448933","avatarImgId_str":"109951162949167233","followeds":2148,"follows":1,"alg":"alg_user_basic","playlistCount":2,"playlistBeSubscribedCount":188}],"userprofileCount":600}
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
         * userprofiles : [{"defaultAvatar":false,"province":320000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/648_dRc4srlSR_6X-7G6ew==/109951163060885481.jpg","accountStatus":0,"gender":1,"city":320100,"birthday":753033600000,"userId":308544838,"userType":4,"nickname":"刘莱斯","signature":"刘莱斯，南京独立音乐人，特小特小的那种 --------------------------------------------音乐节、演出请私信","description":"","detailDescription":"","avatarImgId":109951163060885481,"backgroundImgId":109951163021229773,"backgroundUrl":"https://p1.music.126.net/NIrlmJmqx0kbpnoL5TqtAQ==/109951163021229773.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":0,"remarkName":null,"avatarImgIdStr":"109951163060885481","backgroundImgIdStr":"109951163021229773","avatarImgId_str":"109951163060885481","followeds":36041,"follows":11,"alg":"alg_user_basic","playlistCount":4,"playlistBeSubscribedCount":172},{"defaultAvatar":false,"province":500000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/9PLEpejAOkFhtQ3PZA7-PA==/7775746232717985.jpg","accountStatus":0,"gender":1,"city":500101,"birthday":-2209017600000,"userId":82226370,"userType":0,"nickname":"刘杀鸡","signature":"吾所成之事，不可逆也！","description":"知名电竞达人","detailDescription":"知名电竞达人","avatarImgId":7775746232717985,"backgroundImgId":2002210674180200,"backgroundUrl":"https://p1.music.126.net/45Nu4EqvFqK_kQj6BkPwcw==/2002210674180200.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"avatarImgIdStr":"7775746232717985","backgroundImgIdStr":"2002210674180200","followeds":24968,"follows":0,"alg":"alg_user_basic","playlistCount":5,"playlistBeSubscribedCount":10273},{"defaultAvatar":false,"province":330000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/8FdM2KnFd5a8nlbx_wi9pA==/1391981734511326.jpg","accountStatus":0,"gender":2,"city":330100,"birthday":732038400000,"userId":311587064,"userType":1,"nickname":"刘飞儿faye","signature":"斗鱼主播，房号265438或搜索刘飞儿。","description":"人气女主播","detailDescription":"人气女主播","avatarImgId":1391981734511326,"backgroundImgId":3265549573067532,"backgroundUrl":"https://p1.music.126.net/pF3fboUl4OOCO_OgRSQOtg==/3265549573067532.jpg","authority":3,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":0,"remarkName":null,"avatarImgIdStr":"1391981734511326","backgroundImgIdStr":"3265549573067532","followeds":9232,"follows":0,"alg":"alg_user_basic","playlistCount":2,"playlistBeSubscribedCount":245},{"defaultAvatar":false,"province":120000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/x6OGBrPhf_k_BUfkmlQ6yQ==/109951163320728448.jpg","accountStatus":0,"gender":2,"city":120101,"birthday":960357695161,"userId":635877177,"userType":4,"nickname":"刘增瞳","signature":"代表作《多想留在你身边》《后来的我们》","description":"","detailDescription":"","avatarImgId":109951163320728448,"backgroundImgId":109951163270939694,"backgroundUrl":"https://p1.music.126.net/__hrykXktZOOcbsyV8DtWg==/109951163270939694.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951163320728448","backgroundImgIdStr":"109951163270939694","avatarImgId_str":"109951163320728448","followeds":8698,"follows":3,"alg":"alg_user_basic","playlistCount":1,"playlistBeSubscribedCount":92},{"defaultAvatar":false,"province":440000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/7RJgM-_dBL2PKkz_wuGSqw==/109951163093631992.jpg","accountStatus":0,"gender":1,"city":440300,"birthday":529603200000,"userId":126953459,"userType":4,"nickname":"刘江叶","signature":"来！了解一下！","description":"","detailDescription":"","avatarImgId":109951163093631992,"backgroundImgId":109951163322436041,"backgroundUrl":"https://p1.music.126.net/eLtEyRU5kMaRR-rC_JJmRw==/109951163322436041.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951163093631992","backgroundImgIdStr":"109951163322436041","avatarImgId_str":"109951163093631992","followeds":5652,"follows":29,"alg":"alg_user_basic","playlistCount":17,"playlistBeSubscribedCount":19},{"defaultAvatar":false,"province":440000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/djks4vQorDDZz1GNTPnvlg==/109951163307374170.jpg","accountStatus":0,"gender":2,"city":440100,"birthday":883324800000,"userId":1396896232,"userType":4,"nickname":"刘初寻","signature":"不玩\u2026","description":"","detailDescription":"","avatarImgId":109951163307374170,"backgroundImgId":109951162868128395,"backgroundUrl":"https://p1.music.126.net/2zSNIqTcpHL2jIvU6hG0EA==/109951162868128395.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"avatarImgIdStr":"109951163307374170","backgroundImgIdStr":"109951162868128395","avatarImgId_str":"109951163307374170","followeds":4402,"follows":1,"alg":"alg_user_basic","playlistCount":1,"playlistBeSubscribedCount":0},{"defaultAvatar":false,"province":130000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/EnLCFOMThUxUSqOGY7Sjjw==/109951163288818319.jpg","accountStatus":0,"gender":1,"city":130100,"birthday":515303284530,"userId":37006612,"userType":4,"nickname":"劉伍壹","signature":"独立唱作人 刘键 新浪微博：@庄上青年","description":"独立音乐人","detailDescription":"独立音乐人","avatarImgId":109951163288818319,"backgroundImgId":109951162953625073,"backgroundUrl":"https://p1.music.126.net/5MePcz_vH7wLFyyY-7o8LQ==/109951162953625073.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"avatarImgIdStr":"109951163288818319","backgroundImgIdStr":"109951162953625073","avatarImgId_str":"109951163288818319","followeds":3226,"follows":25,"alg":"alg_user_basic","playlistCount":12,"playlistBeSubscribedCount":2639},{"defaultAvatar":false,"province":110000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/pVxOeDvBvN-rsee_NtWM8A==/3440371886650901.jpg","accountStatus":0,"gender":1,"city":110101,"birthday":612892800000,"userId":88647699,"userType":4,"nickname":"刘陆伟释魂乐队冥燚","signature":"唱作人、音乐制作人、吉他手、录音师、混音师，释魂乐队创建者。中国传媒大学录音系硕士。合作联系491183149","description":"","detailDescription":"","avatarImgId":3440371886650901,"backgroundImgId":2002210674180204,"backgroundUrl":"https://p1.music.126.net/5L9yqWa_UnlHtlp7li5PAg==/2002210674180204.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"avatarImgIdStr":"3440371886650901","backgroundImgIdStr":"2002210674180204","followeds":2893,"follows":4,"alg":"alg_user_basic","playlistCount":3,"playlistBeSubscribedCount":112},{"defaultAvatar":false,"province":640000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/PhJ4noK-tQ52C3A1-_cRow==/109951162897344180.jpg","accountStatus":0,"gender":1,"city":640100,"birthday":1493827200000,"userId":131459295,"userType":4,"nickname":"刘沄","signature":"合作私信。","description":"","detailDescription":"","avatarImgId":109951162897344180,"backgroundImgId":109951163207786627,"backgroundUrl":"https://p1.music.126.net/gMzVzPDClNbX-iWRFv2h0w==/109951163207786627.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951162897344180","backgroundImgIdStr":"109951163207786627","avatarImgId_str":"109951162897344180","followeds":2240,"follows":38,"alg":"alg_user_basic","playlistCount":3,"playlistBeSubscribedCount":93},{"defaultAvatar":false,"province":1000000,"authStatus":1,"followed":false,"avatarUrl":"https://p1.music.126.net/FpXK2YMcoRkk9Co5bi6Iow==/109951162949167233.jpg","accountStatus":0,"gender":2,"city":1007700,"birthday":949852800000,"userId":377143217,"userType":4,"nickname":"刘晓晨Beryl","signature":"做你们的小晨喵","description":"","detailDescription":"","avatarImgId":109951162949167233,"backgroundImgId":109951163287448933,"backgroundUrl":"https://p1.music.126.net/ofaFVj9lionYWlCWIJjmew==/109951163287448933.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951162949167233","backgroundImgIdStr":"109951163287448933","avatarImgId_str":"109951162949167233","followeds":2148,"follows":1,"alg":"alg_user_basic","playlistCount":2,"playlistBeSubscribedCount":188}]
         * userprofileCount : 600
         */

        private int userprofileCount;
        private List<UserprofilesBean> userprofiles;

        public int getUserprofileCount() {
            return userprofileCount;
        }

        public void setUserprofileCount(int userprofileCount) {
            this.userprofileCount = userprofileCount;
        }

        public List<UserprofilesBean> getUserprofiles() {
            return userprofiles;
        }

        public void setUserprofiles(List<UserprofilesBean> userprofiles) {
            this.userprofiles = userprofiles;
        }

        public static class UserprofilesBean {
            /**
             * defaultAvatar : false
             * province : 320000
             * authStatus : 1
             * followed : false
             * avatarUrl : https://p1.music.126.net/648_dRc4srlSR_6X-7G6ew==/109951163060885481.jpg
             * accountStatus : 0
             * gender : 1
             * city : 320100
             * birthday : 753033600000
             * userId : 308544838
             * userType : 4
             * nickname : 刘莱斯
             * signature : 刘莱斯，南京独立音乐人，特小特小的那种 --------------------------------------------音乐节、演出请私信
             * description :
             * detailDescription :
             * avatarImgId : 109951163060885481
             * backgroundImgId : 109951163021229773
             * backgroundUrl : https://p1.music.126.net/NIrlmJmqx0kbpnoL5TqtAQ==/109951163021229773.jpg
             * authority : 0
             * mutual : false
             * expertTags : null
             * experts : null
             * djStatus : 10
             * vipType : 0
             * remarkName : null
             * avatarImgIdStr : 109951163060885481
             * backgroundImgIdStr : 109951163021229773
             * avatarImgId_str : 109951163060885481
             * followeds : 36041
             * follows : 11
             * alg : alg_user_basic
             * playlistCount : 4
             * playlistBeSubscribedCount : 172
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
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private String avatarImgId_str;
            private int followeds;
            private int follows;
            private String alg;
            private int playlistCount;
            private int playlistBeSubscribedCount;

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

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public String getAvatarImgId_str() {
                return avatarImgId_str;
            }

            public void setAvatarImgId_str(String avatarImgId_str) {
                this.avatarImgId_str = avatarImgId_str;
            }

            public int getFolloweds() {
                return followeds;
            }

            public void setFolloweds(int followeds) {
                this.followeds = followeds;
            }

            public int getFollows() {
                return follows;
            }

            public void setFollows(int follows) {
                this.follows = follows;
            }

            public String getAlg() {
                return alg;
            }

            public void setAlg(String alg) {
                this.alg = alg;
            }

            public int getPlaylistCount() {
                return playlistCount;
            }

            public void setPlaylistCount(int playlistCount) {
                this.playlistCount = playlistCount;
            }

            public int getPlaylistBeSubscribedCount() {
                return playlistBeSubscribedCount;
            }

            public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
                this.playlistBeSubscribedCount = playlistBeSubscribedCount;
            }
        }
    }
}
