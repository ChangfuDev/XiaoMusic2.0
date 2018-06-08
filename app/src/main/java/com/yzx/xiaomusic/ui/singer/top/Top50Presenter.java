package com.yzx.xiaomusic.ui.singer.top;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.xiaomusic.ui.singer.SingerDetailModel;

public class Top50Presenter extends CommonBasePresenter<Top50Fragment, SingerDetailModel> {
    @Override
    protected SingerDetailModel getModel() {
        return new SingerDetailModel();
    }
}
