package com.yzx.xiaomusic.ui.singer.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.album.SingerAlbum;
import com.yzx.xiaomusic.ui.adapter.AlbumAdapter;
import com.yzx.xiaomusic.ui.album.AlbumDetailFragment;

import java.util.List;

import butterknife.BindView;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;
import static com.yzx.xiaomusic.ui.singer.SingerDetailsFragment.KEY_ID_SINGER;

/**
 * @author yzx
 */
public class AlbumFragment extends BaseMvpFragment<AlbumPresenter> implements CommonBaseAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String singerId;
    private AlbumAdapter adapter;
    private Bundle arguments;

    @Override
    protected AlbumPresenter getPresenter() {
        return new AlbumPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_detail_singer_album;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        arguments = getArguments();
        singerId = arguments.getString(KEY_ID_SINGER);
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        adapter = new AlbumAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getAlbums(0, singerId);
    }

    public void setData(List<SingerAlbum.HotAlbumsBean> hotAlbums) {
        adapter.addData(hotAlbums);
    }

    @Override
    public void onItemClick(View view, int position) {
        SingerAlbum.HotAlbumsBean hotAlbumsBean = adapter.datas.get(position);

        arguments.clear();
        arguments.putString(KEY_ID, String.valueOf(hotAlbumsBean.getId()));
        arguments.putString(KEY_NAME, hotAlbumsBean.getName());
        arguments.putString(KEY_COVER, hotAlbumsBean.getPicUrl());
        easyParentStart(new AlbumDetailFragment(), arguments);
    }
}
