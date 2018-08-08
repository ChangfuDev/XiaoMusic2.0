package com.yzx.xiaomusic.ui.main.navigation;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.othershe.library.NiceImageView;
import com.yzx.xiaomusic.Constant;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.widget.grade.GradeView;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by yzx on 2018/8/6.
 * Description 等级页面
 */
public class GradeFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.gradeView)
    GradeView gradeView;
    @BindView(R.id.iv_head)
    NiceImageView ivHead;
    Unbinder unbinder;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_grade;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        initToolBar(tb);
        tvTitle.setText(R.string.myGrade);

        GlideUtils.loadImg(getContext(), Constant.PIC, ivHead);
    }
}
