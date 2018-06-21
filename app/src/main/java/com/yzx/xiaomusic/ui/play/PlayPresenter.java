package com.yzx.xiaomusic.ui.play;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;

/**
 * Created by yzx on 2018/6/20.
 * Description
 */
public class PlayPresenter extends CommonBasePresenter<PlayFragment,PlayModel> {
    @Override
    protected PlayModel getModel() {
        return new PlayModel();
    }
}
