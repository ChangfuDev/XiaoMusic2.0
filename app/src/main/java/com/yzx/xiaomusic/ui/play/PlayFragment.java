package com.yzx.xiaomusic.ui.play;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.play.card.PlayCardFragment;
import com.yzx.xiaomusic.ui.play.lyric.LyricFragment;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.utils.TimeUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yzx
 * @date 2018/6/20
 * Description  播放页面
 */
public class PlayFragment extends BaseMvpFragment<PlayPresenter> implements Toolbar.OnMenuItemClickListener {
    public static final String TAG = "ygl" + PlayFragment.class.getSimpleName();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    @BindView(R.id.tv_current_progress)
    TextView tvCurrentProgress;
    @BindView(R.id.seekBar)
    AppCompatSeekBar seekBar;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    private int showPage;
    private MusicInfo musicInfo;
    private MusicService service;
    private PlayCardFragment playCardFragment;
    private LyricFragment lyricFragment;

    @Override
    protected PlayPresenter getPresenter() {
        return new PlayPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        service = ServiceManager.getInstance().getService();
        musicInfo = service.getMusicInfo();
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        playCardFragment = new PlayCardFragment();
        lyricFragment = new LyricFragment();
        loadMultipleRootFragment(R.id.fragmentContainer, 0, playCardFragment, lyricFragment);
        initToolBar(tb);
        tb.inflateMenu(R.menu.menu_share);
        tb.setOnMenuItemClickListener(this);

        tvSubTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(musicInfo.getMusicName());
        tvSubTitle.setText(MusicDataUtils.getSingers(musicInfo));
        if (!musicInfo.isLocal()) {
            GlideUtils.loadBlurImg(getContext(), musicInfo.getAlbumCoverPath(), ivBg);
        }
        tvDuration.setText(TimeUtils.getFormatData(musicInfo.getDuration(), TimeUtils.FORMAT_MM_SS));
    }

    @OnClick({R.id.tv_subTitle, R.id.tb, R.id.iv_play_mode, R.id.iv_previous, R.id.iv_play_pause, R.id.iv_next, R.id.iv_song_sheet})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_subTitle:
//                SingerDetailsFragment singerDetailsFragment = new SingerDetailsFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString();
//                singerDetailsFragment.setArguments(bundle);
//                start(singerDetailsFragment,SINGLETASK);
                break;
            case R.id.tb:
                break;
            case R.id.iv_play_mode:
                break;
            case R.id.iv_previous:
                service.previous();
                break;
            case R.id.iv_play_pause:
                break;
            case R.id.iv_next:
                service.next();
                break;
            case R.id.iv_song_sheet:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                showToast(R.string.share);
                return true;
            default:
                return false;
        }
    }
}
