package com.yzx.xiaomusic.ui.search.result;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.LogUtils;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.base.LoadMoreView;
import com.yzx.xiaomusic.model.entity.common.AlbumInfo;
import com.yzx.xiaomusic.model.entity.common.SongSheetInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.model.entity.eventbus.SearchContent;
import com.yzx.xiaomusic.model.entity.search.SearchSingerResult.ResultBean.ArtistsBean;
import com.yzx.xiaomusic.model.entity.search.SearchUserResult;
import com.yzx.xiaomusic.network.ApiConstant;
import com.yzx.xiaomusic.ui.adapter.SearchResultAdapter;
import com.yzx.xiaomusic.ui.album.AlbumDetailFragment;
import com.yzx.xiaomusic.ui.search.SearchFragment;
import com.yzx.xiaomusic.ui.singer.SingerDetailsFragment;
import com.yzx.xiaomusic.ui.songsheet.SongSheetDetailFragment;
import com.yzx.xiaomusic.ui.usercenter.UserCenterFragment;
import com.yzx.xiaomusic.widget.loadsir.EmptyCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_SEARCH_CONTENT;
import static com.yzx.xiaomusic.ui.singer.SingerDetailsFragment.KEY_INFO_SINGER;
import static com.yzx.xiaomusic.ui.usercenter.UserCenterFragment.KEY_USER_ID;

/**
 * 展示搜索结果页面
 */

public class SearchResultFragment extends BaseMvpFragment<SearchResultPresenter> implements LoadMoreView, CommonBaseAdapter.OnItemClickListener {

    /**
     * search_type	含义
     * 1	单曲
     * 10	专辑
     * 100	歌手
     * 1000	歌单
     * 1002	用户
     * 1004	mv
     * 1006	歌词
     * 1009	主播电台
     */
    public static final int TYPE_SEARCH_MUSIC = 1;
    public static final int TYPE_SEARCH_ALBUM = 10;
    public static final int TYPE_SEARCH_SINGER = 100;
    public static final int TYPE_SEARCH_SONG_SHEET = 1000;
    public static final int TYPE_SEARCH_USER = 1002;
    public static final int TYPE_SEARCH_MV = 1004;
    public static final int TYPE_SEARCH_LYRICS = 1006;
    public static final int TYPE_SEARCH_RADIO = 1009;

    public static final String SEARCH_TYPE = "searchType";
    public static final String SEARCH_CONTENT = "searchContent";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String searchContent;
    private int searchType;

    int offset;
    private SearchResultAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_search_result;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            searchType = arguments.getInt(SEARCH_TYPE);
        } else {
            showToast(R.string.error_get_data);
        }
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> search(offset));

        adapter = new SearchResultAdapter(searchType);
        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();

        getSearchContent();
        search(0);
    }

    /**
     * 获取父布局
     */
    private void getSearchContent() {
        SearchFragment searchFragment = (SearchFragment) getParentFragment();
        searchContent = searchFragment.getSearchContent();
    }

    @Override
    protected SearchResultPresenter getPresenter() {
        return new SearchResultPresenter();
    }

    public void search(int offset) {
        mPresenter.getSearchResult(searchType, offset * ApiConstant.LIMIT, searchContent);
    }

    @Override
    public void onLoadMoreSuccess(List datas) {
        smartRefreshLayout.finishLoadMore();
        if (datas != null && datas.size() > 0) {
            adapter.addData(datas);
            offset++;
            if (datas.size() < ApiConstant.LIMIT) {
//                smartRefreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else {
            if (offset == 0) {
                loadService.showCallback(EmptyCallback.class);
            }
        }
    }

    @Override
    public void onLoadMoreFail(String errorMsg) {
        smartRefreshLayout.finishLoadMore();
        showToast(errorMsg, ToastUtils.TYPE_FAIL);
    }

    @Override
    public void reload(View v) {
        super.reload(v);
        search(offset);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case TYPE_SEARCH_CONTENT:

                SearchContent content = (SearchContent) event.getContent();
                searchContent = content.getSearchContent();
                if (searchType == content.getSearchType()) {
                    search(0);
                    LogUtils.d(SearchResultFragment.class.getSimpleName(), "lazyLoadData: " + searchType + content.getSearchContent());
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onItemClick(View view, int position) {

        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.rl_search_music:
                break;
            case R.id.rl_search_mv:
                break;
            case R.id.rl_search_singer:
                bundle.clear();
                bundle.putSerializable(KEY_INFO_SINGER, (ArtistsBean) adapter.datas.get(position));
                easyParentStart(new SingerDetailsFragment(), bundle);
                break;
            case R.id.rl_search_album:
                bundle.clear();
                AlbumInfo albumInfo = (AlbumInfo) adapter.datas.get(position);
                bundle.putString(KEY_ID, albumInfo.getId());
                bundle.putString(KEY_NAME, albumInfo.getName());
                bundle.putString(KEY_COVER, albumInfo.getCover());
                easyParentStart(new AlbumDetailFragment(), bundle);
                break;
            case R.id.rl_search_song_sheet:
                bundle.clear();
                SongSheetInfo songSheetInfo = (SongSheetInfo) adapter.datas.get(position);
                bundle.putString(KEY_ID, songSheetInfo.getId());
                bundle.putString(KEY_NAME, songSheetInfo.getTitle());
                bundle.putString(KEY_COVER, songSheetInfo.getCoverUrl());
                easyParentStart(new SongSheetDetailFragment(), bundle);
                break;
            case R.id.rl_search_radio:
                break;
            case R.id.rl_search_user:
                bundle.clear();
                SearchUserResult.ResultBean.UserprofilesBean userprofilesBean = (SearchUserResult.ResultBean.UserprofilesBean) adapter.datas.get(position);
                bundle.putString(KEY_USER_ID, userprofilesBean.getUserId());
                easyParentStart(new UserCenterFragment(), bundle);
                break;
            default:
                break;
        }
    }
}
