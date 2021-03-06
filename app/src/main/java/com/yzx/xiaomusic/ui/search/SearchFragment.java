package com.yzx.xiaomusic.ui.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.db.dao.SearchHistoryDao;
import com.yzx.xiaomusic.db.entity.SearchHistory;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.model.entity.eventbus.SearchContent;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.HotSearchAdapter;
import com.yzx.xiaomusic.ui.adapter.SearchAdapter;
import com.yzx.xiaomusic.ui.adapter.SearchHistoryAdapter;
import com.yzx.xiaomusic.ui.adapter.SearchResultPagerAdapter;
import com.yzx.xiaomusic.ui.search.result.SearchResultFragment;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.widget.CircleProgress;
import com.yzx.xiaomusic.widget.simplelistenner.SimpleTabSelectedListener;
import com.yzx.xiaomusic.widget.simplelistenner.SimpleTextWathcer;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_CHANGED;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PAUSE;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_PLAYING;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_MUSIC_UPDATE_PROGRESS;
import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_SEARCH_CONTENT;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.SEARCH_TYPE;


/**
 * @author yzx
 */
public class SearchFragment extends BaseFragment implements TagFlowLayout.OnTagClickListener, CommonBaseAdapter.OnItemClickListener, AdapterView.OnItemClickListener {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ll_search_result)
    LinearLayout llSearchResult;
    @BindView(R.id.ll_singer_classify)
    LinearLayout llSingerClassify;
    @BindView(R.id.iv_music_cover)
    ImageView ivMusicCover;
    @BindView(R.id.tv_music_name)
    TextView tvMusicName;
    @BindView(R.id.tv_music_singer)
    TextView tvMusicSinger;
    @BindView(R.id.iv_play_pause)
    CircleProgress ivPlayPause;
    @BindView(R.id.iv_song_sheet)
    ImageView ivSongSheet;
    @BindView(R.id.layout_bottom_music_controller)
    LinearLayout layoutBottomMusicController;
    private ListPopupWindow listPopupWindow;
    private List<String> hotSearchs;
    private ArrayList<String> searchs;
    private SearchHistoryDao searchHistoryDao;
    private List<SearchHistory> leastFiveSearchHistory;
    private SearchHistoryAdapter searchHistoryAdapter;
    //    private ArrayList<String> tabTitles;
    private ArrayList<Fragment> fragments;
    private SearchResultPagerAdapter adapter;
    private MusicInfo musicInfo;
    private String[] tabTitles = {"单曲", "视频", "歌手", "专辑", "歌单", "主播电台", "用户"};

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        searchHistoryDao = DBUtils.getSearchHistoryDao();
        leastFiveSearchHistory = searchHistoryDao.getLeastFiveSearchHistory();

        searchs = new ArrayList<>();

        hotSearchs = new ArrayList<>();
        hotSearchs.add("刘惜君");
        hotSearchs.add("冬天的秘密");
        hotSearchs.add("一生所爱");
        hotSearchs.add("Back To December");
        hotSearchs.add("一生所爱");
        hotSearchs.add("追光者");
        hotSearchs.add("爱得太迟");
        hotSearchs.add("Goodbye My Lover");
        hotSearchs.add("Hero");
        hotSearchs.add("I Am You ");
        hotSearchs.add("Say SomeThing");


        fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.length; i++) {

            SearchResultFragment searchResultFragment = new SearchResultFragment();
            Bundle args = new Bundle();
            args.putInt(SEARCH_TYPE, SearchUtils.getSearchTypeByPosition(i));
            searchResultFragment.setArguments(args);
            fragments.add(searchResultFragment);
        }

    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        tb.setNavigationOnClickListener(v -> pop());

        llSearch.setVisibility(View.VISIBLE);
        llSearchResult.setVisibility(View.GONE);
        //搜索页面

        //搜索框操作
        ivClear.setVisibility(View.GONE);
        if (listPopupWindow == null) {
            listPopupWindow = new ListPopupWindow(getContext());
        }

        listPopupWindow.setAnchorView(tb);
        listPopupWindow.setVerticalOffset(15);
        listPopupWindow.setHorizontalOffset(15);
        listPopupWindow.setDropDownGravity(Gravity.CENTER_HORIZONTAL);
        listPopupWindow.setWidth(getResources().getDisplayMetrics().widthPixels - 30);
        SearchAdapter searchAdapter = new SearchAdapter();
        listPopupWindow.setAdapter(searchAdapter);
        listPopupWindow.setOnItemClickListener(this);

        etSearch.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {

                    searchs.clear();
                    searchs.add(String.valueOf(s));
                    searchAdapter.setDatas(searchs);
                    listPopupWindow.show();
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    dismissPop();
                    ivClear.setVisibility(View.GONE);
                }
            }
        });


        //热门搜索
        flowLayout.setAdapter(new HotSearchAdapter(hotSearchs));
        flowLayout.setOnTagClickListener(this);

        //搜索历史

        leastFiveSearchHistory = searchHistoryDao.getLeastFiveSearchHistory();

        searchHistoryAdapter = new SearchHistoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        searchHistoryAdapter.setData(leastFiveSearchHistory);
        recyclerView.setAdapter(searchHistoryAdapter);
        searchHistoryAdapter.setOnItemClickListener(this);


        //搜索结果页面
        adapter = new SearchResultPagerAdapter(getChildFragmentManager());
