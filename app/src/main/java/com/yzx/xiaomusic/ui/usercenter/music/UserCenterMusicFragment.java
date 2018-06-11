package com.yzx.xiaomusic.ui.usercenter.music;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.common.SongSheet;
import com.yzx.xiaomusic.model.entity.user.UserSongSheet;
import com.yzx.xiaomusic.ui.adapter.SongSheetAdapter;
import com.yzx.xiaomusic.ui.usercenter.UserCenterFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.yzx.xiaomusic.ui.usercenter.UserCenterFragment.KEY_USER_ID;

public class UserCenterMusicFragment extends BaseMvpFragment<UserCenterMusicPresenter> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String userId;
    private SongSheetAdapter adapter;

    @Override
    protected UserCenterMusicPresenter getPresenter() {
        return new UserCenterMusicPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_user_center_music;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle arguments = getArguments();
        userId = arguments.getString(KEY_USER_ID);
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        adapter = new SongSheetAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
        mPresenter.getUserInfo("0", "10", userId);
    }


    @SuppressLint("CheckResult")
    public void setData(UserSongSheet userSongSheet) {

        if (userSongSheet.getPlaylist() != null && userSongSheet.getPlaylist().size() > 0) {

            UserCenterFragment userCenterFragment = (UserCenterFragment) getParentFragment();
            userCenterFragment.upData(userSongSheet.getPlaylist().get(0).getCreator());
            Observable
                    .fromIterable(userSongSheet.getPlaylist())
                    .map(playlistBean -> {

                        SongSheet songSheet = new SongSheet();
                        songSheet.setId(playlistBean.getId());
                        songSheet.setCoverUrl(playlistBean.getCoverImgUrl());
                        songSheet.setDes(playlistBean.getDescription());
                        songSheet.setTitle(playlistBean.getName());
                        songSheet.setPlayCount(String.valueOf(playlistBean.getPlayCount()));
                        songSheet.setMusicCount(String.valueOf(playlistBean.getTrackCount()));
                        UserSongSheet.PlaylistBean.CreatorBean creator = playlistBean.getCreator();
                        if (creator != null) {
                            songSheet.setCreatorId(creator.getUserId());
                            songSheet.setCreatorCoverUrl(creator.getAvatarUrl());
                            songSheet.setCreatorBgUrl(creator.getBackgroundUrl());
                            songSheet.setCreatorNickName(creator.getNickname());
                        }
                        return songSheet;
                    })
                    .toList()
                    .subscribe(songSheets -> adapter.setData(songSheets));
        }
    }
}
