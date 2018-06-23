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
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.ui.adapter.SongSheetListAdapter;
import com.yzx.xiaomusic.ui.songsheet.detail.SongSheetDetailFragment;
import com.yzx.xiaomusic.widget.GridItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;

/**
 * Created by yzx on 2018/6/23.
 * Description
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
    Unbinder unbinder;
    private SongSheetListAdapter adapter;
    private int offset;

    @Override
    protected SongSheetListPresenter getPresenter() {
        return new SongSheetListPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
}
