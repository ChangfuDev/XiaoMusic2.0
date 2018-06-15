package com.yzx.xiaomusic.ui.common;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.ui.adapter.CommonTagAdapter;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.widget.ShapeTextView;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_DES;
import static com.yzx.xiaomusic.Constant.KEY_TAG;
import static com.yzx.xiaomusic.Constant.KEY_TITLE;

/**
 * Created by yzx on 2018/6/12.
 * Description 专辑、歌单封面等信息
 */
public class CoverInfoFragment extends BaseFragment {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_little_bg)
    ImageView ivLittleBg;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.ll_tags)
    LinearLayout llTags;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.stv_save)
    ShapeTextView stvSave;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_cover_info;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator(R.anim.fragment_a_enter,R.anim.fragment_a_exit);
    }

//    @Override
//    public FragmentAnimator getFragmentAnimator() {
//        FragmentAnimator fragmentAnimator = super.getFragmentAnimator();
//        fragmentAnimator.setEnter(R.anim.fragment_a_enter);
//        fragmentAnimator.setExit(R.anim.fragment_a_exit);
//        fragmentAnimator.setPopEnter(R.anim.fragment_a_pop_enter);
//        fragmentAnimator.setPopExit(R.anim.fragment_a_pop_exit);
//        return fragmentAnimator;
//    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        Bundle arguments = getArguments();

        String cover = arguments.getString(KEY_COVER);
        String title = arguments.getString(KEY_TITLE);
        String des = arguments.getString(KEY_DES);
        ArrayList<String> tags = arguments.getStringArrayList(KEY_TAG);
        tvTitle.setText(title);
        tvDes.setText(TextUtils.isEmpty(des) ? "暂无描述" : des);
        GlideUtils.loadImg(getContext(), cover, ivLittleBg);
        GlideUtils.loadImg(getContext(), cover, GlideUtils.TYPE_TRANSFORM_BLUR, ivBg);


        if (tags != null && tags.size() > 0) {
            flowLayout.setVisibility(View.VISIBLE);
            flowLayout.setAdapter(new CommonTagAdapter(tags));
        } else {
            flowLayout.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.iv_bg, R.id.stv_save, R.id.ll_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_bg:
            case R.id.ll_content:
                pop();
                break;
            case R.id.stv_save:
                showToast(R.string.save);
                break;
        }
    }
}
