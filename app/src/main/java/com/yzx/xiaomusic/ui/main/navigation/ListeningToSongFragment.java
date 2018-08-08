package com.yzx.xiaomusic.ui.main.navigation;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.widget.listeningtosongs.ListeningToSongView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yzx
 * @date 2018/8/6
 * Description 听歌识曲页面
 */
public class ListeningToSongFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.listeningToSongView)
    ListeningToSongView listeningToSongView;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_listening_to_song;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        initToolBar(tb);
        tvTitle.setText(R.string.listeningToSong);
    }

    @OnClick(R.id.listeningToSongView)
    public void onViewClicked() {
        listeningToSongView.startStop();
    }
}