//        setUpViewPager(viewPager, tabLayout, adapter, fragments, tabTitles);
        adapter.setFragments(fragments);
        viewPager.setOffscreenPageLimit(tabTitles.length);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager, tabTitles);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.setCurrentTab(position);
            }
        });
        tabLayout.setOnTabSelectListener(new SimpleTabSelectedListener() {
            @Override
            public void onTabSelect(int position) {
                super.onTabSelect(position);
                viewPager.setCurrentItem(position, true);
            }
        });
    }


    @Override
    public void onPause() {
        dismissPop();
        super.onPause();
    }

    @OnClick({R.id.iv_clear, R.id.ll_singer_classify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etSearch.setText(null);
                dismissPop();
                break;
            case R.id.ll_singer_classify:
                showToast(R.string.commingSoon);
                break;
        }

    }

    private void dismissPop() {
        if (listPopupWindow != null && listPopupWindow.isShowing()) {
            listPopupWindow.dismiss();
        }
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        String content = hotSearchs.get(position);
        doSearch(content);
        SearchHistory searchHistory = new SearchHistory(content);
        searchHistoryDao.addSearchHistory(searchHistory);
        return true;
    }

    @Override
    public void onItemClick(View view, int position) {
        String text = searchHistoryAdapter.datas.get(position).getTitle();
        doSearch(text);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String content = searchs.get(position);
        doSearch(content);
        SearchHistory searchHistory = new SearchHistory(content);
        searchHistoryDao.addSearchHistory(searchHistory);
        dismissPop();
    }

    /**
     * TODO 真正搜索
     *
     * @param content
     */
    private void doSearch(String content) {

        int currentItem = viewPager.getCurrentItem();

        EventBusUtils.postSticky(new MessageEvent(TYPE_SEARCH_CONTENT, new SearchContent(SearchUtils.getSearchTypeByPosition(currentItem), content)));
        etSearch.setText(content);
        dismissPop();
        llSearchResult.setVisibility(View.VISIBLE);
        llSearch.setVisibility(View.GONE);
    }


    public String getSearchContent() {
        return etSearch.getText().toString().trim();
    }

    @Override
    public void onResume() {
        super.onResume();
        initBottomMusicController(layoutBottomMusicController);
        musicInfo = service.getMusicInfo();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBusUtils.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBusUtils.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case TYPE_MUSIC_CHANGED:
                service = ServiceManager.getInstance().getService();
                musicInfo = service.getMusicInfo();
                tvMusicName.setText(musicInfo.getMusicName());
                tvMusicSinger.setText(MusicDataUtils.getSingers(musicInfo));
                if (!musicInfo.isLocal()) {
                    GlideUtils.loadImg(getContext(), musicInfo.getAlbumCoverPath(), ivMusicCover);
                }
                break;
            case TYPE_MUSIC_PLAYING:
                ivPlayPause.setState(CircleProgress.STATE_PLAY);
                break;
            case TYPE_MUSIC_PAUSE:
                ivPlayPause.setState(CircleProgress.STATE_PAUSE);
                break;
            case TYPE_MUSIC_UPDATE_PROGRESS:
                Integer content = (Integer) event.getContent();
                ivPlayPause.setMax((int) musicInfo.getDuration());
                ivPlayPause.setProgress(content);
                break;
        }
    }
}
