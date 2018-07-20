package com.yzx.xiaomusic.ui.setting;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.utils.EventBusUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yzx on 2018/7/20.
 * Description 设置
 */
public class SettingFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.tv_login_out)
    TextView tvLoginOut;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        tvTitle.setText(R.string.setting);
        initToolBar(tb);
    }

    @OnClick(R.id.tv_login_out)
    public void onViewClicked() {
        AVUser.logOut();
        EventBusUtils.post(new MessageEvent(MessageEvent.TYPE_USER_INFOR_CHANGED));
    }
}
