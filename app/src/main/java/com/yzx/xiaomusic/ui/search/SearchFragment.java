package com.yzx.xiaomusic.ui.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
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

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.db.dao.SearchHistoryDao;
import com.yzx.xiaomusic.db.entity.SearchHistory;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.model.entity.eventbus.SearchContent;
import com.yzx.xiaomusic.ui.adapter.HotSearchAdapter;
import com.yzx.xiaomusic.ui.adapter.SearchAdapter;
import com.yzx.xiaomusic.ui.adapter.SearchHistoryAdapter;
import com.yzx.xiaomusic.ui.adapter.SearchResultPagerAdapter;
import com.yzx.xiaomusic.ui.search.result.SearchResultFragment;
import com.yzx.xiaomusic.widget.simplelistenner.SimpleTextWathcer;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yzx.xiaomusic.model.entity.eventbus.MessageEvent.TYPE_SEARCH_CONTENT;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.SEARCH_TYPE;


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
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ll_search_result)
    LinearLayout llSearchResult;
    private ListPopupWindow listPopupWindow;
    private List<String> hotSearchs;
    private ArrayList<String> searchs;
    private SearchHistoryDao searchHistoryDao;
    private List<SearchHistory> leastFiveSearchHistory;
    private SearchHistoryAdapter searchHistoryAdapter;
    private ArrayList<String> tabTitles;
    private ArrayList<Fragment> fragments;
    private SearchResultPagerAdapter adapter;


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

        tabTitles = new ArrayList<>();
        tabTitles.add("单曲");
        tabTitles.add("视频");
        tabTitles.add("歌手");
        tabTitles.add("专辑");
        tabTitles.add("歌单");
        tabTitles.add("主播电台");
        tabTitles.add("用户");


        fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.size(); i++) {

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

        tl.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setOffscreenPageLimit(tabTitles.size());
        adapter = new SearchResultPagerAdapter(getChildFragmentManager());
        adapter.setTitles(tabTitles);
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tl.setupWithViewPager(viewPager);

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

        EventBus.getDefault().postSticky(new MessageEvent(TYPE_SEARCH_CONTENT, new SearchContent(SearchUtils.getSearchTypeByPosition(currentItem), content)));
        etSearch.setText(content);
        dismissPop();
        llSearchResult.setVisibility(View.VISIBLE);
        llSearch.setVisibility(View.GONE);
    }


    public String getSearchContent() {
        return etSearch.getText().toString().trim();
    }

}
