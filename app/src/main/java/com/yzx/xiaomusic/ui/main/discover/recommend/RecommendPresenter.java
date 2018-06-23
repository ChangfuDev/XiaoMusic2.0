package com.yzx.xiaomusic.ui.main.discover.recommend;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;

/**
 * Created by yzx on 2018/6/21.
 * Description
 */
public class RecommendPresenter extends CommonBasePresenter<RecommendFragment, RecommendModel> {
    @Override
    protected RecommendModel getModel() {
        return new RecommendModel();
    }


}
