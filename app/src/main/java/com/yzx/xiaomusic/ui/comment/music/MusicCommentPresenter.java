package com.yzx.xiaomusic.ui.comment.music;

import android.util.Log;

import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.commonlibrary.base.mvp.CommonMvpObserver;
import com.yzx.xiaomusic.model.entity.comment.MusicComment;

/**
 * Created by yzx on 2018/7/12.
 * Description
 */
public class MusicCommentPresenter extends CommonBasePresenter<MusicCommentFragment, MusicCommentModel> {
    @Override
    protected MusicCommentModel getModel() {
        return new MusicCommentModel();
    }

    public void getComment(String id, int offset) {
        if (offset == 0) {
            mView.showLoadingLayout();
        }
        Log.i("ygl", "onSuccess: " + offset);
        Log.i("ygl", "onSuccess: " + mView.loadService.getCurrentCallback().getSimpleName());
        mModel.getComment(id, offset, new CommonMvpObserver<MusicComment>() {
            @Override
            protected void onSuccess(MusicComment musicComment) {
                if (offset == 0) {
                    if (musicComment.getTotal() > 0) {
                        Log.i(TAG, "onSuccess: ");
                        mView.setData(musicComment);
                        mView.showSuccessLayout();
                    } else {
                        mView.showEmptyLayout();
                    }
                } else {
                    mView.addData(musicComment.getComments());
                    if (musicComment.isMore()) {
                        mView.smartRefreshLayout.finishLoadMore();
                    } else {
                        mView.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
            }

            @Override
            protected void onFail(int code, String errorMsg) {
                super.onFail(code, errorMsg);
                if (offset == 0) {
                    mView.showErrorLayout();
                } else {
                    mView.smartRefreshLayout.finishLoadMore(false);
                }
            }
        });
    }
}
