package com.yzx.xiaomusic.ui.main.music;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.leancloud.callback.SimpleFindCallBack;

import java.util.List;

/**
 * @author yzx
 * @date 2018/8/15
 * Description
 */
public class MusicPresenter extends CommonBasePresenter<MusicFragment, MusicModel> {
    @Override
    protected MusicModel getModel() {
        return new MusicModel();
    }

    public void getSongSheet() {
        mView.swipeRefreshLayout.setRefreshing(true);
        mModel.getSongSheet(new SimpleFindCallBack<AVObject>() {
            @Override
            public void onSuccess(List<AVObject> data) {
                mView.swipeRefreshLayout.setRefreshing(false);
//                mView.setData(data);
                Log.i("ygl", "onSuccess: " + data.toString());

            }

            @Override
            public void onFail(AVException e) {
                mView.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
