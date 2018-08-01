package com.yzx.xiaomusic.ui.usercenter.music;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.common.SongSheetInfo;
import com.yzx.xiaomusic.model.entity.user.UserSongSheet;
import com.yzx.xiaomusic.ui.adapter.SongSheetAdapter;
import com.yzx.xiaomusic.ui.songsheet.detail.SongSheetDetailFragment;
import com.yzx.xiaomusic.ui.usercenter.UserCenterFragment;
import com.yzx.xiaomusic.widget.MusicFooter;

import butterknife.BindView;
import io.reactivex.Observable;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;
import static com.yzx.xiaomusic.ui.usercenter.UserCenterFragment.KEY_USER_ID;

/**
 * @author yzx
 */
public class UserCenterMusicFragment extends BaseMvpFragment<UserCenterMusicPresenter> implements CommonBaseAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String userId;
    private SongSheetAdapter adapter;
    private int index;

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
        smartRefreshLayout.setEnableOverScrollDrag(false);
        adapter = new SongSheetAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        smartRefreshLayout.setRefreshFooter(new MusicFooter(getContext()));
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.getUserInfo(index, "10", userId));
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
        mPresenter.getUserInfo(index, "10", userId);
    }


    @SuppressLint("CheckResult")
    public void setData(UserSongSheet userSongSheet) {

        if (userSongSheet.getPlaylist() != null && userSongSheet.getPlaylist().size() > 0) {

            UserCenterFragment userCenterFragment = (UserCenterFragment) getParentFragment();
            if (index == 0) {
                userCenterFragment.upData(userSongSheet.getPlaylist().get(0).getCreator());
            }
            Observable
                    .fromIterable(userSongSheet.getPlaylist())
                    .map(playlistBean -> {

                        SongSheetInfo songSheet = new SongSheetInfo();
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
                    .subscribe(songSheets -> adapter.addData(songSheets));

            index += 10;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        SongSheetInfo songSheetInfo = adapter.datas.get(position);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, songSheetInfo.getId());
        bundle.putString(KEY_NAME, songSheetInfo.getTitle());
        bundle.putString(KEY_COVER, songSheetInfo.getCoverUrl());
        easyParentStart(new SongSheetDetailFragment(), bundle);
    }
}
