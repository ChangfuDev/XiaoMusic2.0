package com.yzx.xiaomusic.ui.main.music;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.leancloud.callback.SimpleFindCallBack;

/**
 * @author yzx
 * @date 2018/8/15
 * Description
 */
public class MusicModel extends CommonBaseModel {

    public void getSongSheet(SimpleFindCallBack<AVObject> simpleFindCallBack) {

        AVQuery<AVObject> songSheet = AVQuery.getQuery("SongSheet");
        songSheet.whereEqualTo("creator", AVUser.getCurrentUser());
        songSheet.include("creator");
        songSheet.findInBackground(simpleFindCallBack);
    }
}
