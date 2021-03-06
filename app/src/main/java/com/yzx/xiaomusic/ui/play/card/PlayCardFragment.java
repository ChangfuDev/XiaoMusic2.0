package com.yzx.xiaomusic.ui.play.card;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.db.dao.ExtraMusicInfoDao;
import com.yzx.xiaomusic.db.entity.ExtraMusicInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.MusicAdapter;
import com.yzx.xiaomusic.ui.adapter.PlayCardAdapter;
import com.yzx.xiaomusic.ui.comment.music.MusicCommentFragment;
import com.yzx.xiaomusic.ui.dialog.BottomMusicInfoDialog;
import com.yzx.xiaomusic.ui.play.PlayFragment;
import com.yzx.xiaomusic.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.ui.adapter.MusicAdapter.KEY_MUSIC_INFO;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class PlayCardFragment extends BaseFragment {
    public static final String TAG = "ygl" + PlayCardFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_like)
    ImageView ivLike;
    private MusicInfo musicInfo;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_play_card;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBusUtils.register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        PagerSnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(recyclerView);

        //处理滑动时歌曲切换
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (SCROLL_STATE_IDLE == newState) {
                    int targetSnapPosition = snapHelper.findTargetSnapPosition(recyclerView.getLayoutManager(), recyclerView.getScrollX(), recyclerView.getScrollY());
                    if (targetSnapPosition != service.getIndex() && service.getSongSheet() != null) {
                        service.setMusicIndex(targetSnapPosition);
                        service.realPlay();
                        Log.i(TAG, "onScrollStateChanged: " + service.getMusicInfo().getMusicName() + targetSnapPosition);
                    }
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        PlayCardAdapter adapter = new PlayCardAdapter();
        service = ServiceManager.getInstance().getService();
        adapter.setData(service.getSongSheet());
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(service.getIndex());
        adapter.setOnItemClickListener((view, position) -> {
            PlayFragment playFragment = (PlayFragment) getParentFragment();
            playFragment.showHideFragment(playFragment.lyricFragment, this);
        });
        showLiked();
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.scrollToPosition(service.getIndex());
    }

    @OnClick({R.id.iv_like, R.id.iv_download, R.id.iv_evaluate, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                likeMusic();
                break;
            case R.id.iv_download:
                showToast(R.string.commingSoon);
                break;
            case R.id.iv_evaluate:
                musicInfo = service.getMusicInfo();
                Bundle args1 = new Bundle();
                args1.putString(KEY_ID, musicInfo.getMusicId());
                easyParentStart(new MusicCommentFragment(), args1);
                break;
            case R.id.iv_more:
                musicInfo = service.getMusicInfo();
                if (musicInfo != null) {
                    BottomMusicInfoDialog bottomMusicInfoDialog = new BottomMusicInfoDialog();
                    Bundle args = new Bundle();
                    args.putSerializable(KEY_MUSIC_INFO, musicInfo);
                    bottomMusicInfoDialog.setArguments(args);
                    bottomMusicInfoDialog.setParentFragment((SupportFragment) getParentFragment());
                    bottomMusicInfoDialog.show(getChildFragmentManager(), MusicAdapter.class.getSimpleName());
                }
                break;
        }
    }

    /**
     * 点击喜欢收藏到喜欢数据里
     */
    private void likeMusic() {
        MusicInfo musicInfo = service.getMusicInfo();
        if (musicInfo == null) {
            showToast("无法获取当前音乐");
        } else {
            ExtraMusicInfoDao extraMusicInfoDao = DBUtils.getExtraMusicInfoDao();
            ExtraMusicInfo likedMusicInfo = extraMusicInfoDao.getLikedMusicInfoById(musicInfo.getMusicId());
            if (likedMusicInfo == null) {
                DBUtils.likeMusic(musicInfo);
                ivLike.setImageResource(R.drawable.ae2);
            } else {
                DBUtils.cancelLikeMusic(likedMusicInfo);
                ivLike.setImageResource(R.drawable.ae0);
            }
        }
    }

    @Override
    public void onDestroyView() {
        EventBusUtils.unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case TYPE_MUSIC_CHANGED:
                recyclerView.scrollToPosition(service.getIndex());
                showLiked();
                break;
        }
    }

    /**
     * 根据是否收藏显示图标
     */
    private void showLiked() {
        musicInfo = service.getMusicInfo();
        if (musicInfo != null) {
            ExtraMusicInfoDao likedMusicInfoDao = DBUtils.getExtraMusicInfoDao();
            ExtraMusicInfo likedMusicInfo = likedMusicInfoDao.getLikedMusicInfoById(musicInfo.getMusicId());
            ivLike.setImageResource(likedMusicInfo == null ? R.drawable.ae0 : R.drawable.ae2);
        }
    }
}
