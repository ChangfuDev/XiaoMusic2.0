package com.yzx.xiaomusic.ui.play.lyric;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;

/**
 *
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class LyricPresenter extends CommonBasePresenter<LyricFragment,LyricModel> {
    @Override
    protected LyricModel getModel() {
        return new LyricModel();
    }
}
