package com.yzx.xiaomusic.ui.songsheet.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.SongSheetListAdapter;
import com.yzx.xiaomusic.ui.songsheet.detail.SongSheetDetailFragment;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.widget.CircleProgress;
import com.yzx.xiaomusic.widget.GridItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;

/**
 * @author yzx
 * @date 2018/6/23
 * Description 歌单列表
 */
public class SongSheetListFragment extends BaseMvpFragment<SongSheetListPresenter> implements OnLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.iv_music_cover)
    ImageView ivMusicCover;
    @BindView(R.id.tv_music_name)
    TextView tvMusicName;
    @BindView(R.id.tv_music_singer)
    TextView tvMusicSinger;
    @BindView(R.id.iv_play_pause)
    CircleProgress ivPlayPause;
    Unbinder unbinder;
    @BindView(R.id.layout_bottom_music_controller)
    LinearLayout layoutBottomMusicController;
    Unbinder unbinder1;
    private SongSheetListAdapter adapter;
    private int offset;
    private MusicInfo musicInfo;

    @Override
    protected SongSheetListPresenter getPresenter() {
        return new SongSheetListPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        //重新注册到View里
        loadService = LoadSir
                .getDefault()
                .register(smartRefreshLayout, (Callback.OnReloadListener) v -> mPresenter.getSongSheet(0));
        return view;
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_list_song_sheet;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        initToolBar(tb);
        tvTitle.setText(R.string.songSheet);
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()));
        smartRefreshLayout.setOnLoadMoreListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridItemDecoration(2, DensityUtils.dip2px(getContext(), 3), false));
        adapter = new SongSheetListAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position) -> {
            SongSheetList.PlaylistsBean playlistsBean = adapter.datas.get(position);

            Bundle bundle = new Bundle();
            bundle.putString(KEY_NAME, playlistsBean.getName());
            bundle.putString(KEY_COVER, playlistsBean.getCoverImgUrl());
            bundle.putString(KEY_ID, String.valueOf(playlistsBean.getId()));
            easyStart(new SongSheetDetailFragment(), bundle);
        });
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mPresenter.getSongSheet(0);
    }

    public void setData(List<SongSheetList.PlaylistsBean> list) {
        offset += ApiConstant.LIMIT;
        adapter.addData(list);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        mPresenter.getSongSheet(offset);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        initBottomMusicController(layoutBottomMusicController);
        musicInfo = service.getMusicInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case TYPE_MUSIC_CHANGED:
                service = ServiceManager.getInstance().getService();
                musicInfo = service.getMusicInfo();
                tvMusicName.setText(musicInfo.getMusicName());
                tvMusicSinger.setText(MusicDataUtils.getSingers(musicInfo));
                if (!musicInfo.isLocal()) {
                    GlideUtils.loadImg(getContext(), musicInfo.getAlbumCoverPath(), ivMusicCover);
                }
                break;
            case TYPE_MUSIC_PLAYING:
                ivPlayPause.setState(CircleProgress.STATE_PLAY);
                break;
            case TYPE_MUSIC_PAUSE:
                ivPlayPause.setState(CircleProgress.STATE_PAUSE);
                break;
            case TYPE_MUSIC_UPDATE_PROGRESS:
                Integer content = (Integer) event.getContent();
                ivPlayPause.setMax((int) musicInfo.getDuration());
                ivPlayPause.setProgress(content);
                break;
        }
    }
}
