package com.yzx.xiaomusic.ui.main.discover.recommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.ui.adapter.RecommendAdapter;

import java.util.ArrayList;
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
    private RecommendAdapter songSheetAdapter;
    private RecommendAdapter albumAdapter;

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

        //创建RecyclerView & VirtualLayoutManager 对象并进行绑定
        //设置布局管理器
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        recyclerView.setLayoutManager(virtualLayoutManager);
//        设置回收复用池大小
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
//        步骤3：设置Adapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        recyclerView.setAdapter(delegateAdapter);

        //创建存储adapter的list
        List<DelegateAdapter.Adapter> adapters = new ArrayList<>();
        //banner
        RecommendAdapter bannerAdapter = new RecommendAdapter(parentFragment, new SingleLayoutHelper(), 1) {
            @Override
            public int getItemViewType(int position) {
                return TYPE_BANNER;
            }
        };
        adapters.add(bannerAdapter);

        RecommendAdapter theFourAdapter = new RecommendAdapter(parentFragment, new SingleLayoutHelper(), 1) {
            @Override
            public int getItemViewType(int position) {
                return TYPE_FOUR;
            }
        };
        adapters.add(theFourAdapter);
        //标题-歌单
        adapters.add(new RecommendAdapter(parentFragment, new SingleLayoutHelper(), 1) {
            @Override
            public int getItemViewType(int position) {
                return TYPE_TITLE_SONG_SHEET;
            }
        });
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        int margin = DensityUtils.dip2px(getContext(), 5);
        gridLayoutHelper.setMargin(margin, 0, margin, 0);
        //控制两个grid之间横向间距
        gridLayoutHelper.setHGap(DensityUtils.dip2px(getContext(), 3));
        songSheetAdapter = new RecommendAdapter(parentFragment, gridLayoutHelper, 6) {
            @Override
            public int getItemViewType(int position) {
                return TYPE_SONG_SHEET;
            }
        };
        adapters.add(songSheetAdapter);
        //标题-最新歌曲
        adapters.add(new RecommendAdapter(parentFragment, new SingleLayoutHelper(), 1) {
            @Override
            public int getItemViewType(int position) {
                return TYPE_TITLE_ALBUM;
            }
        });
        GridLayoutHelper albumGridLayoutHelper = new GridLayoutHelper(3);
        //控制两个grid之间横向间距
        albumGridLayoutHelper.setHGap(DensityUtils.dip2px(getContext(), 3));
        albumGridLayoutHelper.setMargin(margin, 0, margin, 0);
        albumAdapter = new RecommendAdapter(parentFragment, albumGridLayoutHelper, 6) {
            @Override
            public int getItemViewType(int position) {
                return TYPE_ALBUM;
            }
        };
        adapters.add(albumAdapter);
        delegateAdapter.setAdapters(adapters);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getSongSheet();
        mPresenter.getLatestAlbums();
    }

    public void setSongSheetData(List<SongSheetList.PlaylistsBean> playlists) {
        songSheetAdapter.setSongSheetData(playlists);
    }

    public void setAlbumData(List<LatestAlbumList.AlbumsBean> albums) {
        albumAdapter.setAlbumData(albums);
    }
}
