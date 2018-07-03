package com.yzx.xiaomusic.ui.main.discover.friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.othershe.library.NiceImageView;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.utils.GlideUtils;

import butterknife.BindView;

/**
 * Created by yzx on 2018/6/21.
 * Description
 */
public class FriendFragment extends BaseFragment {

    @BindView(R.id.iv_head)
    NiceImageView ivHead;


    @Override
    protected int initContentViewId() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        GlideUtils.loadImg(getContext(), "http://p1.music.126.net/1-quMYddvOZwuG6A5j9s-g==/109951163368297427.jpg", ivHead);
    }
}
