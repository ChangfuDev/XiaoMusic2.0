package com.yzx.xiaomusic.ui.main.discover.recommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.ui.adapter.RecommendAdapter;

import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author yzx
 * @date 2018/6/21
 * Description 推荐页面
 */
public class RecommendFragment extends BaseMvpFragment<RecommendPresenter> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private RecommendAdapter adapter;

    @Override
    protected RecommendPresenter getPresenter() {
        return new RecommendPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        showLoadingLayout();
        return view;
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        SupportFragment parentFragment = (SupportFragment) getParentFragment().getParentFragment();
        adapter = new RecommendAdapter(parentFragment);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getSongSheet();
        mPresenter.getLatestAlbums();
    }

    public void setSongSheetData(List<SongSheetList.PlaylistsBean> playlists) {
        adapter.setSongSheetData(playlists);
    }

    public void setAlbumData(List<LatestAlbumList.AlbumsBean> albums) {
        adapter.setAlbumData(albums);
    }
}
