package com.yzx.xiaomusic.ui.main.discover.recommend;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.ui.adapter.SongSheetListAdapter;
import com.yzx.xiaomusic.ui.songsheet.SongSheetDetailFragment;
import com.yzx.xiaomusic.widget.GridItemDecoration;

import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;

/**
 * @author yzx
 * @date 2018/6/21
 * Description 推荐页面
 */
public class RecommendFragment extends BaseMvpFragment<RecommendPresenter> implements OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private SongSheetListAdapter adapter;

    @Override
    protected RecommendPresenter getPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(this);

        adapter = new SongSheetListAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.addItemDecoration(new GridItemDecoration(3, (int) DensityUtils.dip2px(3), true));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position) -> {
            Log.i("ygl", "initView: 点击了");
            Bundle bundle = new Bundle();
            SongSheetList.PlaylistsBean playlistsBean = adapter.datas.get(position);
            bundle.putString(KEY_ID, String.valueOf(playlistsBean.getId()));
            bundle.putString(KEY_NAME, playlistsBean.getName());
            bundle.putString(KEY_COVER, playlistsBean.getCoverImgUrl());
            SupportFragment parentFragment = (SupportFragment) getParentFragment().getParentFragment();
            SongSheetDetailFragment songSheetDetailFragment = new SongSheetDetailFragment();
            songSheetDetailFragment.setArguments(bundle);
            parentFragment.start(songSheetDetailFragment);
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mPresenter.getSongSheet(0);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mPresenter.getSongSheet(0);
    }

    public void setData(List<SongSheetList.PlaylistsBean> data) {
        adapter.setData(data);
    }
}
