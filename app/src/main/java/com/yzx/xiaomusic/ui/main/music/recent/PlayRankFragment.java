package com.yzx.xiaomusic.ui.main.music.recent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.db.entity.ExtraMusicInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.ui.adapter.RankPlayAdapter;
import com.yzx.xiaomusic.utils.JsonUtils;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author yzx
 * @date 2018/7/12
 * Description 听歌记录排行榜
 */
public class PlayRankFragment extends BaseFragment implements CommonBaseAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private RankPlayAdapter adapter;
    private List<MusicInfo> musicInfos;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_play_rank;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        adapter = new RankPlayAdapter(getChildFragmentManager(), (SupportFragment) getParentFragment());
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setData();
    }

    @SuppressLint("CheckResult")
    private void setData() {
        showLoadingLayout();
        List<ExtraMusicInfo> allRecentMusicInfos = DBUtils.getExtraMusicInfoDao().getPlayRankMusicInfos();
        if (allRecentMusicInfos.size() > 0) {
            adapter.setData(allRecentMusicInfos);
            Observable
                    .fromIterable(adapter.datas)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(extraMusicInfo -> (MusicInfo) JsonUtils.stringToObject(extraMusicInfo.getMusicInfo(), MusicInfo.class))
                    .toList()
                    .subscribe(musicInfos -> {
                        this.musicInfos = musicInfos;
                        showSuccessLayout();
                    });
        } else {
            showEmptyLayout();
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        if (musicInfos != null) {
            playMusicWithStartFragment(this, musicInfos, position);
        }
    }

    @Override
    public void reload(View v) {
        super.reload(v);
        setData();
    }
}
