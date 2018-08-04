package com.yzx.xiaomusic.ui.main.navigation;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.widget.signview.SignInData;
import com.yzx.xiaomusic.widget.signview.SignInView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author yzx
 * @date 2018/8/3
 * Description 签到页面
 */
public class SignInFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.signInView)
    SignInView signInView;
    private ArrayList<SignInData> signInDatas;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);


        signInDatas = new ArrayList<>();
        signInDatas.add(new SignInData(0, "周日"));
        signInDatas.add(new SignInData(1, "周一"));
        signInDatas.add(new SignInData(0, "周二"));
        signInDatas.add(new SignInData(1, "周三"));
        signInDatas.add(new SignInData(0, "周四"));
        signInDatas.add(new SignInData(1, "周五"));
        signInDatas.add(new SignInData(0, "周六"));
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        initToolBar(tb);
        tvTitle.setText(R.string.signIn);
        signInView.setData(signInDatas);
    }
}
