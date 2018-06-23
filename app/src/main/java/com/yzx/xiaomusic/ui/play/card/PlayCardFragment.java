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

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.PlayCardAdapter;
import com.yzx.xiaomusic.ui.play.PlayFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class PlayCardFragment extends BaseFragment {
    public static final String TAG = "ygl" + PlayCardFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MusicInfo musicInfo;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_play_card;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
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
                    if (targetSnapPosition != service.getIndex()) {
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
                break;
            case R.id.iv_download:
                break;
            case R.id.iv_evaluate:
                break;
            case R.id.iv_more:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case TYPE_MUSIC_CHANGED:
                musicInfo = service.getMusicInfo();
                recyclerView.scrollToPosition(service.getIndex());
                break;
        }
    }
}
